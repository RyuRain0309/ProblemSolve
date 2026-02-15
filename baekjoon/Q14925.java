package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14925 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        boolean[][] hurdle = new boolean[H][W];
        int[][] sum = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                if (Integer.parseInt(st.nextToken()) != 0) {
                    hurdle[i][j] = true;
                }
            }
        }

        for (int i = 0; i < H; i++) {
            if (hurdle[i][0]) {
                continue;
            }
            sum[i][0] = 1;
        }
        for (int i = 0; i < W; i++) {
            if (hurdle[0][i]) {
                continue;
            }
            sum[0][i] = 1;
        }

        for (int i = 1; i < H; i++) {
            for (int j = 1; j < W; j++) {
                if (hurdle[i][j]) {
                    continue;
                }
                int min = Math.min(sum[i - 1][j - 1], Math.min(sum[i - 1][j], sum[i][j - 1]));
                sum[i][j] = min + 1;
            }
        }

        int res = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                res = Math.max(res, sum[i][j]);
            }
        }
        System.out.println(res);
    }
}
