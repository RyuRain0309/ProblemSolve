package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1774 {
    static class Node implements Comparable<Node> {
        int a;
        int b;
        double dist;

        Node(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            if (dist > o.dist) {
                return 1;
            }
            return -1;
        }
    }

    static int[] uf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        uf = new int[N + 1];
        int[] posX = new int[N + 1];
        int[] posY = new int[N + 1];
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            posX[i] = Integer.parseInt(st.nextToken());
            posY[i] = Integer.parseInt(st.nextToken());
            for (int j = 1; j < i; j++) {
                q.add(new Node(i, j, getDist(posX[i], posX[j], posY[i], posY[j])));
            }
            uf[i] = i;
        }

        int preCnt = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            a = unionFind(a);
            b = unionFind(b);
            if (a == b) continue;
            if (b > a) {
                uf[b] = a;
            } else {
                uf[a] = b;
            }
            preCnt++;
        }


        double ans = 0;
        int cnt = preCnt;

        while (!q.isEmpty()) {
            if (cnt == N - 1) {
                break;
            }
            Node node = q.poll();
            int a = unionFind(node.a);
            int b = unionFind(node.b);
            if (a == b) continue;

            if (a > b) {
                uf[a] = b;
            } else {
                uf[b] = a;
            }

            ans += node.dist;
            cnt++;
        }

        System.out.printf("%.2f", (double) Math.round(ans * 100) / 100);
    }

    private static int unionFind(int num) {
        if (num == uf[num]) {
            return num;
        }
        return uf[num] = unionFind(uf[num]);
    }

    private static double getDist(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
