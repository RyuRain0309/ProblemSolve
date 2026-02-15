package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12865 {
    final static int WEIGHT = 0;
    final static int VALUE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][2];
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= K; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (arr[i - 1][WEIGHT] <= j) {
                    dp[i][j] = Math.max(arr[i - 1][VALUE] + dp[i - 1][j - arr[i - 1][WEIGHT]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.print(dp[N][K]);
    }
}
