package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q14052 {
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N, M;
    static int res = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(res);
    }

    private static void dfs(int pos, int depth) {
        if (depth == 3) {
            getCntSafeArea();
            return;
        }
        if (pos == N * M) {
            return;
        }
        int y = pos / M;
        int x = pos % M;
        if (map[y][x] == 0) {
            map[y][x] = 1;
            dfs(pos + 1, depth + 1);
            map[y][x] = 0;
        }
        dfs(pos + 1, depth);
    }

    private static void getCntSafeArea() {
        int[][] infectionMap = getInfectionMap();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (infectionMap[i][j] == 0) {
                    cnt++;
                }
            }
        }
        res = Math.max(res, cnt);
    }

    private static int[][] getInfectionMap() {
        int[][] copyMap = deepCopy();
        Queue<Pos> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 2) {
                    q.add(new Pos(i, j));
                }
            }
        }
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos.y;
                int tx = dx[i] + pos.x;
                if (ty < 0 || ty >= N || tx < 0 || tx >= M) {
                    continue;
                }
                if (copyMap[ty][tx] != 0) {
                    continue;
                }
                copyMap[ty][tx] = 2;
                q.add(new Pos(ty, tx));
            }
        }
        return copyMap;
    }

    private static int[][] deepCopy() {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    private static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
