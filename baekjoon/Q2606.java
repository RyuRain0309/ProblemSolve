package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2606 {
    static ArrayList<ArrayList<Integer>> node = new ArrayList<>();
    static boolean[] isVisited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int V = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        isVisited = new boolean[V + 1];
        for (int i = 0; i <= V; i++) {
            node.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            node.get(a).add(b);
            node.get(b).add(a);
        }
        dfs(1);
        System.out.print(ans - 1);
    }

    private static void dfs(int vertex) {
        ans++;
        isVisited[vertex] = true;
        for (Integer i : node.get(vertex)) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
    }
}
