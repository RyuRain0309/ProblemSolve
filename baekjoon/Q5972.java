package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q5972 {

    static ArrayList<ArrayList<int[]>> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr.get(a).add(new int[]{b, c});
            arr.get(b).add(new int[]{a, c});
        }

        int[] dijkstra = new int[N + 1];
        Arrays.fill(dijkstra, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{1, 0});
        dijkstra[1] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int now = current[0];
            int nowDist = current[1];

            if (dijkstra[now] < nowDist) continue;

            for (int[] edge : arr.get(now)) {
                int next = edge[0];
                int cost = edge[1];

                if (dijkstra[next] > dijkstra[now] + cost) {
                    dijkstra[next] = dijkstra[now] + cost;
                    pq.add(new int[]{next, dijkstra[next]});
                }
            }
        }
        System.out.println(dijkstra[N]);
    }
}
