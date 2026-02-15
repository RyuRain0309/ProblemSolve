package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15686 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N, M;
    static int[][] map;
    static int res = Integer.MAX_VALUE;
    static ArrayList<ChickenShop> chickenShops = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int token = Integer.parseInt(st.nextToken());
                if (token == 2) {
                    chickenShops.add(new ChickenShop(i, j));
                    map[i][j] = 0;
                    continue;
                }
                map[i][j] = token;
            }
        }
        dfs(0, 0, 0);

        System.out.println(res);
    }

    static void dfs(int depth, int cnt, int bit) {
        if (cnt == M) {
            bfs(bit);
            return;
        }
        if (depth == chickenShops.size()) {
            return;
        }
        dfs(depth + 1, cnt + 1, bit | (1 << depth));
        dfs(depth + 1, cnt, bit);
    }

    private static void bfs(int bit) {
        ChickenShop[] remain = new ChickenShop[M];
        int index = 0;
        int chickenDist = 0;
        for (int i = 0; i < chickenShops.size(); i++) {
            if ((bit & (1 << i)) == 0) {
                continue;
            }
            remain[index++] = chickenShops.get(i);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    int temp = Integer.MAX_VALUE;
                    for (ChickenShop chickenShop : remain) {
                        temp = Math.min(temp, getDist(i, j, chickenShop));
                    }
                    chickenDist += temp;
                }
            }
        }
        res = Math.min(res, chickenDist);
    }

    private static int getDist(int y, int x, ChickenShop chickenShop) {
        return Math.abs(y - chickenShop.y) + Math.abs(x - chickenShop.x);
    }

    private static class ChickenShop {
        int y, x;

        public ChickenShop(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
