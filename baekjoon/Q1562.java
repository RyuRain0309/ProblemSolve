package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1562 {

    static final int[] bit = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N][10][2048];
        for (int i = 1; i < 10; i++) {
            dp[0][i][bit[i]] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    int tBit = bit[j] | k;
                    if (j != 0) {
                        dp[i][j][tBit] += dp[i - 1][j - 1][k] % MOD;
                        dp[i][j][tBit] %= MOD;
                    }
                    if (j != 9) {
                        dp[i][j][tBit] += dp[i - 1][j + 1][k] % MOD;
                        dp[i][j][tBit] %= MOD;
                    }
                }
            }
        }
        long res = 0L;
        for (int i = 0; i < 10; i++) {
            res += dp[N - 1][i][1023] % MOD;
            res %= MOD;
        }
        System.out.println(res);
    }
}
