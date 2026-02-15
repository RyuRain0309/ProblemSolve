package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int[] isVisited = new int[V + 1];
            ArrayList<ArrayList<Integer>> node = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                node.add(new ArrayList<>());
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                node.get(u).add(v);
                node.get(v).add(u);
            }
            boolean isBipartiteGraph = true;
            Queue<Pair> q = new LinkedList<>();

            a:
            for (int i = 1; i <= V; i++) {
                if (isVisited[i] == 0 && !node.get(i).isEmpty()) {
                    q.add(new Pair(i, 1));
                    isVisited[i] = 1;
                }
                while (!q.isEmpty()) {
                    Pair pair = q.poll();
                    for (Integer v : node.get(pair.vertex)) {
                        if (isVisited[v] != 0) {
                            if (isVisited[v] == pair.color) {
                                isBipartiteGraph = false;
                                break a;
                            }
                        } else {
                            q.add(new Pair(v, pair.color * -1));
                            isVisited[v] = pair.color * -1;
                        }
                    }
                }
            }
            sb.append(isBipartiteGraph ? "YES\n" : "NO\n");
        }
        System.out.print(sb);
    }

    private static class Pair {
        final int vertex;
        final int color;

        Pair(int vertex, int color) {
            this.vertex = vertex;
            this.color = color;
        }
    }
}
