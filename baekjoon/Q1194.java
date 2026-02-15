package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1194 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        char[][] maze = new char[H][W];
        boolean[][][] isVisited = new boolean[H][W][1 << 8];
        Queue<Loc> q = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                if (s.charAt(j) == '0') {
                    isVisited[i][j][0] = true;
                    q.add(new Loc(i, j, 0, 0));
                    maze[i][j] = '.';
                    continue;
                }
                maze[i][j] = s.charAt(j);
            }
        }
        int res = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Loc loc = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + loc.y;
                int tx = dx[i] + loc.x;
                int tkey = loc.key;
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    continue;
                }
                if (isVisited[ty][tx][tkey] || maze[ty][tx] == '#') {
                    continue;
                }
                if (maze[ty][tx] == '1') {
                    res = Math.min(res, loc.cnt + 1);
                    continue;
                }
                if (maze[ty][tx] >= 'A' && maze[ty][tx] <= 'F') {
                    if (isLock(tkey, maze[ty][tx])) {
                        continue;
                    }
                }
                if (maze[ty][tx] >= 'a' && maze[ty][tx] <= 'f') {
                    tkey = getKey(tkey, maze[ty][tx]);
                }

                isVisited[ty][tx][tkey] = true;
                q.add(new Loc(ty, tx, loc.cnt + 1, tkey));
            }
        }

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static boolean isLock(int key, char c) {
        return (key & (1 << (c - 'a'))) == 0;
    }

    private static int getKey(int key, char c) {
        return key | (1 << (c - 'a'));
    }

    private static class Loc {
        int y, x, cnt, key;

        public Loc(int y, int x, int cnt, int key) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.key = key;
        }
    }
}
