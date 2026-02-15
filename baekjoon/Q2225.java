package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2225 {
    final static int DIV = 1_000_000_000;
    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K][N + 1];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < K; i++) {
            dp[i][0] = 1;
            for (int j = 0; j <= N; j++) {
                for (int k = 0; j + k <= N; k++) {
                    if (j == 0 && k == 0) continue;
                    dp[i][j + k] += dp[i - 1][j] % DIV;
                    dp[i][j + k] %= DIV;
                }
            }
        }
        System.out.println(dp[K - 1][N]);
    }
}
