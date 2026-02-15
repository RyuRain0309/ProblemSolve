package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2749 {

    static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Solution solution = new Solution();
        long n = Long.parseLong(br.readLine());
        System.out.println(solution.solution(n));
    }

    static class Solution {
        int solution(long n) {
            int[] dp = new int[1_500_000];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i < 1_500_000; i++) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
            }
            int r = (int) (n % 1_500_000);
            return dp[r];
        }
    }
}
