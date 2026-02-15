package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2281 {

    static int M, N;
    static int[][] dp;
    static int[] name;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[M + 1][N];
        for (int i = 0; i <= M; i++) {
            Arrays.fill(dp[i], -1);
        }
        name = new int[N];
        for (int i = 0; i < N; i++) {
            name[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int depth, int len) {
        if (depth >= N) {
            return 0;
        }

        if (dp[len][depth] != -1) {
            return dp[len][depth];
        }

        if (len == 0) {
            return dp[len][depth] = dfs(depth + 1, name[depth]);
        }

        int newLine, append = Integer.MAX_VALUE;
        int appendLength = len + 1 + name[depth];

        if (appendLength <= M) {
            append = dfs(depth + 1, appendLength);
        }

        if (dp[0][depth] == -1) {
            int newVal = (M - len) * (M - len);
            newLine = dfs(depth + 1, name[depth]) + newVal;
        } else {
            newLine = dp[0][depth];
        }
        dp[len][depth] = Math.min(newLine, append);
        return dp[len][depth];
    }
}
