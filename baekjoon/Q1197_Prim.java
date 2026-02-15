package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1197_Prim {
    static class Node implements Comparable<Node> {
        int b;
        int c;

        Node(int b, int c) {
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return c - o.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> node = new ArrayList<>();
        boolean[] isVisited = new boolean[V + 1];
        for (int i = 0; i <= V; i++) {
            node.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            node.get(a).add(new Node(b, c));
            node.get(b).add(new Node(a, c));
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0));
        int ans = 0;
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (isVisited[n.b]) continue;
            isVisited[n.b] = true;
            q.addAll(node.get(n.b));
            ans += n.c;
        }
        System.out.print(ans);
    }
}