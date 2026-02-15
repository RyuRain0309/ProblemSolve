package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q25682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] blackBoard = new int[N][M];
        int[][] whiteBoard = new int[N][M];
        int K = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == 'W') {
                    if (i % 2 == j % 2) {
                        whiteBoard[i][j] = 0;
                        blackBoard[i][j] = 1;
                    } else {
                        whiteBoard[i][j] = 1;
                        blackBoard[i][j] = 0;
                    }
                } else {
                    if (i % 2 == j % 2) {
                        whiteBoard[i][j] = 1;
                        blackBoard[i][j] = 0;
                    } else {
                        whiteBoard[i][j] = 0;
                        blackBoard[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 1; i < M; i++) {
            whiteBoard[0][i] += whiteBoard[0][i - 1];
            blackBoard[0][i] += blackBoard[0][i - 1];
        }
        for (int i = 1; i < N; i++) {
            whiteBoard[i][0] += whiteBoard[i - 1][0];
            blackBoard[i][0] += blackBoard[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                whiteBoard[i][j] += whiteBoard[i - 1][j] + whiteBoard[i][j - 1] - whiteBoard[i - 1][j - 1];
                blackBoard[i][j] += blackBoard[i - 1][j] + blackBoard[i][j - 1] - blackBoard[i - 1][j - 1];
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N - K; i++) {
            for (int j = 0; j < M - K; j++) {
                if (i == 0 && j == 0) {
                    res = Math.min(whiteBoard[i + K][j + K], blackBoard[i + K][j + K]);
                } else if (i == 0) {
                    res = Math.min(whiteBoard[i + K][j + K] - whiteBoard[i + K][j - 1], res);
                    res = Math.min(blackBoard[i + K][j + K] - blackBoard[i + K][j - 1], res);
                } else if (j == 0) {
                    res = Math.min(whiteBoard[i + K][j + K] - whiteBoard[i - 1][j + K], res);
                    res = Math.min(blackBoard[i + K][j + K] - blackBoard[i - 1][j + K], res);
                } else {
                    res = Math.min(whiteBoard[i + K][j + K] - whiteBoard[i - 1][j + K] - whiteBoard[i + K][j - 1] + whiteBoard[i - 1][j - 1], res);
                    res = Math.min(blackBoard[i + K][j + K] - blackBoard[i - 1][j + K] - blackBoard[i + K][j - 1] + blackBoard[i - 1][j - 1], res);
                }
            }
        }
        System.out.print(res);
    }
}
