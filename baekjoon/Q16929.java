package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16929 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N, M;
    static char c;
    static char[][] map;
    static int[][] whenVisited;

    public static void main(String[] args) throws

            IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        whenVisited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (whenVisited[i][j] == 0) {
                    c = map[i][j];
                    dfs(i, j, 1);
                }
            }
        }
        System.out.println("No");
    }

    private static void dfs(int y, int x, int depth) {
        whenVisited[y][x] = depth;
        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];
            if (!isRange(ty, tx)) {
                continue;
            }
            if (map[ty][tx] != c) {
                continue;
            }
            if (whenVisited[ty][tx] == 0) {
                dfs(ty, tx, depth + 1);
            }
            if (whenVisited[ty][tx] - whenVisited[y][x] >= 3) {
                System.out.println("Yes");
                System.exit(0);
            }
        }
    }

    private static boolean isRange(int ty, int tx) {
        return ty >= 0 && ty < N && tx >= 0 && tx < M;
    }
}
