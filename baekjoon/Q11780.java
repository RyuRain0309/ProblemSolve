package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q11780 {

    static int N, M;
    static int[][] map;
    static ArrayList<Integer>[][] before;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        before = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                before[i][j] = new ArrayList<Integer>();
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
                        before[j][k].clear();
                        for (int x = 0; x < before[j][i].size(); x++) {
                            before[j][k].add(before[j][i].get(x));
                        }
                        before[j][k].add(i);
                        for (int x = 0; x < before[i][k].size(); x++) {
                            before[j][k].add(before[i][k].get(x));
                        }
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
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == Integer.MAX_VALUE) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(before[i][j].size() + 2).append(" ");
                    sb.append(i).append(" ");
                    for (int k = 0; k < before[i][j].size(); k++) {
                        sb.append(before[i][j].get(k)).append(" ");
                    }
                    sb.append(j).append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}
