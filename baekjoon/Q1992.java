package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1992 {
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        divide(0, 0, N);
        System.out.println(sb);
    }

    private static void divide(int y, int x, int size) {
        int std = map[y][x];
        boolean pass = true;
        a:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[y + i][x + j] != std) {
                    pass = false;
                    break a;
                }
            }
        }
        if (pass) {
            sb.append(std);
        } else {
            size /= 2;
            sb.append("(");
            divide(y, x, size);
            divide(y, x + size, size);
            divide(y + size, x, size);
            divide(y + size, x + size, size);
            sb.append(")");
        }
    }
}
