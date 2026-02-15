package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= Math.max(s1.length(), s2.length()); i++) {
            if (i <= s1.length()) {
                dp[i][0] = 0;
            }
            if (i <= s2.length()) {
                dp[0][i] = 0;
            }
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
//        for (int i = 0; i <= s1.length(); i++) {
//            for (int j = 0; j <= s2.length(); j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        StringBuilder sb = new StringBuilder();
        int y = s1.length();
        int x = s2.length();
        while (y > 0 || x > 0) {
            if (x > 0 && dp[y][x] == dp[y][x - 1]) {
                x--;
            } else if (y > 0 && dp[y][x] == dp[y - 1][x]) {
                y--;
            } else {
                x--;
                y--;
                sb.insert(0, s2.charAt(x));
            }
        }
        sb.insert(0, "\n");
        sb.insert(0, dp[s1.length()][s2.length()]);
        System.out.print(sb);
    }
}
