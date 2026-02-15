package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15681 {
    static int[] dp;
    static ArrayList<ArrayList<Integer>> node = new ArrayList<>();
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        isVisited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            node.add(new ArrayList<>());
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            node.get(a).add(b);
            node.get(b).add(a);
        }
        dfs(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.print(sb);
    }

    private static int dfs(int ver) {
        isVisited[ver] = true;
        int root = 1;
        for (int i : node.get(ver)) {
            if (!isVisited[i]) {
                root += dfs(i);
            }
        }
        return dp[ver] = root;
    }
}
