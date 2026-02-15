package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1513 {
    static final int DIV = 1_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][][][] dp = new int[C + 1][C + 1][N][M];

        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = i + 1;
        }

        if (map[0][0] == 0) {
            dp[0][0][0][0] = 1;
        } else {
            dp[1][map[0][0]][0][0] = 1;
        }

        for (int i = 1; i < N; i++) {
            if (map[i][0] == 0) {
                for (int j = 0; j < C + 1; j++) {
                    for (int k = 0; k < C + 1; k++) {
                        dp[j][k][i][0] = dp[j][k][i - 1][0];
                    }
                }
            } else {
                for (int j = 1; j < C + 1; j++) {
                    for (int k = 0; k < map[i][0]; k++) {
                        dp[j][map[i][0]][i][0] += dp[j - 1][k][i - 1][0];
                    }
                }
            }
        }

        for (int i = 1; i < M; i++) {
            if (map[0][i] == 0) {
                for (int j = 0; j < C + 1; j++) {
                    for (int k = 0; k < C + 1; k++) {
                        dp[j][k][0][i] = dp[j][k][0][i - 1];
                    }
                }
            } else {
                for (int j = 1; j < C + 1; j++) {
                    for (int k = 0; k < map[0][i]; k++) {
                        dp[j][map[0][i]][0][i] += dp[j - 1][k][0][i - 1];
                    }
                }
            }
        }


        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (map[i][j] == 0) {
                    for (int k = 0; k < C + 1; k++) {
                        for (int l = 0; l < C + 1; l++) {
                            dp[k][l][i][j] = (dp[k][l][i - 1][j] + dp[k][l][i][j - 1]) % DIV;
                        }
                    }
                } else {
                    for (int k = 1; k < C + 1; k++) {
                        for (int l = 0; l < map[i][j]; l++) {
                            dp[k][map[i][j]][i][j] += (dp[k - 1][l][i - 1][j] + dp[k - 1][l][i][j - 1]) % DIV;
                            dp[k][map[i][j]][i][j] %= DIV;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < C + 1; i++) {
            int res = 0;
            for (int j = 0; j < C + 1; j++) {
                res += dp[i][j][N - 1][M - 1];
                res %= DIV;
            }
            sb.append(res).append(" ");
        }
        System.out.println(sb);
    }
}
