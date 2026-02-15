package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3197 {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int H;
    static int W;
    static int day;
    static Pos dest;

    static boolean[][] map;
    static boolean[][] isVisited;
    static boolean[][] isPut;
    static Queue<Pos>[] swan;
    static Queue<Pos>[] glacier;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];
        isVisited = new boolean[H][W];
        isPut = new boolean[H][W];
        swan = new Queue[2];
        glacier = new Queue[2];
        for (int i = 0; i < 2; i++) {
            swan[i] = new LinkedList<>();
            glacier[i] = new LinkedList<>();
        }
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                if (s.charAt(j) == 'X') {
                    map[i][j] = true;
                }
                if (s.charAt(j) == 'L') {
                    swan[0].add(new Pos(i, j));
                }
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
                    if (map[ty][tx]) {
                        continue;
                    }
                    isPut[i][j] = true;
                    glacier[0].add(new Pos(i, j));
                    break;
                }
            }
        }
        day = 0;
        dest = swan[0].poll();
        while (true) {
            moveSwan();
            meltGlacier();
            day++;
        }
    }

    private static void moveSwan() {
        while (!swan[day % 2].isEmpty()) {
            Pos s = swan[day % 2].poll();
            if (s.y == dest.y && s.x == dest.x) {
                System.out.println(day);
                System.exit(0);
            }
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + s.y;
                int tx = dx[i] + s.x;
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    continue;
                }
                if (isVisited[ty][tx]) {
                    continue;
                }
                if (map[ty][tx]) {
                    swan[(day + 1) % 2].add(new Pos(ty, tx));
                    isVisited[ty][tx] = true;
                    continue;
                }
                swan[day % 2].add(new Pos(ty, tx));
                isVisited[ty][tx] = true;
            }
        }
    }

    private static void meltGlacier() {
        while (!glacier[day % 2].isEmpty()) {
            Pos g = glacier[day % 2].poll();
            map[g.y][g.x] = false;
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + g.y;
                int tx = dx[i] + g.x;
                if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                    continue;
                }
                if (isPut[ty][tx]) {
                    continue;
                }
                if (map[ty][tx]) {
                    isPut[ty][tx] = true;
                    glacier[(day + 1) % 2].add(new Pos(ty, tx));
                }
            }
        }
    }

    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
