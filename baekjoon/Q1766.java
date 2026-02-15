package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] inDegree = new int[N + 1];
        ArrayList<ArrayList<Integer>> node = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            node.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            node.get(a).add(b);
            inDegree[b]++;
        }
        Queue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                pq.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(" ");
            for (int i : node.get(now)) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    pq.offer(i);
                }
            }
        }
        System.out.println(sb);
    }
}
