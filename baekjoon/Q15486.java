package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] dp = new int[N + 1];
        int[] t = new int[N];
        int[] p = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int preMax = 0;
        for (int i = 0; i < N; i++) {
            preMax = Math.max(preMax, dp[i]);
            dp[i] = preMax;
            if (i + t[i] > N) {
                continue;
            }
            dp[i + t[i]] = Math.max(dp[i + t[i]], preMax + p[i]);
        }
        System.out.println(Math.max(dp[N], preMax));
    }
}
