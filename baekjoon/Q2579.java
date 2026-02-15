package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        if (N == 1) {
            System.out.println(arr[0]);
            return;
        }
        int[][] dp = new int[N][2];
        dp[0][0] = arr[0];
        dp[1][0] = arr[1];
        dp[1][1] = dp[0][0] + arr[1];
        for (int i = 2; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 2][0] + arr[i], dp[i - 2][1] + arr[i]);
            dp[i][1] = dp[i - 1][0] + arr[i];
        }
        System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
    }
}
