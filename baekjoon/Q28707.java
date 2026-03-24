package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q28707 {

    static class Node implements Comparable<Node> {
        long bits;
        int cost;

        public Node(long bits, int cost) {
            this.bits = bits;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[][] ops = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            ops[i][0] = Integer.parseInt(st.nextToken()) - 1;
            ops[i][1] = Integer.parseInt(st.nextToken()) - 1;
            ops[i][2] = Integer.parseInt(st.nextToken());
        }

        HashMap<Long, Integer> dist = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        long startState = getBits(A);
        dist.put(startState, 0);
        pq.offer(new Node(startState, 0));

        Arrays.sort(A);
        long targetState = getBits(A);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            long curState = current.bits;

            if (current.cost > dist.getOrDefault(curState, Integer.MAX_VALUE)) {
                continue;
            }

            if (targetState == curState) {
                System.out.println(current.cost);
                return;
            }

            for (int[] op : ops) {
                long nextState = swapBits(curState, op[0], op[1]);
                int nextCost = current.cost + op[2];

                if (nextCost < dist.getOrDefault(nextState, Integer.MAX_VALUE)) {
                    dist.put(nextState, nextCost);
                    pq.offer(new Node(nextState, nextCost));
                }
            }
        }

        // 큐가 다 빌 때까지 목표 상태를 만나지 못했다면 불가능한 경우
        System.out.println(-1);
    }

    private static long swapBits(long state, int a, int b) {
        long aVal = (state >> (a * 4)) & 0xFL;
        long bVal = (state >> (b * 4)) & 0xFL;

        state &= ~(0xFL << (a * 4));
        state &= ~(0xFL << (b * 4));

        state |= (aVal << (b * 4));
        state |= (bVal << (a * 4));

        return state;
    }

    private static long getBits(int[] a) {
        long res = 0;
        for (int i = 0; i < a.length; i++) {
            res += ((long) a[i] << (i * 4));
        }
        return res;
    }
}