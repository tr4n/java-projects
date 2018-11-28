import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static int width = 0, height = 0;
    public static int[][] map = new int[20][20];
    public static int[] directX = {0, 1};
    public static int[] directY = {1, 0};

    public static void main(String[] args) {
        System.out.println("Hello World!");
        width = scanner.nextInt();
        height = scanner.nextInt();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = -1;
            }
        }
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == -1) {
                    int k = random.nextInt(2);
                    if (map[i + directX[k]][j + directY[k]] != -1) k = 1 - k;
                    if (map[i + directX[k]][j + directY[k]] != -1) continue;
                    map[i][j] = map[i + directX[k]][j + directY[k]] = count;
                    count++;


                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.format("%5d", map[i][j]);
            }
            System.out.println();
        }


    }
}
