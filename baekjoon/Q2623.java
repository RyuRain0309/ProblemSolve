package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] singer = new int[N + 1];
        ArrayList<Integer>[] node = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int end = Integer.parseInt(st.nextToken());
            int[] array = new int[end];
            for (int j = 0; j < end; j++) {
                array[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < end - 1; j++) {
                node[array[j]].add(array[j + 1]);
                singer[array[j + 1]]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (singer[i] == 0) {
                q.add(i);
            }
        }
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int s = q.poll();
            sb.append(s).append("\n");
            cnt++;
            for (int i : node[s]) {
                singer[i]--;
                if (singer[i] == 0) {
                    q.add(i);
                }
            }
        }
        if (cnt == N) {
            System.out.println(sb);
        } else {
            System.out.println(0);
        }
    }
}
