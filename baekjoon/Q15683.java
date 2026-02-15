package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15683 {

    static final int[][] loop = {{0, 4, 2, 4, 4, 1}, {0, 1, 2, 2, 3, 4}};
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static int H;
    static int W;
    static int res = Integer.MAX_VALUE;
    static boolean[][] map;
    static int[][] resMap;
    static ArrayList<CCTV> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];
        resMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 6) {
                    map[i][j] = true;
                    continue;
                }
                if (temp != 0) {
                    arr.add(new CCTV(i, j, temp));
                    resMap[i][j]++;
                }
            }
        }
        dfs(0);
        System.out.println(res);

    }

    private static void dfs(int depth) {
        if (depth >= arr.size()) {
            res = Math.min(res, getSize());
            return;
        }
        CCTV cctv = arr.get(depth);
        for (int i = 0; i < loop[0][cctv.type]; i++) {
            for (int j = 0; j < loop[1][cctv.type]; j++) {
                if (cctv.type == 2) {
                    checking(cctv.y, cctv.x, dy[(i + 2 * j) % 4], dx[(i + 2 * j) % 4], true);
                    continue;
                }
                checking(cctv.y, cctv.x, dy[(i + j) % 4], dx[(i + j) % 4], true);
            }

            dfs(depth + 1);

            for (int j = 0; j < loop[1][cctv.type]; j++) {
                if (cctv.type == 2) {
                    checking(cctv.y, cctv.x, dy[(i + 2 * j) % 4], dx[(i + 2 * j) % 4], false);
                    continue;
                }
                checking(cctv.y, cctv.x, dy[(i + j) % 4], dx[(i + j) % 4], false);
            }
        }
    }

    private static void checking(int y, int x, int iy, int ix, boolean b) {
        while (y >= 0 && y < H && x >= 0 && x < W && !map[y][x]) {
            resMap[y][x] += b ? 1 : -1;
            y += iy;
            x += ix;
        }
    }

    private static int getSize() {
        int res = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (resMap[i][j] == 0 && !map[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }

    static class CCTV {
        int y, x, type;

        public CCTV(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }
}
