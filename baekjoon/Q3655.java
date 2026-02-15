package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3655 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            solve();
        }
        System.out.println(sb);
    }

    private static void solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] rank = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            rank[i] = Integer.parseInt(st.nextToken());
        }
        int[] inDegree = new int[N + 1];
        boolean[][] node = new boolean[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                inDegree[rank[j]]++;
                node[rank[i]][rank[j]] = true;
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (node[b][a]) {
                int temp = a;
                a = b;
                b = temp;
            }
            node[a][b] = false;
            node[b][a] = true;
            inDegree[b]--;
            inDegree[a]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        if (q.isEmpty()) {
            sb.append("IMPOSSIBLE").append("\n");
            return;
        }
        int[] res = new int[N];
        int resCnt = 0;
        while (!q.isEmpty()) {
            if (q.size() != 1) {
                sb.append("?").append("\n");
                return;
            }
            int now = q.poll();
            res[resCnt++] = now;
            for (int i = 0; i <= N; i++) {
                if (node[now][i]) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        q.offer(i);
                    }
                }
            }
        }
        if (resCnt != N) {
            sb.append("IMPOSSIBLE").append("\n");
            return;
        }
        for (int i : res) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
    }
}
