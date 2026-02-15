package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1670 {
    static final int DIV = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(1);
            return;
        }
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[2] = 1;
        for (int i = 4; i <= N; i += 2) {
            for (int j = 0; j <= i - 2; j += 2) {
                dp[i] = (dp[i] + (dp[j] * dp[i - j - 2]) % DIV) % DIV;
            }
        }
        System.out.println(dp[N]);
    }
}
