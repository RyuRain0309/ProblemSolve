package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1113 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int H, W, res = 0;
    static int[][] pool;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        pool = new int[H][W];
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                pool[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 2; i < 10; i++) {
            isVisited = new boolean[H][W];
            for (int y = 1; y < H - 1; y++) {
                for (int x = 1; x < W - 1; x++) {
                    if (pool[y][x] + 1 == i) {
                        if (isVisited[y][x]) {
                            continue;
                        }
                        if (bfs(new Pos(y, x), i)) {
                            fillWater(new Pos(y, x), i);
                        }
                    }
                }
            }
//            System.out.println(i);
//            for (int j = 0; j < H; j++) {
//                System.out.println(Arrays.toString(pool[j]));
//            }
//            System.out.println();
        }
        System.out.println(res);
    }

    private static void fillWater(Pos p, int water) {
        Queue<Pos> q = new LinkedList<>();
        pool[p.y][p.x] += 1;
        q.add(p);
        res++;
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos.y;
                int tx = dx[i] + pos.x;
                if (pool[ty][tx] + 1 == water) {
                    pool[ty][tx] += 1;
                    q.add(new Pos(ty, tx));
                    res++;
                }
            }
        }
    }

    private static boolean bfs(Pos p, int water) {
        Queue<Pos> q = new LinkedList<>();
        boolean flag = true;
        isVisited[p.y][p.x] = true;
        q.add(p);
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos.y;
                int tx = dx[i] + pos.x;
                if (pool[ty][tx] >= water) {
                    continue;
                }
                if (ty == 0 || ty == H - 1 || tx == 0 || tx == W - 1) {
                    flag = false;
                    continue;
                }
                if (isVisited[ty][tx]) {
                    continue;
                }
                isVisited[ty][tx] = true;
                q.add(new Pos(ty, tx));
            }
        }

        return flag;
    }

    static private class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
