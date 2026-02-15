package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q17142 {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] map;
    static ArrayList<Virus> viruses = new ArrayList<>();

    static int N, M;
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    viruses.add(new Virus(i, j, 0));
                }
            }
        }

        dfs(0, 0, 0);
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static void dfs(int depth, int cnt, int bit) {
        if (cnt == M) {
            bfs(bit);
            return;
        }
        if (depth >= viruses.size()) {
            return;
        }
        dfs(depth + 1, cnt, bit);
        dfs(depth + 1, cnt + 1, bit | 1 << depth);
    }

    private static void bfs(int bit) {
        Queue<Virus> q = new LinkedList<>();
        int[][] time = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(time[i], -1);
        }
        for (int i = 0; i < viruses.size(); i++) {
            if ((bit & 1 << i) == 0) {
                continue;
            }
            Virus virus = viruses.get(i);
            time[virus.y][virus.x] = virus.cnt;
            q.offer(virus);
        }

        while (!q.isEmpty()) {
            Virus virus = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + virus.y;
                int nx = dx[i] + virus.x;
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                    continue;
                }
                if (map[ny][nx] == 1 || time[ny][nx] != -1) {
                    continue;
                }
                time[ny][nx] = virus.cnt + 1;
                q.offer(new Virus(ny, nx, virus.cnt + 1));
            }
        }

        res = Math.min(res, calcMaxTime(time));
    }

    private static int calcMaxTime(int[][] time) {
        int temp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                if (time[i][j] == -1) {
                    return Integer.MAX_VALUE;
                }
                temp = Math.max(temp, time[i][j]);
            }
        }
        return temp;
    }

    static class Virus {
        int y, x, cnt;

        public Virus(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
