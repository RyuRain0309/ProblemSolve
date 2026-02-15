package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1956 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] map = new int[V + 1][V + 1];
        for (int i = 0; i <= V; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            map[start][end] = dist;
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j || map[j][i] == Integer.MAX_VALUE) continue;
                for (int k = 1; k <= V; k++) {
                    if (i == k || j == k || map[i][k] == Integer.MAX_VALUE) continue;

                    if (map[j][k] > map[j][i] + map[i][k]) {
                        map[j][k] = map[j][i] + map[i][k];
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            for (int j = i + 1; j <= V; j++) {
                if (map[i][j] == Integer.MAX_VALUE) continue;
                if (map[j][i] == Integer.MAX_VALUE) continue;

                ans = Math.min(ans, map[i][j] + map[j][i]);
            }
        }
        System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
