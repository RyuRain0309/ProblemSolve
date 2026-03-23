package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q20303 {

    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static boolean[] isVisited;
    static int[] c;
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        c = new int[N];
        for (int i = 0; i < N; i++) {
            arr.add(new ArrayList<>());
            c[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        isVisited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (isVisited[i]) continue;
            bfs(i);
        }

        int[] dp = new int[K];
        for (int[] i : list) {
            int weight = i[0];
            int value = i[1];

            for (int j = K - 1; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }
        System.out.println(dp[K - 1]);
    }

    private static void bfs(int i) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        isVisited[i] = true;
        int resCnt = 0;
        int resSum = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            resCnt++;
            resSum += c[now];
            for (int next : arr.get(now)) {
                if (isVisited[next]) {
                    continue;
                }
                isVisited[next] = true;
                q.offer(next);
            }
        }
        list.add(new int[]{resCnt, resSum});
    }
}
