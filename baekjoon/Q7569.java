package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7569 {
    static int[] rangeX = {1, 0, -1, 0, 0, 0};
    static int[] rangeY = {0, 1, 0, -1, 0, 0};
    static int[] ramgeZ = {0, 0, 0, 0, 1, -1};
    static int N;
    static int M;
    static int H;
    static boolean[][][] isVisited;
    static int[][][] box;
    static int[][][] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[H][N][M];
        cnt = new int[H][N][M];
        isVisited = new boolean[H][N][M];
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(cnt[i][j], Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < M; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if (box[i][j][k] == -1) {
                        cnt[i][j][k] = 0;
                    } else if (box[i][j][k] == 1) {
                        q.add(new Pair(i, j, k, 0));
                        isVisited[i][j][k] = true;
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            cnt[pair.z][pair.y][pair.x] = pair.cnt;
            for (int i = 0; i < 6; i++) {
                int moveY = pair.y + rangeY[i];
                int moveX = pair.x + rangeX[i];
                int moveZ = pair.z + ramgeZ[i];
                if (moveY < 0 || moveX < 0 || moveZ < 0 || moveY >= N || moveX >= M || moveZ >= H) {
                    continue;
                }
                if (!isVisited[moveZ][moveY][moveX] && box[moveZ][moveY][moveX] == 0) {
                    q.add(new Pair(moveZ, moveY, moveX, pair.cnt + 1));
                    isVisited[moveZ][moveY][moveX] = true;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    ans = Math.max(ans, cnt[i][j][k]);
                }
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
        private final int z;
        private final int y;
        private final int x;
        private final int cnt;

        Pair(int z, int y, int x, int cnt) {
            this.z = z;
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
