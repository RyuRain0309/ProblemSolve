package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q2213 {
    static int[] weight;
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
    static PriorityQueue<Integer> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        weight = new int[N + 1];
        dp = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        dfs(1, 0, 0);

//        for (int i = 1; i <= N; i++) {
//            System.out.println(dp[i][0] + " " + dp[i][1]);
//        }


        StringBuilder sb = new StringBuilder();
        if (dp[1][0] > dp[1][1]) {
            sb.append(dp[1][0]).append("\n");
            trace(1, 0, 0);
        } else {
            sb.append(dp[1][1]).append("\n");
            trace(1, 0, 1);
        }
        while (!q.isEmpty()) {
            sb.append(q.poll()).append(" ");
        }

        System.out.print(sb);
    }

    private static void trace(int pos, int pre, int inc) {
        if (inc == 1) q.add(pos);

        for (int i : edges.get(pos)) {
            if (i == pre) continue;

            if (inc == 1) trace(i, pos, 0);
            else {
                if (dp[i][0] > dp[i][1]) {
                    trace(i, pos, 0);
                } else {
                    trace(i, pos, 1);
                }
            }
        }
    }

    private static int dfs(int pos, int pre, int include) {
        int nInc = 0;
        if (dp[pos][0] != 0) {
            nInc = dp[pos][0];
        } else {
            for (int i : edges.get(pos)) {
                if (i == pre) continue;
                nInc += dfs(i, pos, 0);
            }
            dp[pos][0] = nInc;
        }
        if (include == 1) return nInc;

        int Inc = weight[pos];
        if (dp[pos][1] != 0) {
            Inc = dp[pos][1];
        } else {
            for (int i : edges.get(pos)) {
                if (i == pre) continue;
                Inc += dfs(i, pos, 1);
            }
            dp[pos][1] = Inc;
        }

        return Math.max(Inc, nInc);
    }
}
