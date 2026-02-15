package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q1938 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] degree = {{0, 0, -1, 1}, {-1, 1, 0, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        Queue<Log> q = new LinkedList<>();
        Log end = new Log(-1, -1, -1, -1);
        boolean bFlag = false, eFlag = false;
        boolean[][][] isVisited = new boolean[N][N][2];
        int preB = -1, preE = -1;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                if (c == 'B') {
                    if (!bFlag) {
                        bFlag = true;
                        preB = i;
                    } else {
                        bFlag = false;
                        if (preB == i) {
                            isVisited[i][j][0] = true;
                            q.add(new Log(i, j, 0, 0));
                        } else {
                            isVisited[i][j][1] = true;
                            q.add(new Log(i, j, 1, 0));
                        }
                    }
                } else if (c == 'E') {
                    if (!eFlag) {
                        eFlag = true;
                        preE = i;
                    } else {
                        eFlag = false;
                        if (preE == i) {
                            end = new Log(i, j, 0, 0);
                        } else {
                            end = new Log(i, j, 1, 0);
                        }
                    }
                } else {
                    map[i][j] = c - '0';
                }
            }
        }
        while (!q.isEmpty()) {
            Log log = q.poll();
            //UDLR
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + log.y;
                int ty2 = ty + degree[log.degree][0];
                int ty3 = ty + degree[log.degree][1];
                int tx = dx[i] + log.x;
                int tx2 = tx + degree[log.degree][2];
                int tx3 = tx + degree[log.degree][3];
                if (ty2 < 0 || ty3 >= N || tx2 < 0 || tx3 >= N) {
                    continue;
                }
                if (isVisited[ty][tx][log.degree]) {
                    continue;
                }
                if (map[ty][tx] == 1 || map[ty2][tx2] == 1 || map[ty3][tx3] == 1) {
                    continue;
                }
                if (ty == end.y && tx == end.x && log.degree == end.degree) {
                    System.out.println(log.cnt + 1);
                    System.exit(0);
                }
                isVisited[ty][tx][log.degree] = true;
                q.add(new Log(ty, tx, log.degree, log.cnt + 1));
            }

            //T
            if (log.y == 0 || log.x == 0 || log.y == N - 1 || log.x == N - 1) {
                continue;
            }
            if (isVisited[log.y][log.x][(log.degree + 1) % 2]) {
                continue;
            }
            boolean degreeFlag = true;
            for (int i = log.y - 1; i <= log.y + 1; i++) {
                for (int j = log.x - 1; j <= log.x + 1; j++) {
                    if (map[i][j] == 1) {
                        degreeFlag = false;
                        break;
                    }
                }
            }
            if (degreeFlag) {
                if (log.y == end.y && log.x == end.x && log.degree != end.degree) {
                    System.out.println(log.cnt + 1);
                    System.exit(0);
                }
                isVisited[log.y][log.x][(log.degree + 1) % 2] = true;
                q.add(new Log(log.y, log.x, (log.degree + 1) % 2, log.cnt + 1));
            }
        }
        System.out.println(0);
    }

    static class Log {
        int y, x, degree, cnt;

        public Log(int y, int x, int degree, int cnt) {
            this.y = y;
            this.x = x;
            this.degree = degree;
            this.cnt = cnt;
        }
    }
}
