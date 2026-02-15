package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q9370 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static ArrayList<ArrayList<Node>> node = new ArrayList<>();
    static StringTokenizer st;
    static int n, m, t, s, g, h;
    static final int MAX = 50_000_001;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            node.clear();
            solve();
        }
        System.out.print(sb);
    }

    private static void solve() throws IOException {
        for (int i = 0; i <= n; i++) {
            node.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            node.get(from).add(new Node(to, weight));
            node.get(to).add(new Node(from, weight));
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(node.get(i));
        }
        int[][] sum = new int[3][n + 1];
        sum[0] = dijkstra(s);
        sum[1] = dijkstra(g);
        sum[2] = dijkstra(h);
        int pass1 = sum[0][g] + sum[1][h];
        int pass2 = sum[0][h] + sum[2][g];
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int can = Integer.parseInt(br.readLine());
            if (sum[0][can] != MAX && sum[2][can] != MAX && sum[0][can] == pass1 + sum[2][can]) {
                ans.add(can);
            } else if (sum[0][can] != MAX && sum[1][can] != MAX && sum[0][can] == pass2 + sum[1][can]) {
                ans.add(can);
            }
        }
        Collections.sort(ans);
        for (int i : ans) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
    }

    private static int[] dijkstra(int start) {
        boolean[] isVisited = new boolean[n + 1];
        int[] res = new int[n + 1];
        Arrays.fill(res, MAX);
        PriorityQueue<Node> q = new PriorityQueue<>();

        q.add(new Node(start, 0));
        res[start] = 0;
        while (!q.isEmpty()) {
            Node e = q.poll();

            if (isVisited[e.vertex]) continue;
            isVisited[e.vertex] = true;

            for (Node i : node.get(e.vertex)) {
                if (res[i.vertex] > res[e.vertex] + i.weight) {
                    res[i.vertex] = res[e.vertex] + i.weight;
                    q.add(new Node(i.vertex, res[i.vertex]));
                }
            }
        }
        return res;
    }

    private static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}
