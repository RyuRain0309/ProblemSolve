package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1311 {

    static int[][] map;
    static int[][] dp;
    static int N;
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][(1 << N) + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int depth, int bit) {
        if (depth == N) {
            return 0;
        }
        if (dp[depth][bit] != Integer.MAX_VALUE) {
            return dp[depth][bit];
        }
        for (int i = 0; i < N; i++) {
            if ((bit & 1 << i) != 0) {
                continue;
            }
            dp[depth][bit] = Math.min(
                    dp[depth][bit],
                    dfs(depth + 1, bit | 1 << i) + map[depth][i]);
        }
        return dp[depth][bit];
    }
}
