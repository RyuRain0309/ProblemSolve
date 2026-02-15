package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N + 1][2];
        int[][] dp = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N; i++) {
            dp[i][i + 1] = matrix[i][0] * matrix[i + 1][0] * matrix[i + 1][1];
        }
        for (int n = 2; n <= N; n++) {
            for (int start = 1; start + n <= N; start++) {
                int des = start + n;
                dp[start][des] = Integer.MAX_VALUE;
                for (int div = start; div < des; div++) {
                    dp[start][des] = Math.min(dp[start][des], dp[start][div] + dp[div + 1][des] +
                            (matrix[start][0] * matrix[div][1] * matrix[des][1]));
                }
            }
        }
        System.out.print(dp[1][N]);
    }
}
