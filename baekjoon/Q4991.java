package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q4991 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] bit = {0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) {
                break;
            }
            solve(W, H);
        }
        System.out.println(sb);
    }

    private static void solve(int W, int H) throws IOException {
        int[][] map = new int[H][W];
        boolean[][][] isVisited = new boolean[H][W][1024];
        int wasteCnt = 0;
        Queue<Pos> q = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                if (s.charAt(j) == '*') {
                    wasteCnt++;
                    map[i][j] = wasteCnt;
                    continue;
                }
                if (s.charAt(j) == 'x') {
                    map[i][j] = -1;
                    continue;
                }
                if (s.charAt(j) == 'o') {
                    q.add(new Pos(i, j, 0, 0));
                    isVisited[i][j][0] = true;
                }
                map[i][j] = 0;
            }
        }

        while (!q.isEmpty()) {
            Pos pos = q.poll();
            if (pos.clean == bit[wasteCnt + 1] - 1) {
                sb.append(pos.cnt).append("\n");
                return;
            }
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos.y;
                int tx = dx[i] + pos.x;
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    continue;
                }
                if (isVisited[ty][tx][pos.clean] || map[ty][tx] == -1) {
                    continue;
                }
                if (map[ty][tx] == 0) {
                    q.add(new Pos(ty, tx, pos.cnt + 1, pos.clean));
                    isVisited[ty][tx][pos.clean] = true;
                } else {
                    q.add(new Pos(ty, tx, pos.cnt + 1, pos.clean | bit[map[ty][tx]]));
                    isVisited[ty][tx][pos.clean | bit[map[ty][tx]]] = true;
                }
            }
        }
        sb.append(-1).append("\n");
    }

    static class Pos {
        int y, x, cnt, clean;

        public Pos(int y, int x, int cnt, int clean) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.clean = clean;
        }
    }
}
