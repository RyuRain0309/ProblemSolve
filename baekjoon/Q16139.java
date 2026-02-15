package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16139 {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        dp = new int[26][s.length()];
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        dp[s.charAt(0) - 'a'][0]++;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                dp[j][i] = dp[j][i - 1];
            }
            dp[s.charAt(i) - 'a'][i]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char a = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (start == 0) {
                sb.append(dp[a - 'a'][end]).append("\n");
            } else {
                sb.append(dp[a - 'a'][end] - dp[a - 'a'][start - 1]).append("\n");
            }
        }
        System.out.print(sb);
    }
}
