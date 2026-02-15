package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        long[][] dp = new long[N][N];
        dp[0][0] = 1L;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    continue;
                }
                if (i + arr[i][j] < N) {
                    dp[i + arr[i][j]][j] += dp[i][j];
                }
                if (j + arr[i][j] < N) {
                    dp[i][j + arr[i][j]] += dp[i][j];
                }
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }
}
