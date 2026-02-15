package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q9184 {
    static int[][][] map = new int[21][21][21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        mapSet();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == b && b == c && c == -1) {
                break;
            }
            int val = w(a, b, c);
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(val).append("\n");
        }
        System.out.print(sb);
    }

    private static int w(int a, int b, int c) {
        if (isPossi(a, b, c) && map[a][b][c] != -1) {
            return map[a][b][c];
        }

        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }
        if (a > 20 || b > 20 || c > 20) {
            return map[20][20][20] = w(20, 20, 20);
        }
        if (a < b && b < c) {
            return map[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        }
        return map[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }

    private static boolean isPossi(int a, int b, int c) {
        return a >= 0 && a <= 20 && b >= 0 && b <= 20 && c >= 0 && c <= 20;
    }

    private static void mapSet() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                Arrays.fill(map[i][j], -1);
            }
        }
    }
}
