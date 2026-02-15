package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2580 {

    static int[][] map = new int[9][9];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backTracking(0);
    }

    private static void backTracking(int n) {
        if (n == 81) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
            System.exit(0);
            return;
        }
        while (map[n / 9][n % 9] != 0) {
            n++; // 0이 나올때까지
            if (n == 81) {
                backTracking(n);
                return;
            }
        }
        int y = n / 9;
        int x = n % 9;
        for (int i = 1; i < 10; i++) {
            if (promising(n, i)) {
                map[y][x] = i;
                backTracking(n + 1);
                map[y][x] = 0;
            }
        }
    }

    private static boolean promising(int n, int num) {
        int y = n / 9;
        int x = n % 9;
        int yPos = y / 3;
        int xPos = x / 3;
        for (int i = 0; i < 9; i++) {
            if (map[y][i] == num) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (map[i][x] == num) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[yPos * 3 + i][xPos * 3 + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
