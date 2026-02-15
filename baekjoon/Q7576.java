package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7576 {
    static int[] rangeX = {1, 0, -1, 0};
    static int[] rangeY = {0, 1, 0, -1};
    static int N;
    static int M;
    static boolean[][] isVisited;
    static int[][] box;
    static int[][] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        cnt = new int[N][M];
        isVisited = new boolean[N][M];
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            Arrays.fill(cnt[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == -1) {
                    cnt[i][j] = 0;
                } else if (box[i][j] == 1) {
                    q.add(new Pair(i, j, 0));
                    isVisited[i][j] = true;
                }
            }
        }
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            cnt[pair.y][pair.x] = pair.cnt;
            for (int i = 0; i < 4; i++) {
                int moveY = pair.y + rangeY[i];
                int moveX = pair.x + rangeX[i];
                if (moveY < 0 || moveX < 0 || moveY >= N || moveX >= M) {
                    continue;
                }
                if (!isVisited[moveY][moveX] && box[moveY][moveX] == 0) {
                    q.add(new Pair(moveY, moveX, pair.cnt + 1));
                    isVisited[moveY][moveX] = true;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, cnt[i][j]);
            }
        }
//        for(int i = 0 ; i < N ; i++){
//            for(int j = 0 ; j < M ; j++){
//                System.out.print(cnt[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static class Pair {
        private final int y;
        private final int x;
        private final int cnt;

        Pair(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
