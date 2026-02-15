package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2636 {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int H, W;
    static boolean[][] cheese;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        cheese = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }
        int preCnt = cntCheese();
        if (preCnt == 0) {
            System.out.println(0 + "\n" + 0);
            return;
        }
        int time = 0;
        while (true) {
            melt();
            time++;
            int cnt = cntCheese();
            if (cnt == 0) {
                System.out.println(time + "\n" + preCnt);
                break;
            }
            preCnt = cnt;
        }
    }

    private static int cntCheese() {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (cheese[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    private static void melt() {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> melted = new LinkedList<>();
        isVisited = new boolean[H][W];
        isVisited[0][0] = true;
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos[0];
                int tx = dx[i] + pos[1];
                if (ty < 0 || tx < 0 || ty >= H || tx >= W) {
                    continue;
                }
                if (isVisited[ty][tx]) {
                    continue;
                }
                if (cheese[ty][tx]) {
                    isVisited[ty][tx] = true;
                    melted.add(new int[]{ty, tx});
                    continue;
                }
                isVisited[ty][tx] = true;
                q.add(new int[]{ty, tx});
            }
        }
        while (!melted.isEmpty()) {
            int[] pos = melted.poll();
            cheese[pos[0]][pos[1]] = false;
        }
    }
}
