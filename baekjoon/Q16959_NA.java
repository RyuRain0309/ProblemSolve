package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16959_NA {
    static int[][][] chessman = {
            {{-1, -2, -2, -1, 1, 2, 2, 1}, {-2, -1, 1, 2, 2, 1, -1, -2}}, //Knight
            {{-1, 1, 0, 0}, {0, 0, -1, 1}}, // pawn
            {{-1, -1, 1, 1}, {-1, 1, -1, 1}}  // bishop
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        boolean[][][][] isVisited = new boolean[N][N][N * N + 1][3];
        StringTokenizer st;
        int startY = 0, startX = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    startY = i;
                    startX = j;
                }
            }
        }
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(startY, startX, 0, 0, 2));
        isVisited[startY][startX][2][0] = true;
        q.add(new Pos(startY, startX, 0, 1, 2));
        isVisited[startY][startX][2][1] = true;
        q.add(new Pos(startY, startX, 0, 2, 2));
        isVisited[startY][startX][2][2] = true;
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            for (int i = 1; i < 3; i++) {
                int nChessman = (pos.chessman + i) % 3;
                if (isVisited[pos.y][pos.x][pos.next][nChessman]) {
                    continue;
                }
                isVisited[pos.y][pos.x][pos.next][nChessman] = true;
                q.add(new Pos(pos.y, pos.x, pos.cnt + 1, nChessman, pos.next));
            }
            for (int i = 0; i < chessman[pos.chessman][0].length; i++) {
                int ty = pos.y;
                int tx = pos.x;
                boolean flag = false;
                while (true) {
                    ty += chessman[pos.chessman][0][i];
                    tx += chessman[pos.chessman][1][i];
                    if (ty < 0 || ty >= N || tx < 0 || tx >= N) {
                        break;
                    }
                    if (isVisited[ty][tx][pos.next][pos.chessman]) {
                        continue;
                    }
                    if (board[ty][tx] == pos.next) {
                        if (pos.next == N * N) {
                            System.out.println(pos.cnt + 1);
                            return;
                        }
                        isVisited[ty][tx][pos.next + 1][pos.chessman] = true;
                        q.add(new Pos(ty, tx, pos.cnt + 1, pos.chessman, pos.next + 1));
                        continue;
                    }
                    isVisited[ty][tx][pos.next][pos.chessman] = true;
                    q.add(new Pos(ty, tx, pos.cnt + 1, pos.chessman, pos.next));
                    if (pos.chessman == 0) {
                        break;
                    }
                }
            }
        }
    }

    private static class Pos {
        int y, x, cnt, chessman, next;


        public Pos(int y, int x, int cnt, int chessman, int next) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.chessman = chessman;
            this.next = next;
        }
    }
}
