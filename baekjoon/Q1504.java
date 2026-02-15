package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1504 {
    static int N;
    static int E;
    static ArrayList<ArrayList<Node>> node = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            node.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            node.get(from).add(new Node(to, weight));
            node.get(to).add(new Node(from, weight));
        }
        for (int i = 1; i <= N; i++) {
            node.get(i).sort(Comparator.comparingInt(o -> o.weight));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int[] v = new int[2];
        v[0] = Integer.parseInt(st.nextToken());
        v[1] = Integer.parseInt(st.nextToken());

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 2; i++) {
            int sum = 0;
            sum += dijkstra(1, v[i == 0 ? 0 : 1]);
            sum += dijkstra(v[i == 0 ? 0 : 1], v[i == 0 ? 1 : 0]);
            sum += dijkstra(v[i == 0 ? 1 : 0], N);
            res = Math.min(res, sum);
        }
        System.out.print(res);
    }

    static int dijkstra(int start, int end) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N + 1];
        int[] res = new int[N + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        q.add(new Node(start, 0));
        res[start] = 0;
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (isVisited[n.vertex]) continue;
            isVisited[n.vertex] = true;

            for (Node i : node.get(n.vertex)) {
                if (res[i.vertex] > res[n.vertex] + i.weight) {
                    res[i.vertex] = res[n.vertex] + i.weight;
                    q.add(new Node(i.vertex, res[n.vertex] + i.weight));
                }
            }
        }
        if (res[end] == Integer.MAX_VALUE) {
            System.out.print(-1);
            System.exit(0);
        }
        return res[end];
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
