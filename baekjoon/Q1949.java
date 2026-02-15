package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1949 {
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> node = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            node.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            dp[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            node.get(a).add(b);
            node.get(b).add(a);
        }

        dfs(1, 0);

        System.out.print(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int pos, int pre) {
        for (int i : node.get(pos)) {
            if (i == pre) continue;
            dfs(i, pos);

            dp[pos][0] += Math.max(dp[i][0], dp[i][1]);
            dp[pos][1] += dp[i][0];
        }

    }
}
