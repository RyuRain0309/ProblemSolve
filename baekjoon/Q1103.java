package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1103 {

    static final int[] dy = {1, -1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int H, W;
    static int[][] board;
    static int[][] dp;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        board = new int[H][W];
        dp = new int[H][W];
        isVisited = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                if (s.charAt(j) == 'H') {
                    board[i][j] = 0;
                    continue;
                }
                board[i][j] = s.charAt(j) - '0';
            }
        }
        isVisited[0][0] = true;
        dp[0][0] = dfs(0, 0);
        System.out.println(dp[0][0]);
    }

    private static int dfs(int y, int x) {
        int max = 1;
        int X = board[y][x];
        for (int i = 0; i < 4; i++) {
            int ty = dy[i] * X + y;
            int tx = dx[i] * X + x;
            if (ty < 0 || ty >= H || tx < 0 || tx >= W) {
                continue;
            }
            if (board[ty][tx] == 0) {
                continue;
            }
            if (isVisited[ty][tx]) {
                System.out.println(-1);
                System.exit(0);
            }
            if (dp[ty][tx] == 0) {
                isVisited[ty][tx] = true;
                max = Math.max(max, dfs(ty, tx) + 1);
                isVisited[ty][tx] = false;
            } else {
                max = Math.max(max, dp[ty][tx] + 1);
            }

        }
        return dp[y][x] = max;
    }
}
