package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2293 {
    static int N;
    static int K;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];
        int temp;
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            temp = Integer.parseInt(br.readLine());
            for (int j = temp; j <= K; j++) {
                dp[j] += dp[j - temp];
            }
        }
        System.out.print(dp[K]);
    }
}
