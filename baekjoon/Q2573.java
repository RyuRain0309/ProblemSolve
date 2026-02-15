package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2573 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int H, W;
    static int[][] map;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int year = 0;
        while (true) {
            melt();
            year++;
            isVisited = new boolean[H][W];
            int cnt = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (isVisited[i][j] || map[i][j] == 0) {
                        continue;
                    }
                    bfs(i, j);
                    cnt++;
                }
            }
            if (cnt >= 2) {
                System.out.println(year);
                break;
            }
            if (cnt == 0) {
                System.out.println(0);
                break;
            }
        }
    }

    private static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        isVisited[y][x] = true;
        q.add(new int[]{y, x});
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos[0];
                int tx = dx[i] + pos[1];
                if (ty < 0 || tx < 0 || ty >= H || tx >= W) {
                    continue;
                }
                if (map[ty][tx] == 0 || isVisited[ty][tx]) {
                    continue;
                }
                isVisited[ty][tx] = true;
                q.add(new int[]{ty, tx});
            }
        }
    }

    private static void melt() {
        int[][] temp = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                int water = 0;
                for (int k = 0; k < 4; k++) {
                    int ty = i + dy[k];
                    int tx = j + dx[k];
                    if (ty < 0 || tx < 0 || ty >= H || tx >= W) {
                        continue;
                    }
                    if (map[ty][tx] == 0) {
                        water++;
                    }
                }
                temp[i][j] = map[i][j] - water;
                if (temp[i][j] < 0) {
                    temp[i][j] = 0;
                }
            }
        }
        map = temp;
    }
}
