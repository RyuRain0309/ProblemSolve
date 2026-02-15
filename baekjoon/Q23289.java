package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q23289 {
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] spreadY = {{0, -1, 1}, {0, -1, 1}, {-1, -1, -1}, {1, 1, 1}};
    static int[][] spreadX = {{1, 1, 1}, {-1, -1, -1}, {0, -1, 1}, {0, -1, 1}};

    static int[][] map;
    static int R, C, K, W;
    static boolean[][][][] wall;
    static ArrayList<int[]> heaters = new ArrayList<>();
    static ArrayList<int[]> research = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        wall = new boolean[R][C][R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                int t = Integer.parseInt(st.nextToken());
                if (t == 0) {
                    continue;
                }
                if (t == 5) {
                    research.add(new int[]{i, j});
                    continue;
                }
                heaters.add(new int[]{i, j, t - 1});
            }
        }
        W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            if (t == 0) {
                wall[y - 1][x][y][x] = true;
                wall[y][x][y - 1][x] = true;
            }
            if (t == 1) {
                wall[y][x][y][x + 1] = true;
                wall[y][x + 1][y][x] = true;
            }
        }
        int choco = 0;
        while (choco <= 100) {
            for (int[] heater : heaters) {
                onHeater(heater[0], heater[1], heater[2]);
            }
            control();
            edge();
            choco++;
            if (checkRes()) {
                break;
            }
        }
        System.out.println(choco);
    }

    private static boolean checkRes() {
        for (int[] pos : research) {
            if (map[pos[0]][pos[1]] < K) {
                return false;
            }
        }
        return true;
    }

    private static void edge() {
        for (int i = 0; i < R; i++) {
            if (map[i][0] != 0) {
                map[i][0] -= 1;
            }
            if (map[i][C - 1] != 0) {
                map[i][C - 1] -= 1;
            }
        }
        for (int i = 1; i < C - 1; i++) {
            if (map[0][i] != 0) {
                map[0][i] -= 1;
            }
            if (map[R - 1][i] != 0) {
                map[R - 1][i] -= 1;
            }
        }
    }

    private static void control() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < 2; k++) {
                    int ty = i + (k == 0 ? 1 : 0);
                    int tx = j + (k == 1 ? 1 : 0);
                    if (ty >= R || tx >= C) {
                        continue;
                    }
                    if (wall[i][j][ty][tx]) {
                        continue;
                    }
                    if (map[i][j] == map[ty][tx]) {
                        continue;
                    }
                    int diff = Math.abs(map[i][j] - map[ty][tx]);
                    if (map[i][j] > map[ty][tx]) {
                        temp[i][j] -= diff / 4;
                        temp[ty][tx] += diff / 4;
                    } else {
                        temp[i][j] += diff / 4;
                        temp[ty][tx] -= diff / 4;
                    }
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    private static void onHeater(int y, int x, int dir) {
        int[][] temp = new int[R][C];
        Queue<int[]> q = new LinkedList<>();
        int[] start = new int[]{y + dy[dir], x + dx[dir], 5};
        q.add(start);
        temp[start[0]][start[1]] = 5;
        while (!q.isEmpty()) {
            int[] h = q.poll();
            for (int i = 0; i < 3; i++) {
                int ty = h[0] + spreadY[dir][i];
                int tx = h[1] + spreadX[dir][i];
                if (ty < 0 || ty >= R || tx < 0 || tx >= C || temp[ty][tx] != 0) {
                    continue;
                }
                if (wallCheck(h[0], h[1], ty, tx, dir)) {
                    continue;
                }
                temp[ty][tx] = h[2] - 1;
                if (temp[ty][tx] != 1) {
                    q.add(new int[]{ty, tx, h[2] - 1});
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += temp[i][j];
            }
        }
    }

    private static boolean wallCheck(int y, int x, int ty, int tx, int dir) {
        if (y == ty || x == tx) {
            return wall[y][x][ty][tx];
        } else {
            if (dir == 0 || dir == 1) {
                return wall[y][x][ty][x] || wall[ty][x][ty][tx];
            } else {
                return wall[y][x][y][tx] || wall[y][tx][ty][tx];
            }
        }
    }
}
