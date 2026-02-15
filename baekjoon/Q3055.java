package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3055 {
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, -1, 0, 1};

    static int[] D = new int[2];
    static int[] S = new int[3];
    static int[][] forest;

    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        forest = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (c[j] == '*') forest[i][j] = 2;
                else if (c[j] == 'X') forest[i][j] = 1;
                else if (c[j] == 'D') {
                    D[0] = i;
                    D[1] = j;
                } else if (c[j] == 'S') {
                    S[0] = i;
                    S[1] = j;
                    S[2] = 0;
                }
            }
        }
        int time = -1;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[R][C];
        q.add(S);
        isVisited[S[0]][S[1]] = true;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            if (pos[0] == D[0] && pos[1] == D[1]) {
                System.out.println(pos[2]);
                return;
            }
            if (pos[2] > time) {
                flow();
                time = pos[2];
            }
            for (int i = 0; i < 4; i++) {
                int ty = pos[0] + dy[i];
                int tx = pos[1] + dx[i];
                if (ty < 0 || ty >= R || tx < 0 || tx >= C || forest[ty][tx] != 0) {
                    continue;
                }
                if (isVisited[ty][tx]) {
                    continue;
                }
                q.add(new int[]{ty, tx, pos[2] + 1});
                isVisited[ty][tx] = true;
            }
        }
        System.out.println("KAKTUS");
    }

    private static void flow() {
        boolean[][] isVisited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (forest[i][j] == 2 && !isVisited[i][j]) {
                    for (int k = 0; k < 4; k++) {
                        int ty = i + dy[k];
                        int tx = j + dx[k];
                        if (ty < 0 || ty >= R || tx < 0 || tx >= C || forest[ty][tx] != 0) {
                            continue;
                        }
                        if (ty == D[0] && tx == D[1]) {
                            continue;
                        }
                        forest[ty][tx] = 2;
                        isVisited[ty][tx] = true;
                    }
                }
            }
        }
    }
}
