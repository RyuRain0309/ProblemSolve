package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1261 {
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[M][N];
        boolean[][] isVisited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '1') {
                    map[i][j] = true;
                }
            }
        }

        PriorityQueue<Pos> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.wall));
        q.add(new Pos(0, 0, 0));
        while (!q.isEmpty()) {
            Pos pos = q.poll();
//            System.out.println(pos.y + "  " + pos.x + "  " + pos.wall);
            if (pos.y == M - 1 && pos.x == N - 1) {
                System.out.println(pos.wall);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int y = pos.y + dy[i];
                int x = pos.x + dx[i];
                if (y >= 0 && y < M && x >= 0 && x < N && !isVisited[y][x]) {
                    if (map[y][x]) {
                        q.add(new Pos(y, x, pos.wall + 1));
                    } else {
                        q.add(new Pos(y, x, pos.wall));
                    }
                    isVisited[y][x] = true;
                }
            }
        }
    }

    static class Pos {
        int y, x, wall;

        public Pos(int y, int x, int wall) {
            this.y = y;
            this.x = x;
            this.wall = wall;
        }
    }
}
