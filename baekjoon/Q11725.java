package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] node = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            node[i] = new ArrayList<Integer>();
        }
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            node[from].add(to);
            node[to].add(from);
        }
        Queue<Integer> q = new LinkedList<>();
        int[] parents = new int[N + 1];
        Arrays.fill(parents, -1);
        q.add(1);
        parents[1] = 0;
        while (!q.isEmpty()) {
            int temp = q.poll();

            for (int i : node[temp]) {
                if (parents[i] == -1) {
                    q.add(i);
                    parents[i] = temp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.print(sb);
    }
}
