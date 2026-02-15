package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2234 {

    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {-1, 0, 1, 0};
    static int[] bit = {1, 2, 4, 8};

    static int[] roomSizes;
    static int[][] map;
    static int[][] isVisited;

    static int H, W;
    static int roomCnt = 0;
    static int roomSize = 0;
    static int twoRoomSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        isVisited = new int[H][W];
        roomSizes = new int[H * W + 1];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (isVisited[i][j] != 0) {
                    continue;
                }
                roomCnt++;
                bfs(i, j, roomCnt);
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                for (int k = 0; k < 4; k++) {
                    int ty = dy[k] + i;
                    int tx = dx[k] + j;
                    if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                        continue;
                    }
                    if (isVisited[ty][tx] != isVisited[i][j]) {
                        twoRoomSize = Math.max(twoRoomSize, roomSizes[isVisited[ty][tx]] + roomSizes[isVisited[i][j]]);
                    }
                }
            }
        }
        System.out.println(roomCnt + "\n" + roomSize + "\n" + twoRoomSize);
    }

    private static void bfs(int y, int x, int mask) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(y, x, 1, 0));
        isVisited[y][x] = mask;
        int tempRoomSize = 0;
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            tempRoomSize++;
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos.y;
                int tx = dx[i] + pos.x;
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    continue;
                }
                if (isVisited[ty][tx] != 0 || (map[pos.y][pos.x] & bit[i]) != 0) {
                    continue;
                }
                isVisited[ty][tx] = mask;
                q.add(new Pos(ty, tx, pos.cnt + 1, 0));
            }
            roomSize = Math.max(roomSize, tempRoomSize);
            roomSizes[mask] = tempRoomSize;
        }
    }

    static class Pos {
        int y, x, cnt, broken;

        public Pos(int y, int x, int cnt, int broken) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.broken = broken;
        }
    }
}
