package spanner;

import java.io.*;
import java.util.List;
import java.util.Random;

public class Utils {

    public static final int FILL_COLOR = 1432;
    public static final int CREATE_FLOW = 1234;
    private static Random random = new Random();
    public static final String[] RANDOM_DIRECTS = {"1230", "2301", "3012", "0123", "1032", "2103", "3021", "0213", "1320"};
    public  static final int[] DIRECT_X = {0, -1, 0, 1};
    public  static final int[] DIRECT_Y = {1, 0, -1, 0};


    public static boolean isInsideMap(int x, int y,Map map) {
        return (0 <= x && x < map.height && 0 <= y && y < map.width);
    }
 


    public static void initializeRandomMap(Map map) {
       
        for (int line = 0; line < map.height; line++) {
            for (int column = 0; column < map.width; column++) {
                map.table[line][column] = -1;
            }
        }

        int dominoNumber = 0;

        for (int line = 0; line < map.height; line++) {
            for (int col = 0; col < map.width; col++) {
                if (map.table[line][col] == -1) {
                    if (line == map.height - 1 && col > 1 && map.table[line][col - 1] == -1) {
                        map.table[line][col] = map.table[line][col - 1] = dominoNumber++;
                    } else if (col == map.width - 1 && line > 1 && map.table[line - 1][col] == -1) {
                        map.table[line][col] = map.table[line - 1][col] = dominoNumber++;
                    } else {
                        int k = random.nextInt(RANDOM_DIRECTS.length);
                        for (int index = 0; index < 4; index++) {
                            int dir = (int) (RANDOM_DIRECTS[k].charAt(index) - '0');
                            int checkX = line + DIRECT_X[dir], checkY = col + DIRECT_Y[dir];
                            if (!isInsideMap(checkX, checkY, map)) continue;
                            if (map.table[checkX][checkY] != -1) continue;
                            map.table[line][col] = map.table[checkX][checkY] = dominoNumber++;
                            break;
                        }
                    }
                }
            }
        }

    }

    public static Flow createFlowList(Map map, int x, int y) {
        Flow flow = new Flow();
        int tmpX = x, tmpY = y;
        boolean isEndOfPoint = false;
        while (!isEndOfPoint) {

            int numberFlow = map.table[tmpX][tmpY];
            map.table[tmpX][tmpY] = 1;
            flow.add(tmpX * map.width + tmpY);
            isEndOfPoint = true;
            for (int dir = 0; dir < 4; dir++) {
                int nextX = tmpX + DIRECT_X[dir], nextY = tmpY + DIRECT_Y[dir];
                if (isInsideMap(nextX, nextY, map)) {
                    if (map.table[nextX][nextY] == numberFlow - 1) {
                        tmpX = nextX;
                        tmpY = nextY;
                        isEndOfPoint = false;
                        break;

                    }
                }
            }

        }
        return flow;
    }



    public static void fillColor(Map map, int x, int y, int flowNumber, boolean isNewDomino, int[] pointNumber) {
        if (isNewDomino) {
            for (int dir = 0; dir < 4; dir++) {
                int nextX = x + DIRECT_X[dir], nextY = y + DIRECT_Y[dir];
                if (isInsideMap(nextX, nextY, map)) {
                    if (map.table[nextX][nextY] >= 0 && map.table[nextX][nextY] == map.table[x][y]) {
                        map.table[x][y] = flowNumber * 100 - pointNumber[0]++;
                        map.table[nextX][nextY] = flowNumber * 100 - pointNumber[0]++;
                        fillColor(map, nextX, nextY, flowNumber, false, pointNumber);
                        return;
                    }
                }
            }
        } else {
            int index = random.nextInt(RANDOM_DIRECTS.length);
            for (int i = 0; i < 4; i++) {
                int dir = RANDOM_DIRECTS[index].charAt(i) - '0';
                int nextX = x + DIRECT_X[dir], nextY = y + DIRECT_Y[dir];
                if (isInsideMap(nextX, nextY, map)) {
                    if (map.table[nextX][nextY] >= 0) {
                        fillColor(map, nextX, nextY, flowNumber, true, pointNumber);
                        return;
                    }
                }

            }
        }
    }
    public static void printTargetMap(Map map, List<Flow>flowList){
        for(int i = 0 ;i < map.height; i ++){
            for(int j = 0 ;j < map.width ;j ++){
                map.table[i][j] = 0;
            }
        }

        int count = 0;
        for (Flow flow : flowList) {
            int first = flow.getHead();
            int second = flow.getTail();
            ++count;
            map.table[first / map.width][first % map.width] = map.table[second / map.width][second % map.width] = count;

        }

        System.out.println("-----------final map---------------");
        for(int i = 0 ;i < map.height ;i ++){
            for(int j = 0 ;j < map.width; j ++){
                if(map.table[i][j] == 0){
                    System.out.format("%5s", "-");
                }else{
                    System.out.format("%5d", map.table[i][j]);
                }

            }
            System.out.println();
        }

    }

    public static void writeToFile(Map map, List<Flow> flowList,  String path) throws IOException {


        File fout = new File(path);
        if(!fout.exists()){
            fout.getParentFile().mkdirs();
            fout.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for(int i = 0 ;i < map.height; i ++){
            for(int j = 0 ;j < map.width ;j ++){
                map.table[i][j] = 0;
            }
        }

        int count = 0;
        for (Flow flow : flowList) {
            int first = flow.getHead();
            int second = flow.getTail();
            ++count;
            map.table[first / map.width][first % map.width] = map.table[second / map.width][second % map.width] = count;

        }


        for(int i = 0 ;i < map.height ;i ++){
            for(int j = 0 ;j < map.width; j ++){
                if(map.table[i][j] == 0){
                    bw.write(".");
                }else{
                    bw.write((char)(map.table[i][j] + 'A' -1));
                }

            }
            bw.write("\n");
        }

        bw.close();
    }



}
