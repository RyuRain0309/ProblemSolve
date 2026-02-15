package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14391 {
    static int N, M;
    static int[][] map;
    static boolean[][] isRow;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isRow = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        dfs(0);
        System.out.println(ans);
    }

    private static void dfs(int depth) {
        if (depth == (N * M)) {
            calc();
            return;
        }
        int y = depth / M;
        int x = depth % M;

        dfs(depth + 1);
        isRow[y][x] = true;
        dfs(depth + 1);
        isRow[y][x] = false;
    }

    private static void calc() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            int temp = 0;
            for (int j = 0; j < M; j++) {
                if (isRow[i][j]) {
                    temp = temp * 10 + map[i][j];
                } else {
                    sum += temp;
                    temp = 0;
                }
            }
            res += temp + sum;
        }

        for (int i = 0; i < M; i++) {
            int sum = 0;
            int temp = 0;
            for (int j = 0; j < N; j++) {
                if (!isRow[j][i]) {
                    temp = temp * 10 + map[j][i];
                } else {
                    sum += temp;
                    temp = 0;
                }
            }
            res += temp + sum;
        }
        ans = Math.max(ans, res);
    }
}
