package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2229 {
    static int N;
    static int[] dp;
    static int[][] max, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max = new int[N][N];
        min = new int[N][N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int score = Integer.parseInt(st.nextToken());
            max[i][i] = score;
            min[i][i] = score;
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                max[i][j] = Math.max(max[i][j - 1], max[j][j]);
                min[i][j] = Math.min(min[i][j - 1], min[j][j]);
            }
        }

        for (int i = 1; i < N; i++) {
            dp[i] = max[0][i] - min[0][i];
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + max[j + 1][i] - min[j + 1][i]);
            }
        }

        System.out.println(dp[N - 1]);
    }

}
