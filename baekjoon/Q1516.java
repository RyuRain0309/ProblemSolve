package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1516 {
    static int N;
    static int[] time;
    static int[] cnt;
    static int[] dp;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N + 1];
        time = new int[N + 1];
        cnt = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            time[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) {
                    break;
                }
                arr[next].add(i);
                cnt[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (cnt[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            Integer i = q.poll();
            dp[i] += time[i];
            for (Integer j : arr[i]) {
                dp[j] = Math.max(dp[j], dp[i]);
                cnt[j]--;
                if (cnt[j] == 0) {
                    q.offer(j);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append("\n");
        }
        System.out.print(sb);
    }
}
