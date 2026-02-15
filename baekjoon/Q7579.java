package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q7579 {
    static int N;
    static int M;
    static int[] memory;
    static int[] cost;
    static int[][] dp;
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memory = new int[N];
        cost = new int[N];
        int costSum = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            costSum += cost[i];
        }
        dp = new int[N][costSum + 1];
        for (int i = cost[0]; i <= costSum; i++) {
            dp[0][i] = memory[0];
            if (dp[0][i] >= M) {
                res = Math.min(res, i);
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= costSum; j++) {
                if (j < cost[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                }

                if (dp[i][j] >= M) {

                    res = Math.min(res, j);
                }
            }
        }
        System.out.print(res);
    }
}
