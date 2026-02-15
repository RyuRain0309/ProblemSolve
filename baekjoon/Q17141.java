package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q17141 {

    static int[] dy = {-1, 1, 0, 0,};
    static int[] dx = {0, 0, -1, 1};
    static int[] bits = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

    static int N, M;
    static boolean[][] map;
    static boolean[][] isVisited;

    static int res = Integer.MAX_VALUE;
    static int posCnt = 0;
    static Pos[] poss = new Pos[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 2) {
                    poss[posCnt] = new Pos(i, j, 0);
                    posCnt++;
                }
                map[i][j] = temp == 1;
            }
        }
        dfs(0, 0, 0);
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static void dfs(int depth, int bit, int bitCnt) {
        if (bitCnt == M) {
            bfs(bit);
            return;
        }
        if (depth >= posCnt) {
            return;
        }
        dfs(depth + 1, bit | bits[depth], bitCnt + 1);
        dfs(depth + 1, bit, bitCnt);
    }

    private static void bfs(int bit) {
        Queue<Pos> q = new LinkedList<>();
        isVisited = new boolean[N][N];
        for (int i = 0; i < posCnt; i++) {
            if ((bit & bits[i]) != 0) {
                q.add(poss[i]);
                isVisited[poss[i].y][poss[i].x] = true;
            }
        }
        int maxTime = 0;
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            maxTime = Math.max(maxTime, pos.cnt);
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos.y;
                int tx = dx[i] + pos.x;
                if (ty < 0 || ty >= N || tx < 0 || tx >= N) {
                    continue;
                }
                if (isVisited[ty][tx] || map[ty][tx]) {
                    continue;
                }
                isVisited[ty][tx] = true;
                q.add(new Pos(ty, tx, pos.cnt + 1));
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!map[i][j] && !isVisited[i][j]) {
                    return;
                }
            }
        }
        res = Math.min(res, maxTime);
    }


    static class Pos {
        int y, x, cnt;

        public Pos(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
