package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1256 {
    static StringBuilder sb = new StringBuilder();
    static final int MAX = 1_000_000_000;
    static int N, M, K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= M; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (dp[i][j] > MAX) {
                    dp[i][j] = MAX;
                }
            }
        }
        if (K > dp[N][M]) {
            System.out.println(-1);
            return;
        }
        recur(N, M, K);
        System.out.println(sb);
    }

    private static void recur(int a, int z, int k) {
        if (a == 0) {
            sb.append("z".repeat(z));
            return;
        }
        if (z == 0) {
            sb.append("a".repeat(a));
            return;
        }
        if (k <= dp[a - 1][z]) {
            sb.append("a");
            recur(a - 1, z, k);
        } else {
            sb.append("z");
            recur(a, z - 1, k - dp[a - 1][z]);
        }
    }
}
