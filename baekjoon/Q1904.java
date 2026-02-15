package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1904 {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N];
        dp[0] = 1;
        if (N != 1) {
            dp[1] = 2;
        }
        for (int i = 2; i < N; i++) {
            tile(i);
        }
        System.out.print(dp[N - 1]);
    }

    private static void tile(int n) {
        dp[n] = (dp[n - 2] + dp[n - 1]) % 15746;
    }
}