package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2011 {
    static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cipher = br.readLine();
        if (cipher.length() == 1) {
            if (cipher.charAt(0) == '0') {
                System.out.println(0);
            } else {
                System.out.println(1);
            }
            return;
        }

        int[] dp = new int[cipher.length() + 1];
        dp[0] = 1;
        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) == '0') {
                continue;
            }

            dp[i + 1] = (dp[i + 1] + dp[i]) % MOD;
            if (i == cipher.length() - 1) {
                continue;
            }
            if (cipher.charAt(i) == '1') {
                dp[i + 2] = (dp[i + 2] + dp[i]) % MOD;
            } else if (cipher.charAt(i) == '2' && cipher.charAt(i + 1) <= '6') {
                dp[i + 2] = (dp[i + 2] + dp[i]) % MOD;
            }
        }
        System.out.println(dp[cipher.length()]);
    }
}
