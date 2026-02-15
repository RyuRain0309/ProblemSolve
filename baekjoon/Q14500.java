package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14500 {
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int n, m;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                isVisited[i][j] = true;
                recue(i, j, map[i][j], 0);
                isVisited[i][j] = false;
            }
        }

        System.out.println(res);
    }

    private static void recue(int x, int y, int sum, int cnt) {
        if (cnt == 3) {
            res = Math.max(res, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            if (tempX >= 0 && tempX < n && tempY >= 0 && tempY < m) {
                if (!isVisited[tempX][tempY]) {
                    if (cnt == 1) {
                        isVisited[tempX][tempY] = true;
                        recue(x, y, sum + map[tempX][tempY], cnt + 1);
                        isVisited[tempX][tempY] = false;
                    }
                    isVisited[x][y] = true;
                    recue(tempX, tempY, sum + map[tempX][tempY], cnt + 1);
                    isVisited[x][y] = false;
                }
            }
        }
    }

}
