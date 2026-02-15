package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16933 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[N][M];
        boolean[][][] isVisited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '0') {
                    map[i][j] = true;
                }
            }
        }
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0, 1, 0, true));
        while (!q.isEmpty()) {
            Pos pos = q.poll();

            if (pos.y == N - 1 && pos.x == M - 1) {
                System.out.println(pos.cnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos.y;
                int tx = dx[i] + pos.x;
                if (ty < 0 || ty >= N || tx < 0 || tx >= M) {
                    continue;
                }
                if (isVisited[ty][tx][pos.wall]) {
                    continue;
                }
                if (map[ty][tx]) {
                    isVisited[ty][tx][pos.wall] = true;
                    q.add(new Pos(ty, tx, pos.cnt + 1, pos.wall, !pos.isDay));
                } else {
                    if (pos.wall < K) {
                        if (pos.isDay) {
                            isVisited[ty][tx][pos.wall] = true;
                            q.add(new Pos(ty, tx, pos.cnt + 1, pos.wall + 1, false));
                        } else {
                            isVisited[ty][tx][pos.wall] = true;
                            q.add(new Pos(ty, tx, pos.cnt + 2, pos.wall + 1, false));
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static class Pos {
        int y, x, cnt, wall;
        boolean isDay;

        public Pos(int y, int x, int cnt, int wall, boolean isDay) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.wall = wall;
            this.isDay = isDay;
        }
    }
}
