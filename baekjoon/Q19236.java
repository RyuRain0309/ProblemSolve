package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q19236 {
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[][] map = new int[4][4];
    static int[] y = new int[17];
    static int[] x = new int[17];
    static int[] dir = new int[17];
    static boolean[] getEaten = new boolean[17];
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int fishNum = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                map[i][j] = fishNum;
                y[fishNum] = i;
                x[fishNum] = j;
                dir[fishNum] = direction;
            }
        }

        int eatenFish = map[0][0];
        getEaten[eatenFish] = true;
        map[0][0] = -1;
        dir[0] = dir[eatenFish];
        dfs(eatenFish);
        System.out.println(res);
    }

    private static void dfs(int sum) {
        res = Math.max(res, sum);
        fishMove();
        for (int i = 1; i <= 3; i++) {
            int ty = y[0] + dy[dir[0]] * i;
            int tx = x[0] + dx[dir[0]] * i;
            if (ty < 0 || ty >= 4 || tx < 0 || tx >= 4) {
                return;
            }
            if (map[ty][tx] == 0) {
                continue;
            }
            int fishNum = map[ty][tx];

            int[][] temp = new int[4][4];
            int[] tempY = new int[17];
            int[] tempX = new int[17];
            int[] tempDir = new int[17];
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    temp[j][k] = map[j][k];
                }
            }
            for (int j = 0; j < 17; j++) {
                tempY[j] = y[j];
                tempX[j] = x[j];
                tempDir[j] = dir[j];
            }

            getEaten[fishNum] = true;
            map[ty][tx] = -1;
            map[y[0]][x[0]] = 0;
            swap(0, fishNum);
            dir[0] = dir[fishNum];

            dfs(sum + fishNum);

            getEaten[fishNum] = false;
            map = temp;
            y = tempY;
            x = tempX;
            dir = tempDir;
        }
    }

    private static void swap(int a, int b) {
        int tempY = y[a];
        y[a] = y[b];
        y[b] = tempY;
        int tempX = x[a];
        x[a] = x[b];
        x[b] = tempX;
    }

    private static void fishMove() {
        for (int i = 1; i < 17; i++) {
            if (getEaten[i]) {
                continue;
            }
            while (true) {
                int ty = y[i] + dy[dir[i]];
                int tx = x[i] + dx[dir[i]];
                if (ty < 0 || ty >= 4 || tx < 0 || tx >= 4 || map[ty][tx] == -1) {
                    dir[i] += 1;
                    if (dir[i] >= 9) dir[i] = 1;
                    continue;
                }
                if (map[ty][tx] == 0) {
                    map[ty][tx] = i;
                    map[y[i]][x[i]] = 0;
                    y[i] = ty;
                    x[i] = tx;
                    break;
                }
                int destFish = map[ty][tx];
                map[ty][tx] = i;
                map[y[i]][x[i]] = destFish;
                swap(i, destFish);
                break;
            }
        }
    }
}