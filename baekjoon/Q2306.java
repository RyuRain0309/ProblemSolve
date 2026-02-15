package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2306 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] DNA = br.readLine().toCharArray();
        int N = DNA.length;
        int[][] dp = new int[N][N];

        for (int len = 1; len < N; len++) {
            for (int i = 0; i + len < N; i++) {
                int j = i + len;
                if ((DNA[i] == 'a' && DNA[j] == 't') || (DNA[i] == 'g' && DNA[j] == 'c')) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }
        System.out.println(dp[0][N - 1]);
    }
}
