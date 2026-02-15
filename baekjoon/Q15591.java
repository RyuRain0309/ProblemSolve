package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q15591 {
    static int N, Q;
    static ArrayList<int[]>[] USADO;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        USADO = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            USADO[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            USADO[p].add(new int[]{q, r});
            USADO[q].add(new int[]{p, r});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(solve(k, v)).append("\n");
        }
        System.out.print(sb);
    }

    private static int solve(int k, int v) {
        boolean[] isVisited = new boolean[N + 1];
        int[] temp = new int[N + 1];
        Arrays.fill(temp, Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{v, Integer.MAX_VALUE});
        isVisited[v] = true;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int[] i : USADO[poll[0]]) {
                if (isVisited[i[0]]) {
                    continue;
                }
                temp[i[0]] = Math.min(temp[i[0]], Math.min(i[1], poll[1]));
                q.add(new int[]{i[0], temp[i[0]]});
                isVisited[i[0]] = true;
            }
        }
        int res = 0;
        for (int i = 1; i < N + 1; i++) {
            if (i == v) {
                continue;
            }
            if (temp[i] >= k) {
                res++;
            }
        }
        return res;
    }
}