package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Q1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[501][501];
        Vector<Vector<Integer>> v = new Vector<>();
        for (int i = 0; i < N; i++) {
            Vector<Integer> tempV = new Vector<>();
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j <= i; j++) {
                tempV.add(Integer.parseInt(st.nextToken()));
            }
            v.add(tempV);
        }

        for (int i = 0; i < N; i++) {
            dp[N - 1][i] = v.get(N - 1).get(i);
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + v.get(i).get(j);
            }
        }
        System.out.print(dp[0][0]);

    }
}
