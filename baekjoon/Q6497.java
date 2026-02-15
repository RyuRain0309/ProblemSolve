package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q6497 {
    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int dist;

        Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return dist - o.dist;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            ArrayList<Edge> edges = new ArrayList<>();
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
            int total = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                edges.add(new Edge(x, y, z));
                total += z;
            }
            Collections.sort(edges);
            int cnt = 0;
            int ans = 0;
            for (Edge edge : edges) {
                if (cnt == N - 1) break;
                if (find(edge.a) == find(edge.b)) continue;

                Union(edge.a, edge.b);
                ans += edge.dist;
                cnt++;
            }
            sb.append(total - ans).append("\n");
        }
        System.out.print(sb);
    }

    private static void Union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    private static int find(int num) {
        if (num == parent[num]) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }
}
