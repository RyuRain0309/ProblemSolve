package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10830 {
    final static int div = 1000;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        long B = Long.parseLong(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] %= div;
            }
        }
        StringBuilder sb = new StringBuilder();

        int[][] res = divide(map, B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(res[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static int[][] divide(int[][] map, long B) {
        if (B == 1L) {
            return map;
        }
        int[][] res = divide(map, B / 2);

        res = matrixMulti(res, res);
        if (B % 2 == 1L) {
            res = matrixMulti(res, map);
        }
        return res;
    }

    private static int[][] matrixMulti(int[][] A, int[][] B) {
        int[][] res = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                    res[i][j] %= div;
                }
            }
        }
        return res;
    }
}
