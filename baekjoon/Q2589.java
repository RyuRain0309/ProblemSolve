package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2589 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] map;
    static int H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                if (s.charAt(j) == 'W') {
                    map[i][j] = true;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!map[i][j]) {
                    res = Math.max(res, bfs(i, j));
                }
            }
        }
        System.out.println(res);
    }

    private static int bfs(int y, int x) {
        int res = 0;
        boolean[][] isVisited = new boolean[H][W];
        Queue<Loc> q = new LinkedList<>();
        isVisited[y][x] = true;
        q.add(new Loc(y, x, 0));
        while (!q.isEmpty()) {
            Loc loc = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + loc.y;
                int tx = dx[i] + loc.x;
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    continue;
                }
                if (isVisited[ty][tx] || map[ty][tx]) {
                    continue;
                }
                isVisited[ty][tx] = true;
                q.add(new Loc(ty, tx, loc.cnt + 1));
                res = loc.cnt + 1;
            }
        }
        return res;
    }

    static class Loc {
        int y, x, cnt;

        public Loc(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
