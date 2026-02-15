package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1520 {
    static int[] moveX = {1, 0, -1, 0};
    static int[] moveY = {0, -1, 0, 1};
    static int M;
    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M + 1][N + 1];
        dp = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.print(dfs(1, 1));
    }

    private static int dfs(int y, int x) {
        if (y == M && x == N) {
            return 1;
        }
        if (dp[y][x] != -1) {
            return dp[y][x];
        }
        dp[y][x] = 0;
        for (int op = 0; op < 4; op++) {
            int tempY = y + moveY[op];
            int tempX = x + moveX[op];
            if (tempY < 1 || tempX < 1 || tempY > M || tempX > N) {
                continue;
            }
            if (map[y][x] > map[tempY][tempX]) {
                dp[y][x] += dfs(tempY, tempX);
            }
        }

        return dp[y][x];
    }
}