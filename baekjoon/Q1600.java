package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1600 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] horseY = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] horseX = {-1, 1, -2, 2, -2, 2, -1, 1};

    static boolean[][] map;
    static boolean[][][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];
        isVisited = new boolean[H][W][K + 1];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Objects.equals(st.nextToken(), "1");
            }
        }
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0, 0, 0));
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            if (pos.y == H - 1 && pos.x == W - 1) {
                System.out.println(pos.cnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos.y;
                int tx = dx[i] + pos.x;
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    continue;
                }
                if (isVisited[ty][tx][pos.horse] || map[ty][tx]) {
                    continue;
                }
                isVisited[ty][tx][pos.horse] = true;
                q.add(new Pos(ty, tx, pos.cnt + 1, pos.horse));
            }
            if (pos.horse >= K) {
                continue;
            }
            for (int i = 0; i < 8; i++) {
                int ty = horseY[i] + pos.y;
                int tx = horseX[i] + pos.x;
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    continue;
                }
                if (isVisited[ty][tx][pos.horse + 1] || map[ty][tx]) {
                    continue;
                }
                isVisited[ty][tx][pos.horse + 1] = true;
                q.add(new Pos(ty, tx, pos.cnt + 1, pos.horse + 1));
            }
        }
        System.out.println(-1);
    }

    static class Pos {
        int y, x, cnt, horse;

        public Pos(int y, int x, int cnt, int horse) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.horse = horse;
        }
    }
}
