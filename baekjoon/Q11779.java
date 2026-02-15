package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q11779 {
    private static class Bus implements Comparable<Bus> {
        int dest;
        long cost;
        int before;

        Bus(int dest, long cost, int before) {
            this.dest = dest;
            this.cost = cost;
            this.before = before;
        }

        @Override
        public int compareTo(Bus o) {
            return (int) (cost - o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int city = Integer.parseInt(br.readLine());
        int route = Integer.parseInt(br.readLine());
        int[] before = new int[city + 1];
        long[] res = new long[city + 1];
        Arrays.fill(before, -1);
        Arrays.fill(res, Long.MAX_VALUE);
        ArrayList<ArrayList<Bus>> bus = new ArrayList<>();
        for (int i = 0; i <= city; i++) {
            bus.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 0; i < route; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            bus.get(s).add(new Bus(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));
        }
        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        PriorityQueue<Bus> q = new PriorityQueue<>();
        q.add(new Bus(start, 0, 0));
        res[start] = 0;
        while (!q.isEmpty()) {
            Bus b = q.poll();

            if (before[b.dest] != -1) continue;
            before[b.dest] = b.before;

            for (Bus i : bus.get(b.dest)) {
                if (res[i.dest] > res[b.dest] + i.cost) {
                    res[i.dest] = res[b.dest] + i.cost;
                    q.add(new Bus(i.dest, res[i.dest], b.dest));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(res[dest]).append("\n");
        Stack<Integer> s = new Stack<>();
        while (dest != start) {
            s.add(dest);
            dest = before[dest];
        }
        sb.append(s.size() + 1).append("\n");
        sb.append(start).append(" ");
        while (!s.empty()) {
            sb.append(s.pop()).append(" ");
        }
        System.out.print(sb);
    }
}
