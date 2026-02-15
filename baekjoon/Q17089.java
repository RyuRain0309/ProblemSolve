package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q17089 {
    static int res = Integer.MAX_VALUE;
    static int[] note = new int[4];
    static int[] friends;
    static boolean[] isVisited;
    static ArrayList<Integer>[] node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        node = new ArrayList[N + 1];
        friends = new int[N + 1];
        isVisited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            node[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a]++;
            friends[b]++;
            node[a].add(b);
            node[b].add(a);
        }
        for (int i = 1; i <= N; i++) {
            note[0] = i;
            dfs(0);
            isVisited[i] = true;
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static void dfs(int depth) {
        if (depth == 3) {
            if (note[0] == note[3]) {
                int sum = friends[note[0]] + friends[note[1]] + friends[note[2]] - 6;
                res = Math.min(res, sum);
            }
            return;
        }
        for (Integer i : node[note[depth]]) {
            if (isVisited[i]) {
                continue;
            }
            if (note[depth] == i) {
                continue;
            }
            if (depth - 1 >= 0) {
                if (note[depth - 1] == i) {
                    continue;
                }
            }
            note[depth + 1] = i;
            dfs(depth + 1);
        }
    }
}
