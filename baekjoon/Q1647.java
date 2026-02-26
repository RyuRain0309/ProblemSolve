package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1647 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Edge>> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Edge(b, c));
            list.get(b).add(new Edge(a, c));
        }

        int ans = 0;
        int maxEdge = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        boolean[] isVisited = new boolean[N];
        pq.add(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge n = pq.poll();
            if (isVisited[n.v]) continue;
            isVisited[n.v] = true;

            ans += n.c;
            maxEdge = Math.max(maxEdge, n.c);

            for (Edge next : list.get(n.v)) {
                if (!isVisited[next.v]) {
                    pq.add(next);
                }
            }
        }
        System.out.println(ans - maxEdge);
    }

    static class Edge implements Comparable<Edge> {
        int v;
        int c;

        Edge(int v, int c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return c - o.c;
        }
    }
}
