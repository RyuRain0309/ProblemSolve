package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q15991 {

    final static long MOD = 1_000_000_009L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[100_001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        dp[5] = 3;
        dp[6] = 6;

        for (int i = 7; i < 100_001; i++) {
            dp[i] = ((dp[i - 2] % MOD) + (dp[i - 4] % MOD) + (dp[i - 6] % MOD)) % MOD;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            sb.append(dp[temp]).append("\n");
        }
        System.out.println(sb);
    }
}
