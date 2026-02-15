package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2629 {
    static int N;
    static int[] weight;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        dp = new boolean[N + 1][15001];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp > 15000) {
                sb.append("N").append(" ");
                continue;
            }
            if (dp[N][temp]) {
                sb.append("Y").append(" ");
            } else {
                sb.append("N").append(" ");
            }
        }
        System.out.print(sb);
    }

    private static void dfs(int cnt, int total) {
        if (dp[cnt][total]) {
            return;
        }
        dp[cnt][total] = true;
        if (cnt == N) {
            return;
        }
        dfs(cnt + 1, total + weight[cnt]);
        dfs(cnt + 1, total);
        dfs(cnt + 1, Math.abs(total - weight[cnt]));
    }
}
