package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1197_Kruskal {
    static class Node implements Comparable<Node> {
        int a;
        int b;
        int c;

        Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return c - o.c;
        }
    }

    static int[] uf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        uf = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            uf[i] = i;
        }
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            q.add(new Node(a, b, c));
        }
        int ans = 0;
        int cnt = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (cnt == V - 1) break;
            if (unionFind(node.a, node.b)) continue;

            ans += node.c;
            cnt++;
        }

        System.out.print(ans);
    }

    private static boolean unionFind(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return true;
        } else {
            if (a > b) {
                uf[a] = b;
            } else {
                uf[b] = a;
            }
        }
        return false;
    }

    private static int find(int num) {
        if (num == uf[num]) {
            return num;
        }
        return uf[num] = find(uf[num]);
    }
}
