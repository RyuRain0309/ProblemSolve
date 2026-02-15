package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q9376 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            boolean[][] map = new boolean[H + 2][W + 2];
            boolean[][] door = new boolean[H + 2][W + 2];
            int[][][] cntMap = new int[3][H + 2][W + 2];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < H + 2; j++) {
                    Arrays.fill(cntMap[i][j], Integer.MAX_VALUE);
                }
            }
            int cnt = 1;
            int[][] prisoner = new int[3][2];
            prisoner[0][0] = 0;
            prisoner[0][1] = 0;
            for (int i = 1; i <= H; i++) {
                String s = br.readLine();
                for (int j = 1; j <= W; j++) {
                    if (s.charAt(j - 1) == '$') {
                        prisoner[cnt][0] = i;
                        prisoner[cnt][1] = j;
                        cnt++;
                        continue;
                    }
                    if (s.charAt(j - 1) == '*') {
                        map[i][j] = true;
                    }
                    if (s.charAt(j - 1) == '#') {
                        door[i][j] = true;
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{prisoner[i][0], prisoner[i][1], 0});
                cntMap[i][prisoner[i][0]][prisoner[i][1]] = 0;
                while (!q.isEmpty()) {
                    int[] pos = q.poll();
                    for (int j = 0; j < 4; j++) {
                        int ty = dy[j] + pos[0];
                        int tx = dx[j] + pos[1];
                        if (ty < 0 || ty >= H + 2 || tx < 0 || tx >= W + 2) {
                            continue;
                        }
                        int tempCnt = pos[2];
                        if (door[ty][tx]) {
                            tempCnt++;
                        }
                        if (cntMap[i][ty][tx] <= tempCnt || map[ty][tx]) {
                            continue;
                        }
                        cntMap[i][ty][tx] = tempCnt;
                        q.add(new int[]{ty, tx, tempCnt});
                    }
                }
            }

            int res = Integer.MAX_VALUE;
            for (int i = 0; i < H + 2; i++) {
                for (int j = 0; j < W + 2; j++) {
                    if (map[i][j] || cntMap[0][i][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (door[i][j]) {
                        res = Math.min(res, cntMap[0][i][j] + cntMap[1][i][j] + cntMap[2][i][j] - 2);
                    }
                    res = Math.min(res, cntMap[0][i][j] + cntMap[1][i][j] + cntMap[2][i][j]);

                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}
