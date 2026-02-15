package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[][] dp = new int[N][2];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int n = N - 1;
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
            dp[n - i][1] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i][0] <= dp[j][0]) {
                    dp[i][0] = dp[j][0] + 1;
                }
                if (arr[n - i] > arr[n - j] && dp[n - i][1] <= dp[n - j][1]) {
                    dp[n - i][1] = dp[n - j][1] + 1;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (max < dp[i][0] + dp[i][1]) {
                max = dp[i][0] + dp[i][1];
            }
        }
        System.out.print(max - 1);
    }
}