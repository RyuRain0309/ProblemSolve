package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2178 {
    static int[] rangeX = {1, 0, -1, 0};
    static int[] rangeY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '1') {
                    map[i][j] = true;
                }
            }
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, 1));
        map[0][0] = false;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            if (pair.y == N - 1 && pair.x == M - 1) {
                System.out.print(pair.cnt);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int moveX = pair.x + rangeX[i];
                int moveY = pair.y + rangeY[i];
                if (moveX < 0 || moveY < 0 || moveY >= N || moveX >= M) {
                    continue;
                }
                if (map[moveY][moveX]) {
                    q.add(new Pair(moveY, moveX, pair.cnt + 1));
                    map[moveY][moveX] = false;
                }
            }
        }
    }

    static class Pair {
        private final int y;
        private final int x;
        private final int cnt;

        public Pair(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
