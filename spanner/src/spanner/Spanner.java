package spanner;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spanner {
    public static Random random = new Random();
    private static Map map;
    private static Pair<Integer, Integer> specialPosition;
    public static int[] pointNumber = {0};
    private static List<Flow> flowList = new ArrayList<>();
    private static int flowNumber = -2;


    private static void initialization(int WIDTH, int HEIGHT) {
        map = new Map(new int[20][20], WIDTH, HEIGHT);
        Utils.initializeRandomMap(map);
        specialPosition = map.getSpecialPosition();

     //   map.printMap("after-init");



    }

    private static void executeSpeicalPosition(int actionNumber) {
        if (specialPosition == null) return;
        if (actionNumber == Utils.FILL_COLOR) {
            flowNumber = -2;

            int first = specialPosition.getKey();
            int second = specialPosition.getValue();

            pointNumber[0] = 0;
            int index = random.nextInt(Utils.RANDOM_DIRECTS.length);
            for (int i = 0; i < 4; i++) {
                int dir = Utils.RANDOM_DIRECTS[index].charAt(i) - '0';
                int nextX = first + Utils.DIRECT_X[dir], nextY = second + Utils.DIRECT_Y[dir];
                if (Utils.isInsideMap(nextX, nextY, map)) {
                    if (map.table[nextX][nextY] >= 0) {
                        map.table[first][second] = flowNumber * 100 - pointNumber[0]++;
                        //  map[nextX][nextY] = flow*100 - number ++;
                        Utils.fillColor(map, nextX, nextY, flowNumber, true, pointNumber);
                        break;

                    }


                }


            }
            flowNumber --;
        } else {
            Flow tmpflow = Utils.createFlowList(map, specialPosition.getKey(), specialPosition.getValue());
            if (tmpflow != null && tmpflow.length() > 0)
                flowList.add(tmpflow);
        }
    }

    public static void getTargetMap(int width, int height) {
        initialization(width, height);
        executeSpeicalPosition(Utils.FILL_COLOR);

        for (int line = 0; line < height; line++) {
            for (int col = 0; col < width; col++) {
                if (map.table[line][col] > -2) {
                    pointNumber[0] = 0;
                    Utils.fillColor(map, line, col, flowNumber, true, pointNumber);
                    flowNumber--;
                }
            }
        }
     //   map.printMap("after-fill-color");

        executeSpeicalPosition(Utils.CREATE_FLOW);
        for (int line = 0; line < height; line++) {
            for (int col = 0; col < width; col++) {
                if (map.table[line][col] < 0) {
                    Flow tmpflow = Utils.createFlowList(map, line, col);
                    if (tmpflow == null || tmpflow.length() < 1) continue;
                    flowList.add(tmpflow);
                }

            }
        }
        /*
        for(Flow flow: flowList){
            System.out.println(flow);
        }
*/
        System.out.println();
        List<Flow> targetFlowList = Flow.getTargetFlowList(flowList, map.getNumberFlows(flowList.size()));
        /*
        for(Flow flow: targetFlowList){
            System.out.println(flow);
        }
*/
        Utils.printTargetMap(map, targetFlowList);

        try {
            Utils.writeToFile(map, targetFlowList, "output/output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
