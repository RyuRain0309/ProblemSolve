package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q1941 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] isChoice = new boolean[5][5];
    static boolean[][] map = new boolean[5][5];
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                if (s.charAt(j) == 'S') {
                    map[i][j] = true;
                }
            }
        }

        dfs(0, 0, 0);
        System.out.println(res);
    }

    private static void dfs(int depth, int cnt, int Yeon) {
        if (Yeon >= 4) {
            return;
        }
        if (cnt == 7) {
            bfs((depth - 1) / 5, (depth - 1) % 5);
            return;
        }
        if (depth >= 25) {
            return;
        }
        int y = depth / 5;
        int x = depth % 5;
        isChoice[y][x] = true;
        dfs(depth + 1, cnt + 1, Yeon + (map[y][x] ? 0 : 1));
        isChoice[y][x] = false;
        dfs(depth + 1, cnt, Yeon);
    }

    private static void bfs(int y, int x) {
        Queue<Loc> q = new LinkedList<>();
        q.add(new Loc(y, x));
        boolean[][] isVisited = new boolean[5][5];
        int choiceCnt = 0;
        while (!q.isEmpty()) {
            Loc loc = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + loc.y;
                int tx = dx[i] + loc.x;
                if (ty < 0 || ty >= 5 || tx < 0 || tx >= 5) {
                    continue;
                }
                if (isVisited[ty][tx]) {
                    continue;
                }
                if (isChoice[ty][tx]) {
                    q.add(new Loc(ty, tx));
                    isVisited[ty][tx] = true;
                    choiceCnt++;
                }
            }
        }
        if (choiceCnt == 7) {
            res++;
        }
    }

    private static class Loc {
        int y, x;

        public Loc(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
