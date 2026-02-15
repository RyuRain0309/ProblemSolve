package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2159 {

    static final int[] dy = {0, -1, 1, 0, 0};
    static final int[] dx = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Pos restaurant = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Pos[] customer = new Pos[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            customer[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        long[][] dp = new long[N][5];
        for (int i = 0; i < 5; i++) {
            int ty = customer[0].y + dy[i];
            int tx = customer[0].x + dx[i];
            dp[0][i] = getDist(restaurant.y, restaurant.x, ty, tx);
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 5; j++) {
                int ty = customer[i].y + dy[j];
                int tx = customer[i].x + dx[j];
                long shortDist = Long.MAX_VALUE;
                for (int k = 0; k < 5; k++) {
                    int tty = customer[i - 1].y + dy[k];
                    int ttx = customer[i - 1].x + dx[k];
                    shortDist = Math.min(shortDist, dp[i - 1][k] + getDist(tty, ttx, ty, tx));
                }
                dp[i][j] = shortDist;
            }
        }
        long res = Long.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            res = Math.min(res, dp[N - 1][i]);
        }
        System.out.println(res);
    }

    static int getDist(int y, int x, int y2, int x2) {
        return Math.abs(x - x2) + Math.abs(y - y2);
    }

    static private class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
