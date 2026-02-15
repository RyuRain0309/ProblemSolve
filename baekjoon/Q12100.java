package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12100 {
    static int N;
    static int[][][] map;
    static int res = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[6][N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[0][i][j] = Integer.parseInt(st.nextToken());
                res = Math.max(res, map[0][i][j]);
            }
        }
        dfs(1);
        System.out.println(res);
    }

    private static void dfs(int depth) {
        if (depth > 5) {
            checkingMax();
            return;
        }

        for (int i = 0; i < 4; i++) {
            copyMap(depth);
            promise(depth, i);
            dfs(depth + 1);
        }
    }

    private static void checkingMax() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(res, map[5][i][j]);
            }
        }
    }

    private static void copyMap(int depth) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[depth - 1][i], 0, map[depth][i], 0, N);
        }
    }

    private static void promise(int depth, int dir) {
        //아래
        if (dir == 0) {
            for (int i = 0; i < N; i++) {
                int block = map[depth][N - 1][i];
                int index = N - 1;
                for (int j = N - 2; j >= 0; j--) {
                    if (map[depth][j][i] == 0) {
                        continue;
                    }
                    if (block == 0) {
                        map[depth][index][i] = map[depth][j][i];
                        block = map[depth][j][i];
                        map[depth][j][i] = 0;
                        continue;
                    }
                    if (map[depth][j][i] == block) {
                        map[depth][index][i] += block;
                        map[depth][j][i] = 0;
                        block = 0;
                        index--;
                    } else {
                        index--;
                        int temp = map[depth][j][i];
                        map[depth][j][i] = 0;
                        map[depth][index][i] = temp;
                        block = temp;
                    }
                }
            }
        }

        if (dir == 1) {
            for (int i = 0; i < N; i++) {
                int block = map[depth][0][i];
                int index = 0;
                for (int j = 1; j < N; j++) {
                    if (map[depth][j][i] == 0) {
                        continue;
                    }
                    if (block == 0) {
                        map[depth][index][i] = map[depth][j][i];
                        block = map[depth][j][i];
                        map[depth][j][i] = 0;
                        continue;
                    }
                    if (map[depth][j][i] == block) {
                        map[depth][index][i] += block;
                        map[depth][j][i] = 0;
                        block = 0;
                        index++;
                    } else {
                        index++;
                        int temp = map[depth][j][i];
                        map[depth][j][i] = 0;
                        map[depth][index][i] = temp;
                        block = temp;
                    }
                }
            }
        }

        if (dir == 2) {
            for (int i = 0; i < N; i++) {
                int block = map[depth][i][N - 1];
                int index = N - 1;
                for (int j = N - 2; j >= 0; j--) {
                    if (map[depth][i][j] == 0) {
                        continue;
                    }
                    if (block == 0) {
                        map[depth][i][index] = map[depth][i][j];
                        block = map[depth][i][j];
                        map[depth][i][j] = 0;
                        continue;
                    }
                    if (map[depth][i][j] == block) {
                        map[depth][i][index] += block;
                        map[depth][i][j] = 0;
                        block = 0;
                        index--;
                    } else {
                        index--;
                        int temp = map[depth][i][j];
                        map[depth][i][j] = 0;
                        map[depth][i][index] = temp;
                        block = temp;
                    }
                }
            }
        }
        if (dir == 3) {
            for (int i = 0; i < N; i++) {
                int block = map[depth][i][0];
                int index = 0;
                for (int j = 1; j < N; j++) {
                    if (map[depth][i][j] == 0) {
                        continue;
                    }
                    if (block == 0) {
                        map[depth][i][index] = map[depth][i][j];
                        block = map[depth][i][j];
                        map[depth][i][j] = 0;
                        continue;
                    }
                    if (map[depth][i][j] == block) {
                        map[depth][i][index] += block;
                        map[depth][i][j] = 0;
                        block = 0;
                        index++;
                    } else {
                        index++;
                        int temp = map[depth][i][j];
                        map[depth][i][j] = 0;
                        map[depth][i][index] = temp;
                        block = temp;
                    }
                }
            }
        }
    }
}
