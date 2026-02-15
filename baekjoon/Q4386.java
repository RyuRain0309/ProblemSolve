package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q4386 {
    static class Pos {
        float x;
        float y;

        Pos(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        int des;
        float dist;

        Node(int des, float dist) {
            this.des = des;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (dist - o.dist);
        }
    }

    static Pos[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        boolean[] isVisited = new boolean[N + 1];
        pos = new Pos[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            pos[i] = new Pos(x, y);
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0));
        float ans = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (isVisited[node.des]) continue;
            isVisited[node.des] = true;
            for (int i = 1; i <= N; i++) {
                if (!isVisited[i]) {
                    q.add(new Node(i, getDist(node.des, i)));
                }
            }
            ans += node.dist;
        }
        System.out.print(ans);
    }

    private static float getDist(int a, int b) {
        float h = pos[a].x - pos[b].x;
        h *= h;
        float w = pos[a].y - pos[b].y;
        w *= w;
        return (float) Math.sqrt(h + w);
    }
}
