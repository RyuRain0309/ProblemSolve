package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1260 {
    static int N;
    static int M;
    static boolean[] isVisited;
    static ArrayList<ArrayList<Integer>> v = new ArrayList<>();
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        isVisited = new boolean[N + 1];
        M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            v.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            v.get(from).add(to);
            v.get(to).add(from);
        }

        for (int i = 0; i <= N; i++) {
            Collections.sort(v.get(i));
        }


        dfs(start);
        StringBuilder sb = new StringBuilder();
        for (Integer i : ans) {
            sb.append(i).append(" ");
        }

        sb.append("\n");
        ans.clear();
        Arrays.fill(isVisited, false);

        bfs(start);
        for (Integer i : ans) {
            sb.append(i).append(" ");
        }

        System.out.print(sb);
    }

    private static void dfs(int node) {
        isVisited[node] = true;
        ans.add(node);
        for (Integer i : v.get(node)) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        ans.add(start);
        isVisited[start] = true;
        while (!q.isEmpty()) {
            int temp = q.poll();
            for (Integer i : v.get(temp)) {
                if (!isVisited[i]) {
                    q.add(i);
                    ans.add(i);
                    isVisited[i] = true;
                }
            }
        }
    }
}
