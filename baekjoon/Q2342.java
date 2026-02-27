package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Q2342 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] dp = new int[5][5];
        int[][] newDp = new int[5][5];

        for (int i = 0; i < 5; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        dp[0][N] = 2;
        dp[N][0] = 2;
        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            for (int i = 0; i < 5; i++) {
                Arrays.fill(newDp[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (dp[i][j] == Integer.MAX_VALUE) continue;
                    if (n != j) {
                        newDp[n][j] = Math.min(newDp[n][j], dp[i][j] + getCost(i, n));
                    }
                    if (n != i) {
                        newDp[i][n] = Math.min(newDp[i][n], dp[i][j] + getCost(j, n));
                    }
                }
            }

            int[][] temp = dp;
            dp = newDp;
            newDp = temp;

        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, dp[i][j]);
            }
        }

        System.out.print(ans);

    }

    private static int getCost(int i, int j) {
        if (i == 0) return 2;
        if (i == j) return 1;
        if (Math.abs(i - j) == 2) return 4;
        return 3;
    }
}
