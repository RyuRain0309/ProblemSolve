package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q16954 {
    static boolean[][] map = new boolean[8][8];
    static boolean[][] isVisited = new boolean[8][8];
    static int[] dy = {0, -1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dx = {0, 0, 0, -1, 1, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                if (s.charAt(j) == '#') {
                    map[i][j] = true;
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{7, 0, 0});
        int cnt = 0;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            if (pos[0] == 0 && pos[1] == 0) {
                System.out.println(1);
                return;
            }
            if (pos[2] > cnt) {
                wallMove();
                clearIsVisited();
                cnt = pos[2];
            }
            for (int i = 0; i < 9; i++) {
                int ty = dy[i] + pos[0];
                int tx = dx[i] + pos[1];
                if (ty < 0 || ty >= 8 || tx < 0 || tx >= 8) {
                    continue;
                }
                if (map[ty][tx] || isVisited[ty][tx]) {
                    continue;
                }
                if (ty > 1) {
                    if (map[ty - 1][tx]) {
                        continue;
                    }
                }
                isVisited[ty][tx] = true;
                q.add(new int[]{ty, tx, pos[2] + 1});
            }
        }
        System.out.println(0);
    }

    private static void clearIsVisited() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                isVisited[i][j] = false;
            }
        }
    }

    private static void wallMove() {
        for (int i = 7; i > 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (map[i - 1][j]) {
                    map[i - 1][j] = false;
                    map[i][j] = true;
                }
            }
        }
    }
}
