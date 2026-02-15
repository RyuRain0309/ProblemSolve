package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2447 {
    static boolean[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new boolean[N + 1][N + 1];
        recue(N, false, 1, 1);
        System.out.print(sb);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (arr[i][j]) sb.append(" ");
                else sb.append("*");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void recue(int n, boolean blank, int y, int x) {
        if (n == 1) {
            arr[y][x] = blank;
            return;
        }
        for (int i = 0; i < 3; i++) {
            recue(n / 3, blank, y, x + (n / 3) * i);
        }
        recue(n / 3, blank, y + n / 3, x);
        recue(n / 3, true, y + n / 3, x + n / 3);
        recue(n / 3, blank, y + n / 3, x + (n / 3) * 2);
        for (int i = 0; i < 3; i++) {
            recue(n / 3, blank, y + (n / 3) * 2, x + (n / 3) * i);
        }
    }
}
