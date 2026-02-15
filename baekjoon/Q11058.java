package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N < 5 ? 6 : N + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        dp[5] = 5;
        for (int i = 6; i <= N; i++) {
            for (int j = i - 3; j > 0; j--) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }
        System.out.println(dp[N]);
    }
}
