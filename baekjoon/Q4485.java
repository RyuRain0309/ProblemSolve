package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q4485 {

    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int problem = 1;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            int res = solve(N, br);

            sb.append("Problem ").append(problem++).append(": ").append(res).append("\n");
        }
        System.out.print(sb);
    }

    private static int solve(int N, BufferedReader br) throws IOException {
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        q.offer(new int[]{0, 0, map[0][0]});
        visited[0][0] = map[0][0];
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == N - 1 && cur[1] == N - 1) {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int y = cur[0] + dy[i];
                int x = cur[1] + dx[i];
                if (x < 0 || y < 0 || x >= N || y >= N) {
                    continue;
                }

                int rupee = cur[2] + map[y][x];
                if (visited[y][x] <= rupee) {
                    continue;
                }

                visited[y][x] = rupee;
                q.offer(new int[]{y, x, rupee});
            }
        }
        return -1;
    }
}
