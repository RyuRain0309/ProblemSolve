package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q13549 {
    static final int MAX = 100_002;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] isVisited = new boolean[MAX];
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(N, 0));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            isVisited[pair.pos] = true;
            if (pair.pos == K) {
                System.out.print(pair.cnt);
                break;
            }

            if (pair.pos * 2 < MAX && pair.pos < K && !isVisited[pair.pos * 2]) {
                q.add(new Pair(pair.pos * 2, pair.cnt));
            }
            if (pair.pos + 1 < MAX && !isVisited[pair.pos + 1]) {
                q.add(new Pair(pair.pos + 1, pair.cnt + 1));
            }
            if (pair.pos > 0 && !isVisited[pair.pos - 1]) {
                q.add(new Pair(pair.pos - 1, pair.cnt + 1));
            }
        }
    }

    private static class Pair implements Comparable<Pair> {
        int pos;
        int cnt;

        Pair(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair o) {
            if (cnt == o.cnt) {
                return pos - o.pos;
            }
            return cnt - o.cnt;
        }
    }

}
