package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q4803 {
    static ArrayList<Integer>[] node;
    static boolean[] isVisited;
    static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int testCase = 0;
        while (true) {
            testCase++;
            st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            if (V == 0) {
                break;
            }
            isVisited = new boolean[V + 1];
            node = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                node[i] = new ArrayList<>();
            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                node[s].add(e);
                node[e].add(s);
            }
            res = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                if (!isVisited[i]) {
                    isVisited[i] = true;
                    res[i] = 1;
                    dfs(i, 0, i);
                }
            }
            int ans = 0;
            for (int i : res) {
                ans += i;
            }
            sb.append("Case ").append(testCase).append(": ");
            if (ans == 0) {
                sb.append("No trees.\n");
            } else if (ans == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(ans).append(" trees.\n");
            }
        }
        System.out.print(sb);
    }

    private static void dfs(int now, int pre, int root) {
        for (int i : node[now]) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                dfs(i, now, root);
            } else {
                if (i != pre) {
                    res[root] = 0;
                }
            }
        }
    }
}
