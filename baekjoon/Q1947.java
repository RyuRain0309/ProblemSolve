package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1947 {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }
        long[] dp = new long[N + 1];
        dp[1] = 0L;
        dp[2] = 1L;
        for (int i = 3; i < N + 1; i++) {
            dp[i] = (i - 1) * ((dp[i - 1] + dp[i - 2]) % MOD) % MOD;
        }
        System.out.println(dp[N]);
    }
}
