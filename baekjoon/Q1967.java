package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Q1967 {
    private static class Pair {
        int vertex;
        int distance;

        Pair(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    static ArrayList<Pair>[] node;
    static boolean[] isVisited;
    static int V, T, ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        node = new ArrayList[V + 1];
        isVisited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            node[i] = new ArrayList<Pair>();
        }

        StringTokenizer st;
        for (int i = 1; i < V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            node[from].add(new Pair(to, dist));
            node[to].add(new Pair(from, dist));
        }
        bfs(1);
        bfs(T);
        System.out.print(ans);
    }

    static void bfs(int start) {
        int[] res = new int[V + 1];
        boolean[] isVisited = new boolean[V + 1];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(start, 0));
        isVisited[start] = true;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            for (Pair i : node[pair.vertex]) {
                if (isVisited[i.vertex]) continue;
                q.add(new Pair(i.vertex, pair.distance + i.distance));
                res[i.vertex] = pair.distance + i.distance;
                isVisited[i.vertex] = true;
            }
        }
        for (int i = 2; i <= V; i++) {
            if (ans < res[i]) {
                ans = res[i];
                T = i;
            }
        }
    }
}
