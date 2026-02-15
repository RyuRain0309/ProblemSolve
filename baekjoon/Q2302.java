package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2302 {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[41];
        setDp();
        int M = Integer.parseInt(br.readLine());
        int res = 1;
        int start = 1;
        for (int i = 0; i < M; i++) {
            int fix = Integer.parseInt(br.readLine());
            res *= dp[fix - start];
            start = fix + 1;
        }
        res *= dp[(N + 1) - start];
        System.out.println(res);
    }

    public static void setDp() {
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i < 41; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
    }
}
