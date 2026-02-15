package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17821 {

    static int[][] player;
    static int[] battingOrder = new int[10];

    static int N;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        player = new int[N][10];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 9; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        battingOrder[4] = 1;
        dfs(2);
        System.out.println(res);
    }

    private static void dfs(int depth) {
        if (depth == 10) {
            playGame();
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (battingOrder[i] != 0) {
                continue;
            }
            battingOrder[i] = depth;
            dfs(depth + 1);
            battingOrder[i] = 0;
        }

    }

    private static void playGame() {
        int[] base = new int[8];
        int order = 1;
        for (int i = 0; i < N; i++) {
            int outCount = 0;
            while (outCount < 3) {
                int hit = player[i][battingOrder[order]];
                if (hit == 0) {
                    outCount++;
                } else {
                    base[0] = 1;
                    for (int j = 3; j >= 0; j--) {
                        base[j + hit] += base[j];
                        base[j] = 0;
                    }
                }
                order++;
                if (order > 9) {
                    order = 1;
                }
            }
            for (int j = 0; j <= 3; j++) {
                base[j] = 0;
            }
        }

        int score = 0;
        for (int i = 4; i < 8; i++) {
            score += base[i];
        }
        res = Math.max(res, score);
    }
}
