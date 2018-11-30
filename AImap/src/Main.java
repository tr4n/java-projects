import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static int width = 0, height = 0;
    public static int[][] map = new int[20][20];
    public static String[] orderRandom = {"1230", "2301", "3012", "0123", "1032", "2103", "3021", "0213", "1320"};
    public static int[] directX = {0, -1, 0, 1};
    public static int[] directY = {1, 0, -1, 0};
    public static int number = 0;
    public static List<Pair<Integer, Integer>> pairList = new ArrayList<>();
    public static List<Flow> flowList;

    public static void initialization() {
        flowList = new ArrayList<>();
    }

    public static boolean isInside(int x, int y, int width, int height) {
        return (0 <= x && x < height && 0 <= y && y < width);
    }

    private static void getRandomMap(int width, int height) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = -1;
            }
        }

        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == -1) {
                    if(i == height -1 && j > 1 && map[i][j-1] == -1){

                            map[i][j] = map[i][j-1] = count ++;

                    }else if(j == width -1 && i > 1 && map[i-1][j] == -1){
                        map[i][j] = map[i-1][j] = count++;
                    }else{
                        int k = random.nextInt(orderRandom.length);
                        for (int index = 0; index < 4; index++) {
                            int dir = (int) (orderRandom[k].charAt(index) - '0');
                            //System.out.println("k = " + orderRandom[k] + " dir = " + dir );
                            int checkX = i + directX[dir], checkY = j + directY[dir];
                            if (!isInside(checkX, checkY, width, height)) continue;
                            if (map[checkX][checkY] != -1) continue;
                            map[i][j] = map[checkX][checkY] = count++;
                            break;
                        }
                    }




                }
            }
        }
    }

    private static void fillColor(int x, int y, int flow, boolean isHead) {
        if (isHead) {
            for (int dir = 0; dir < 4; dir++) {
                int nextX = x + directX[dir], nextY = y + directY[dir];
                if (isInside(nextX, nextY, width, height)) {
                    if (map[nextX][nextY] >= 0 && map[nextX][nextY] == map[x][y]) {
                        map[x][y] = flow * 100 - number++;
                        map[nextX][nextY] = flow * 100 - number++;
                        fillColor(nextX, nextY, flow, false);
                        return;
                    }
                }
            }
        } else {
            int index = random.nextInt(orderRandom.length);
            for (int i = 0; i < 4; i++) {
                int dir = orderRandom[index].charAt(i) - '0';
                int nextX = x + directX[dir], nextY = y + directY[dir];
                if (isInside(nextX, nextY, width, height)) {
                    if (map[nextX][nextY] >= 0) {
                        fillColor(nextX, nextY, flow, true);
                        return;
                    }
                }

            }
        }
    }

    public static Flow createFlowList(int x, int y) {
        Flow flow = new Flow();

        int X = x, Y = y;

        boolean conditionBreak = true;
        while (conditionBreak) {

            int numberFlow = map[X][Y];
            map[X][Y] = 1;
            flow.add(X * width + Y);
            conditionBreak = false;
            for (int dir = 0; dir < 4; dir++) {
                int nextX = X + directX[dir], nextY = Y + directY[dir];
                if (isInside(nextX, nextY, width, height)) {
                    if (map[nextX][nextY] == numberFlow - 1) {
                        X = nextX;
                        Y = nextY;
                        conditionBreak = true;
                        break;

                    }
                }
            }

        }


        return flow;
    }

    private static Pair<Integer, Integer> getOddPosition(){
        for(int i = 0 ;i < height ;i ++){
            for(int j = 0 ;j < width ; j ++){
                if(map[i][j] == -1){
                    return new Pair<>(i, j);
                }
            }
        }

        return null;
    }


    public static void main(String[] args) {

        width = scanner.nextInt();
        height = scanner.nextInt();
        initialization();
        getRandomMap(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.format("%5d", map[i][j]);
            }
            System.out.println();
        }
        int flow = -2;

        Pair<Integer, Integer> oddPosition = getOddPosition();
        if(oddPosition != null){
            int first = oddPosition.getKey();
            int second = oddPosition.getValue();
            number = 0;
            int index = random.nextInt(orderRandom.length);
            for (int i = 0; i < 4; i++) {
                int dir = orderRandom[index].charAt(i) - '0';
                int nextX = first + directX[dir], nextY = second + directY[dir];
                if (isInside(nextX, nextY, width, height)) {
                    if (map[nextX][nextY] >= 0) {
                        map[first][second] = flow*100 - number ++;
                      //  map[nextX][nextY] = flow*100 - number ++;
                        fillColor(nextX, nextY, flow, true);

                        break;

                    }
                }

            }
            flow --;

            System.out.println(oddPosition.getKey() + " " + oddPosition.getValue());
        }






        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] > -2) {
                    number = 0;
                    fillColor(i, j, flow, true);
                    flow--;
                }
            }
        }

        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.format("%5d", map[i][j]);
            }
            System.out.println();
        }

        System.out.println();

        if(oddPosition != null){
            Flow tmpflow = createFlowList(oddPosition.getKey(), oddPosition.getValue());
            if (tmpflow != null && tmpflow.getArrayList().size() > 0)
                flowList.add(tmpflow);
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] < 0) {
                    Flow tmpflow = createFlowList(i, j);
                    if (tmpflow == null || tmpflow.getArrayList().size() < 1) continue;
                    flowList.add(tmpflow);
                }

            }
        }


        for (Flow flow1 : flowList) {
            System.out.println(flow1);
        }

        List<Flow> dividedFlowList = Flow.getNewFlowList(flowList, width, height);
        System.out.println();
        for(Flow flow1 : dividedFlowList){
            System.out.println(flow1);
        }

        System.out.println();
        for(int i = 0 ;i < height ;i ++){
            for(int j  = 0 ;j < width ;j ++){
                map[i][j] = 0;
            }
        }
        int count = 0;
        for(Flow flow1 : dividedFlowList){
            int first = flow1.getHead();
            int second = flow1.getTail();
            ++count;
            map[first/width][first%width] = map[second/width][second%width] = count;

        }

        for(int i = 0 ;i < height ;i ++){
            for(int j = 0 ;j < width; j ++){
                if(map[i][j] == 0){
                    System.out.format("%5s", "-");
                }else{
                    System.out.format("%5d", map[i][j]);
                }

            }
            System.out.println();
        }

    }
}

