package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q15990 {

    final static long MOD = 1_000_000_009L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[100_001][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i < 100_001; i++) {
            dp[i][1] = ((dp[i - 1][2] % MOD) + (dp[i - 1][3] % MOD)) % MOD;
            dp[i][2] = ((dp[i - 2][1] % MOD) + (dp[i - 2][3] % MOD)) % MOD;
            dp[i][3] = ((dp[i - 3][1] % MOD) + (dp[i - 3][2] % MOD)) % MOD;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            long sum = ((dp[temp][1] % MOD) + (dp[temp][2] % MOD) + (dp[temp][3] % MOD)) % MOD;
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
