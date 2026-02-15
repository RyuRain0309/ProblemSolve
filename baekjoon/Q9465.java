package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr;
        int[][] dp;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            dp = new int[N][2];
            arr = new int[N][2];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < N; i++) {
                dp[i][0] = arr[i][0];
                dp[i][1] = arr[i][1];
            }
            dp[0][0] = arr[0][0];
            dp[0][1] = arr[0][1];
            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.max(dp[i - 1][1] + arr[i][0], dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 1][0] + arr[i][1], dp[i - 1][1]);
            }
            sb.append(Math.max(dp[N - 1][0], dp[N - 1][1])).append("\n");
        }
        System.out.print(sb);
    }
}
