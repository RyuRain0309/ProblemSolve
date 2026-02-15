package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17404 {

    static final int R = 0, G = 1, B = 2;
    static int N;
    static int[][] cost;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        dp = new int[N][3][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][R] = Integer.parseInt(st.nextToken());
            cost[i][G] = Integer.parseInt(st.nextToken());
            cost[i][B] = Integer.parseInt(st.nextToken());
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            res = Math.min(res, dfs(1, i, i) + cost[0][i]);
        }
        System.out.println(res);
    }

    private static int dfs(int depth, int pre, int first) {
        if (dp[depth][pre][first] != 0) {
            return dp[depth][pre][first];
        }

        if (depth == N - 1) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                if (i == pre) continue;
                if (i == first) continue;
                min = Math.min(min, cost[depth][i]);
            }
            return dp[depth][pre][first] = min;
        }

        dp[depth][pre][first] = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i == pre) {
                continue;
            }
            dp[depth][pre][first] = Math.min(dp[depth][pre][first],
                    dfs(depth + 1, i, first) + cost[depth][i]);
        }
        return dp[depth][pre][first];
    }
}
