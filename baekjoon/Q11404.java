package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11404 {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            if (time < map[start][dest]) {
                map[start][dest] = time;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j || map[j][i] == Integer.MAX_VALUE) continue;
                for (int k = 1; k <= N; k++) {
                    if (j == k || i == k || map[i][k] == Integer.MAX_VALUE) continue;

                    if (map[j][k] > map[j][i] + map[i][k]) {
                        map[j][k] = map[j][i] + map[i][k];
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(map[i][j] == Integer.MAX_VALUE ? 0 : map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
