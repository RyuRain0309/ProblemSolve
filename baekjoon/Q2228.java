package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2228 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[][] dp = new int[M][N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        if (N == 1) {
            System.out.println(arr[0]);
            return;
        }
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -2_000_000);
        }
        dp[0][0] = arr[0];
        dp[0][1] = Math.max(arr[1], arr[0] + arr[1]);
        for (int i = 2; i < N; i++) {
            dp[0][i] = Math.max(arr[i], dp[0][i - 1] + arr[i]);
            for (int j = 1; j < M; j++) {
                if (j > i / 2) {
                    break;
                }
                dp[j][i] = dp[j][i - 1] + arr[i];
                for (int k = (j - 1) * 2; k < i - 1; k++) {
                    dp[j][i] = Math.max(dp[j][i], dp[j - 1][k] + arr[i]);
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = (M - 1) * 2; i < N; i++) {
            res = Math.max(res, dp[M - 1][i]);
        }
        System.out.println(res);
    }
}
