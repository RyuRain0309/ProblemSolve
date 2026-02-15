package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1697 {
    static final int MAX = 100002;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] isVisited = new boolean[MAX];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(N, 0));
        isVisited[N] = true;
        while (!q.isEmpty()) {
            Pair pair = q.poll();

            if (pair.pos == K) {
                System.out.print(pair.cnt);
                break;
            }

            if (pair.pos * 2 < MAX && !isVisited[pair.pos * 2]) {
                q.add(new Pair(pair.pos * 2, pair.cnt + 1));
                isVisited[pair.pos * 2] = true;
            }
            if (pair.pos + 1 < MAX && !isVisited[pair.pos + 1]) {
                q.add(new Pair(pair.pos + 1, pair.cnt + 1));
                isVisited[pair.pos + 1] = true;
            }
            if (pair.pos > 0 && !isVisited[pair.pos - 1]) {
                q.add(new Pair(pair.pos - 1, pair.cnt + 1));
                isVisited[pair.pos - 1] = true;
            }
        }
    }

    private static class Pair {
        private final int pos;
        private final int cnt;

        Pair(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }
}
