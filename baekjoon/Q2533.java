package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2533 {
    static ArrayList<ArrayList<Integer>> node = new ArrayList<>();
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            node.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            node.get(a).add(b);
            node.get(b).add(a);
        }

        dfs(1, 0);

        System.out.print(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int pos, int pre) {
        dp[pos][0] = 0;
        dp[pos][1] = 1;

        for (int i : node.get(pos)) {
            if (i == pre) continue;
            dfs(i, pos);
            dp[pos][0] += dp[i][1];
            dp[pos][1] += Math.min(dp[i][0], dp[i][1]);
        }
    }
}
