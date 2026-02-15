package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[10_001][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i < 10_001; i++) {
            dp[i][1] = 1;
            dp[i][2] = 1 + dp[i - 2][2];
            dp[i][3] = 1 + dp[i - 3][2] + dp[i - 3][3];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            int sum = dp[temp][1] + dp[temp][2] + dp[temp][3];
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
