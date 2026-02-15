package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11052 {
    static int N;
    static int[] price;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        price = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
        dp = price.clone();
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + dp[j]);
            }
        }

        System.out.println(dp[N]);
    }
}
