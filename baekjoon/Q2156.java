package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q2156 {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        int wine = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 0);
        }
        dp[0][1] = wine;
        for (int i = 1; i < N; i++) {
            wine = Integer.parseInt(br.readLine());
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][1] = dp[i - 1][0] + wine;
            dp[i][2] = dp[i - 1][1] + wine;
        }
        System.out.print(Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2])));
    }

}
