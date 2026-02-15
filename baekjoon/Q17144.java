package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q17144 {
    static final int[] dy = {1, 0, -1, 0};
    static final int[] dx = {0, -1, 0, 1};
    static int[][] map;
    static int[][] temp;
    static int R, C, T, res;
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        res = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) arr.add(i);
                else res += map[i][j];
            }
        }
        while (T-- > 0) {
            temp = new int[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 0 || map[i][j] == -1) {
                        continue;
                    }
                    diffusion(i, j);
                }
            }
            purify();
            mapWrite();
        }
        System.out.println(res);
    }

    private static void mapWrite() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    private static void purify() {
        int top = arr.get(0);
        for (int i = top; i > 0; i--) {
            temp[i][0] = temp[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            temp[0][i] = temp[0][i + 1];
        }
        for (int i = 0; i < top; i++) {
            temp[i][C - 1] = temp[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            temp[top][i] = temp[top][i - 1];
        }
        int down = arr.get(1);
        for (int i = down; i < R - 1; i++) {
            temp[i][0] = temp[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            temp[R - 1][i] = temp[R - 1][i + 1];
        }
        for (int i = R - 1; i > down; i--) {
            temp[i][C - 1] = temp[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            temp[down][i] = temp[down][i - 1];
        }

        res -= temp[top][0] + temp[down][0];
        temp[top][0] = -1;
        temp[top][1] = 0;
        temp[down][0] = -1;
        temp[down][1] = 0;
    }

    private static void diffusion(int y, int x) {
        int amount = map[y][x] / 5;
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];
            if (ty < 0 || ty >= R || tx < 0 || tx >= C) {
                continue;
            }
            if (map[ty][tx] == -1) {
                continue;
            }
            temp[ty][tx] += amount;
            cnt++;
        }
        temp[y][x] += map[y][x] - (amount * cnt);
    }
}
