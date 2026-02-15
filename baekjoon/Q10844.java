package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q10844 {
    static long[][] dp;
    final static long mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[N][10];
        Arrays.fill(dp[0], 1L);
        dp[0][0] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][1] % mod;
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][8] % mod;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
            }
        }
        long res = 0;
        for (int i = 0; i < 10; i++) {
            res += dp[N - 1][i];
        }
        System.out.print(res % mod);
    }
}
