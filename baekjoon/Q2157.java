package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2157 {
    static ArrayList<ArrayList<int[]>> air = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            air.add(new ArrayList<>());
        }
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][M];
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            if (from > to) {
                continue;
            }
            air.get(from).add(new int[]{to, score});
        }
        for (int[] plane : air.get(1)) {
            dp[plane[0]][1] = Math.max(dp[plane[0]][1], plane[1]);
        }
        for (int i = 2; i <= N; i++) {
            for (int[] plane : air.get(i)) {
                for (int j = 2; j < M; j++) {
                    if (dp[i][j - 1] == 0) {
                        continue;
                    }
                    dp[plane[0]][j] = Math.max(dp[plane[0]][j], dp[i][j - 1] + plane[1]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i < M; i++) {
            res = Math.max(res, dp[N][i]);
        }
        System.out.println(res);
    }
}
