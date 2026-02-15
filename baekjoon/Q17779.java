package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17779 {

    static int N;
    static int[][] map;
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0 && j == N - 1) {
                    continue;
                }
                if (i == N - 1 && j == 0) {
                    continue;
                }
                if (i == N - 1 && j == N - 1) {
                    continue;
                }
                dfs(i, j);
            }
        }
        System.out.println(res);
    }

    private static void dfs(int y, int x) {
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (x + i + j >= N) {
                    continue;
                }
                if (y - i < 0 || y + j >= N) {
                    continue;
                }
                calc(y, x, i, j);
            }
        }
    }

    private static void calc(int y, int x, int d1, int d2) {
        int[][] tempMap = new int[N][N];

        for (int i = 0; i <= d1; i++) {
            tempMap[y - i][x + i] = 5;
            tempMap[y + d2 - i][x + d2 + i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            tempMap[y + i][x + i] = 5;
            tempMap[y - d1 + i][x + d1 + i] = 5;
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j <= x + d1; j++) {
                if (tempMap[i][j] == 5) {
                    break;
                }
                tempMap[i][j] = 1;
            }
        }
        for (int i = y; i < N; i++) {
            for (int j = 0; j < x + d2; j++) {
                if (tempMap[i][j] == 5) {
                    break;
                }
                tempMap[i][j] = 2;
            }
        }
        for (int i = 0; i <= y - d1 + d2; i++) {
            for (int j = N - 1; j >= x + d1 + 1; j--) {
                if (tempMap[i][j] == 5) {
                    break;
                }
                tempMap[i][j] = 3;
            }
        }
        for (int i = y - d1 + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= x + d2; j--) {
                if (tempMap[i][j] == 5) {
                    break;
                }
                tempMap[i][j] = 4;
            }
        }

        int[] sum = new int[6];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum[tempMap[i][j]] += map[i][j];
            }
        }
        sum[5] += sum[0];
        int max = sum[1];
        int min = sum[1];
        for (int i = 2; i <= 5; i++) {
            max = Math.max(max, sum[i]);
            min = Math.min(min, sum[i]);
        }
        res = Math.min(res, max - min);
    }
}
