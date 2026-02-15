package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1005 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            solve();
        }
        System.out.println(sb);
    }

    private static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] buildTime = new int[N + 1];
        int[] totalBuildTime = new int[N + 1];
        int[] pre = new int[N + 1];
        ArrayList<Integer>[] node = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            node[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            buildTime[i] = Integer.parseInt(st.nextToken());
            totalBuildTime[i] = -987654321;
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            node[a].add(b);
            pre[b]++;
        }
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (pre[i] == 0) {
                q.offer(i);
                totalBuildTime[i] = buildTime[i];
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i : node[now]) {
                totalBuildTime[i] = Math.max(totalBuildTime[i], totalBuildTime[now] + buildTime[i]);
                pre[i]--;
                if (pre[i] == 0) {
                    q.offer(i);
                }
            }
        }
        int W = Integer.parseInt(br.readLine());
        sb.append(totalBuildTime[W]).append("\n");
    }
}
