package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2146 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] map;
    static int N, res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 0 ? 0 : -1;
            }
        }
        int islandCnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -1) {
                    setIsland(i, j, islandCnt);
                    islandCnt++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    res = Integer.min(res, buildBridge(i, j));
                }
            }
        }
        System.out.println(res);
    }

    private static int buildBridge(int y, int x) {
        boolean[][] isVisited = new boolean[N][N];
        Queue<Loc> q = new LinkedList<>();
        isVisited[y][x] = true;
        q.add(new Loc(y, x, 0));
        while (!q.isEmpty()) {
            Loc loc = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + loc.y;
                int tx = dx[i] + loc.x;
                if (ty < 0 || ty >= N || tx < 0 || tx >= N) {
                    continue;
                }
                if (isVisited[ty][tx] || map[ty][tx] == map[y][x]) {
                    continue;
                }
                if (map[ty][tx] == 0) {
                    isVisited[ty][tx] = true;
                    q.add(new Loc(ty, tx, loc.cnt + 1));
                    continue;
                }
                if (map[ty][tx] != map[y][x]) {
                    return loc.cnt;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static void setIsland(int y, int x, int cnt) {
        Queue<Loc> q = new LinkedList<>();
        map[y][x] = cnt;
        q.add(new Loc(y, x));
        while (!q.isEmpty()) {
            Loc loc = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + loc.y;
                int tx = dx[i] + loc.x;
                if (ty < 0 || ty >= N || tx < 0 || tx >= N) {
                    continue;
                }
                if (map[ty][tx] != -1) {
                    continue;
                }
                map[ty][tx] = cnt;
                q.add(new Loc(ty, tx));
            }
        }
    }

    private static class Loc {
        int y, x, cnt;

        public Loc(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        public Loc(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
