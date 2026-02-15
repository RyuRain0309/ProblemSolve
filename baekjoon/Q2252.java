package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] arr = new ArrayList[N + 1];
        int[] entries = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            arr[f].add(t);
            entries[t]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (entries[i] == 0) {
                q.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int n = q.poll();
            sb.append(n).append(" ");

            for (int i : arr[n]) {
                entries[i]--;
                if (entries[i] == 0) {
                    q.offer(i);
                }
            }
        }
        System.out.println(sb);
    }
}
