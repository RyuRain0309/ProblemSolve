package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17822 {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int N, M, K;
    static int[][] disk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        disk = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                disk[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int j = x - 1; j < N; j += x) {
                if (d == 1) {
                    turnDisk(j, k);
                } else {
                    turnDisk(j, M - k);
                }
            }

            boolean flag = false;
            boolean[][] isNear = new boolean[N][M];
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < M; b++) {
                    if (disk[a][b] == -1 || isNear[a][b]) {
                        continue;
                    }
                    for (int c = 0; c < 4; c++) {
                        int ty = a + dy[c];
                        int tx = b + dx[c];

                        if (tx < 0) tx = M - 1;
                        if (tx >= M) tx = 0;

                        if (ty < 0 || ty >= N || disk[ty][tx] == -1) {
                            continue;
                        }
                        if (disk[a][b] == disk[ty][tx]) {
                            flag = true;
                            isNear[a][b] = true;
                            isNear[ty][tx] = true;
                        }
                    }
                }
            }
            if (flag) {
                for (int a = 0; a < N; a++) {
                    for (int b = 0; b < M; b++) {
                        if (isNear[a][b]) {
                            disk[a][b] = -1;
                        }
                    }
                }
            } else {
                int total = 0;
                int cnt = 0;
                for (int a = 0; a < N; a++) {
                    for (int b = 0; b < M; b++) {
                        if (disk[a][b] == -1) {
                            continue;
                        }
                        total += disk[a][b];
                        cnt++;
                    }
                }
                float avg = (float) total / cnt;
                for (int a = 0; a < N; a++) {
                    for (int b = 0; b < M; b++) {
                        if (disk[a][b] == -1) {
                            continue;
                        }
                        if (disk[a][b] > avg) {
                            disk[a][b] -= 1;
                        } else if (disk[a][b] < avg) {
                            disk[a][b] += 1;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < M; b++) {
                if (disk[a][b] == -1) {
                    continue;
                }
                ans += disk[a][b];
            }
        }
        System.out.println(ans);
    }

    private static void turnDisk(int y, int times) {
        int[] temp = new int[M];
        for (int i = 0; i < M; i++) {
            int dist = (i + times) % M;
            temp[i] = disk[y][dist];
        }
        for (int i = 0; i < M; i++) {
            disk[y][i] = temp[i];
        }
    }
}
