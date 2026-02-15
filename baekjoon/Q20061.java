package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20061 {

    static int point = 0;

    static boolean[][] blue = new boolean[4][6];
    static boolean[][] green = new boolean[6][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            block(t, x, y);
            getPoint();
            special();
        }
        int res = 0;
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[j][i]) {
                    res++;
                }
                if (green[i][j]) {
                    res++;
                }
            }
        }
        System.out.println(point + "\n" + res);
    }

    private static void special() {
        int blueCount = 0;
        int greenCount = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[j][i]) {
                    blueCount++;
                    break;
                }
            }
            for (int j = 0; j < 4; j++) {
                if (green[i][j]) {
                    greenCount++;
                    break;
                }
            }
        }
        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = blue[j][i - blueCount];
                green[i][j] = green[i - greenCount][j];
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = false;
                green[i][j] = false;
            }
        }
    }

    private static void getPoint() {
        for (int i = 2; i <= 5; i++) {
            boolean blueFlag = true;
            boolean greenFlag = true;
            for (int j = 0; j < 4; j++) {
                if (!blue[j][i]) {
                    blueFlag = false;
                }
                if (!green[i][j]) {
                    greenFlag = false;
                }
            }
            if (blueFlag) {
                for (int j = i; j > 0; j--) {
                    for (int k = 0; k < 4; k++) {
                        blue[k][j] = blue[k][j - 1];
                    }
                }
                for (int k = 0; k < 4; k++) {
                    blue[k][0] = false;
                }
                point++;
            }
            if (greenFlag) {
                for (int j = i; j > 0; j--) {
                    for (int k = 0; k < 4; k++) {
                        green[j][k] = green[j - 1][k];
                    }
                }
                for (int k = 0; k < 4; k++) {
                    green[0][k] = false;
                }
                point++;
            }
        }
    }

    private static void block(int t, int x, int y) {
        if (t == 1) {
            soloBlue(x);
            soloGreen(y);
        }
        if (t == 2) {
            soloBlue(x);
            soloBlue(x);
            doubleGreen(y);
        }
        if (t == 3) {
            doubleBlue(x);
            soloGreen(y);
            soloGreen(y);
        }
    }

    private static void soloBlue(int x) {
        int placeY = 0;
        for (int i = 0; i <= 5; i++) {
            if (blue[x][i]) {
                break;
            }
            placeY = i;
        }
        blue[x][placeY] = true;
    }

    private static void doubleBlue(int x) {
        int placeY = 0;
        for (int i = 0; i <= 5; i++) {
            if (blue[x][i] || blue[x + 1][i]) {
                break;
            }
            placeY = i;
        }
        blue[x][placeY] = true;
        blue[x + 1][placeY] = true;
    }

    private static void soloGreen(int y) {
        int placeX = 0;
        for (int i = 0; i <= 5; i++) {
            if (green[i][y]) {
                break;
            }
            placeX = i;
        }
        green[placeX][y] = true;
    }

    private static void doubleGreen(int y) {
        int placeX = 0;
        for (int i = 0; i <= 5; i++) {
            if (green[i][y] || green[i][y + 1]) {
                break;
            }
            placeX = i;
        }
        green[placeX][y] = true;
        green[placeX][y + 1] = true;
    }
}
