package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q17135 {

    static int[] dy = {0, -1, 0};
    static int[] dx = {-1, 0, 1};

    static int H, W, RANGE, res = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        RANGE = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0, 0);
        System.out.println(res);
    }

    private static void dfs(int depth, int cnt, int bit) {
        if (cnt == 3) {
            bfs(bit);
            return;
        }
        if (depth >= W) {
            return;
        }
        dfs(depth + 1, cnt, bit);
        dfs(depth + 1, cnt + 1, bit | 1 << depth);
    }

    private static void bfs(int bit) {
        int[] archer = new int[3];
        int index = 0;
        for (int i = 0; i < W; i++) {
            if ((bit & 1 << i) == 0) {
                continue;
            }
            archer[index++] = i;
        }
        int[][] copyMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            copyMap[i] = map[i].clone();
        }
        Arrow[] remove = new Arrow[3];
        for (int i = H - 1; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                Queue<Arrow> q = new LinkedList<>();
                boolean[][] isVisited = new boolean[H][W];
                q.offer(new Arrow(i, archer[j], 1));
                while (!q.isEmpty()) {
                    Arrow arrow = q.poll();
                    if (arrow.dist > RANGE) {
                        break;
                    }
                    if (copyMap[arrow.y][arrow.x] == 1) {
                        remove[j] = arrow;
                        break;
                    }
                    for (int k = 0; k < 3; k++) {
                        int ny = dy[k] + arrow.y;
                        int nx = dx[k] + arrow.x;
                        if (ny < 0 || ny >= H || nx < 0 || nx >= W) {
                            continue;
                        }
                        if (isVisited[ny][nx]) {
                            continue;
                        }
                        isVisited[ny][nx] = true;
                        q.offer(new Arrow(ny, nx, arrow.dist + 1));
                    }
                }
            }
            for (int j = 0; j < 3; j++) {
                if (remove[j] == null) {
                    continue;
                }
                copyMap[remove[j].y][remove[j].x] = 0;
            }
        }
        int removeEnemy = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 1 && copyMap[i][j] == 0) {
                    removeEnemy++;
                }
            }
        }
        res = Math.max(res, removeEnemy);
    }

    static class Arrow {
        int y, x, dist;

        public Arrow(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
}
