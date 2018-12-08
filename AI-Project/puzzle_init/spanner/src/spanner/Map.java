package spanner;

import javafx.util.Pair;

import java.util.Random;

public class Map {
    public  int[][] table = new int[20][20];
    public  int width  = 5;
    public  int height = 5;
    public  int numberFlows = 5;

    public Map(int[][] table, int width, int height) {
        this.table = table;
        this.width = width;
        this.height = height;
        this.numberFlows = (int) Math.sqrt(width * height);
    }

    public int getNumberFlows(int minFlows){
        int maxFlows = (int) Math.sqrt(width * height);
        if(minFlows > maxFlows) return minFlows;
        this.numberFlows = Math.max(minFlows,(new Random()).nextInt((int ) (maxFlows*1.5) + 1) );

        if(numberFlows > maxFlows) numberFlows = maxFlows;


        return numberFlows;
    }

    public  Pair<Integer, Integer> getSpecialPosition(){
        for(int line = 0 ;line < height ;line ++){
            for(int col = 0 ;col < width ; col ++){
                if(table[line][col] == -1){
                    return new Pair<>(line, col);
                }
            }
        }

        return null;
    }

    public void printMap(String title){
        System.out.println("-----" + title +"---------");
        for(int i = 0 ;i < height ;i ++){
            for(int j = 0 ;j < width ;j ++){
                System.out.format("%5d",table[i][j]);
            }
            System.out.println();
        }
    }

}
