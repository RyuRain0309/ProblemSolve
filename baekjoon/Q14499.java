package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14499 {

    static final int[] dy = {0, 0, 0, -1, 1};
    static final int[] dx = {0, 1, -1, 0, 0};
    static final int[][] dDice = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 4, 2, 1, 6, 5, 3},
            {0, 3, 2, 6, 1, 5, 4},
            {0, 5, 1, 3, 4, 6, 2},
            {0, 2, 6, 3, 4, 1, 5}
    };
    static int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());
            int ty = y + dy[dir];
            int tx = x + dx[dir];
            if (ty < 0 || ty >= N || tx < 0 || tx >= M) {
                continue;
            }
            y = ty;
            x = tx;
            moveDice(dir);
            if (map[y][x] == 0) {
                map[y][x] = dice[6];
            } else {
                dice[6] = map[y][x];
                map[y][x] = 0;
            }
            sb.append(dice[1]).append("\n");
        }
        System.out.print(sb);
    }

    private static void moveDice(int dir) {
        int[] temp = new int[7];
        for (int i = 1; i < 7; i++) {
            temp[i] = dice[dDice[dir][i]];
        }
        dice = temp;
    }
}
