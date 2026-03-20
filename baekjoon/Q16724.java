package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16724 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int N, M, result = 0;
    static int[][] map;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = "UDLR".indexOf(s.charAt(j));
            }
        }

        int id = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] != 0) {
                    continue;
                }
                dfs(i, j, id++);
            }
        }
        System.out.println(result);
    }

    private static void dfs(int y, int x, int id) {
        visited[y][x] = id;

        int ty = y + dy[map[y][x]];
        int tx = x + dx[map[y][x]];
        if (visited[ty][tx] == 0) {
            dfs(ty, tx, id);
            return;
        }
        if (visited[ty][tx] == id) {
            result++;
        }
    }
}
