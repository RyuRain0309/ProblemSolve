package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q6087 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] opposite = {1, 0, 3, 2};

    static boolean[][] map;
    static int[][] isVisited;
    static int[][] direct;
    static int[][] c = new int[2][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        isVisited = new int[H][W];
        direct = new int[H][W];
        map = new boolean[H][W];
        int pos = 0;
        for (int i = 0; i < H; i++) {
            Arrays.fill(isVisited[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                if (s.charAt(j) == '*') {
                    map[i][j] = true;
                }
                if (s.charAt(j) == 'C') {
                    c[pos][0] = i;
                    c[pos][1] = j;
                    pos++;
                }
            }
        }

        PriorityQueue<Laser> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.reflect));
        for (int i = 0; i < 4; i++) {
            int ty = dy[i] + c[0][0];
            int tx = dx[i] + c[0][1];
            isVisited[c[0][0]][c[0][1]] = 0;
            if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                continue;
            }
            if (map[ty][tx]) {
                continue;
            }
            q.add(new Laser(ty, tx, i, 0));
            isVisited[ty][tx] = 0;
        }
        while (!q.isEmpty()) {
            Laser laser = q.poll();
            if (laser.y == c[1][0] && laser.x == c[1][1]) {
                System.out.println(laser.reflect);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + laser.y;
                int tx = dx[i] + laser.x;
                int reflect = i == laser.direction ? laser.reflect : laser.reflect + 1;
                if (opposite[laser.direction] == i) {
                    continue;
                }
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    continue;
                }
                if (map[ty][tx] || isVisited[ty][tx] < reflect) {
                    continue;
                }
                if (isVisited[ty][tx] == reflect) {
                    if (direct[ty][tx] == i) {
                        continue;
                    }
                }
                q.add(new Laser(ty, tx, i, reflect));
                isVisited[ty][tx] = reflect;
                direct[ty][tx] = i;
            }
        }
    }


    private static class Laser {
        int y, x, direction, reflect;

        public Laser(int y, int x, int direction, int reflect) {
            this.y = y;
            this.x = x;
            this.direction = direction;
            this.reflect = reflect;
        }
    }

}
