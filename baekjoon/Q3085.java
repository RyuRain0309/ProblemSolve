package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q3085 {
    static char[][] map;
    static int N;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            checkMaxCandyRow(i);
            checkMaxCandyColumn(i);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                change(i, j);
            }
        }
        System.out.print(res);
    }

    private static void change(int x, int y) {
        if (x + 1 < N) {
            swap(x, y, x + 1, y);
            checkMaxCandyRow(x);
            checkMaxCandyRow(x + 1);
            checkMaxCandyColumn(y);
            swap(x, y, x + 1, y);
        }
        if (y + 1 < N) {
            swap(x, y, x, y + 1);
            checkMaxCandyRow(x);
            checkMaxCandyColumn(y);
            checkMaxCandyColumn(y + 1);
            swap(x, y, x, y + 1);
        }
    }

    private static void checkMaxCandyRow(int pos) {
        char preCandy = map[pos][0];
        int tempRes = 1;
        for (int i = 1; i < N; i++) {
            if (preCandy != map[pos][i]) {
                res = Math.max(res, tempRes);
                tempRes = 1;
                preCandy = map[pos][i];
            } else {
                tempRes++;
            }
        }
        res = Math.max(res, tempRes);
    }

    private static void checkMaxCandyColumn(int pos) {
        char preCandy = map[0][pos];
        int tempRes = 1;
        for (int i = 1; i < N; i++) {
            if (preCandy != map[i][pos]) {
                res = Math.max(res, tempRes);
                tempRes = 1;
                preCandy = map[i][pos];
            } else {
                tempRes++;
            }
        }
        res = Math.max(res, tempRes);
    }

    private static void swap(int x, int y, int tempX, int tempY) {
        char tempValue = map[x][y];
        map[x][y] = map[tempX][tempY];
        map[tempX][tempY] = tempValue;
    }
}
