package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q9466 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int cnt;
    static int[] arr;
    static boolean[] isVisited;
    static boolean[] done;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            solve();
        }
        System.out.println(sb);
    }

    private static void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        cnt = 0;
        arr = new int[N + 1];
        isVisited = new boolean[N + 1];
        done = new boolean[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            dfs(i);
        }
        sb.append(N - cnt).append("\n");
    }

    private static void dfs(int now) {
        if (isVisited[now]) {
            return;
        }

        isVisited[now] = true;
        int next = arr[now];

        if (!isVisited[next])
            dfs(next);
        else {
            if (!done[next]) {
                cnt++;
                for (int i = next; i != now; i = arr[i])
                    cnt++;
            }
        }
        done[now] = true;
    }
}
