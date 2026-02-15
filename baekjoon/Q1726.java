package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1726 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] dDIr = {{2, 3}, {2, 3}, {0, 1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[H][W];
        boolean[][][] isVisited = new boolean[H][W][4];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    map[i][j] = true;
                }
            }
        }
        Queue<Loc> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        Loc start = new Loc(Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1, 0);

        st = new StringTokenizer(br.readLine());
        Loc dest = new Loc(Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1, 0);

        if (dest.isEqual(start.y, start.x, start.dir)) {
            System.out.println(0);
            return;
        }

        q.add(start);
        while (!q.isEmpty()) {
            Loc loc = q.poll();
            for (int i = 1; i <= 3; i++) {
                int ty = dy[loc.dir] * i + loc.y;
                int tx = dx[loc.dir] * i + loc.x;
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    break;
                }
                if (map[ty][tx]) {
                    break;
                }
                if (isVisited[ty][tx][loc.dir]) {
                    continue;
                }
                if (dest.isEqual(ty, tx, loc.dir)) {
                    System.out.println(loc.cnt + 1);
                    System.exit(0);
                }
                isVisited[ty][tx][loc.dir] = true;
                q.add(new Loc(ty, tx, loc.dir, loc.cnt + 1));
            }

            for (int i = 0; i < 2; i++) {
                int tDir = dDIr[loc.dir][i];
                if (isVisited[loc.y][loc.x][tDir]) {
                    continue;
                }
                if (dest.isEqual(loc.y, loc.x, tDir)) {
                    System.out.println(loc.cnt + 1);
                    System.exit(0);
                }
                isVisited[loc.y][loc.x][tDir] = true;
                q.add(new Loc(loc.y, loc.x, tDir, loc.cnt + 1));
            }
        }
        System.out.println(-1);
    }

    static class Loc {
        int y, x, dir, cnt;

        public Loc(int y, int x, int dir, int cnt) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cnt = cnt;
        }

        boolean isEqual(int y, int x, int dir) {
            return this.y == y && this.x == x && this.dir == dir;
        }
    }
}
