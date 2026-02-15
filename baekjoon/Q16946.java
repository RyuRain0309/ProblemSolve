package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16946 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int N;
    static int M;
    static int[][] map;
    static int[][] setMap;
    static int[][] res;

    static ArrayList<Integer> setList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        setMap = new int[N][M];
        res = new int[N][M];
        setList.add(-1);
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
                if (map[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }
        int set = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (setMap[i][j] == 0 && map[i][j] == 0) {
                    bfs(i, j, set);
                    set++;
                }
            }
        }
        ArrayList<Integer> useSet = new ArrayList<>();
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            useSet.clear();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos[0];
                int tx = dx[i] + pos[1];
                if (ty < 0 || ty >= N || tx < 0 || tx >= M) {
                    continue;
                }
                if (setMap[ty][tx] == 0 || useSet.contains(setMap[ty][tx])) {
                    continue;
                }
                useSet.add(setMap[ty][tx]);
                map[pos[0]][pos[1]] += setList.get(setMap[ty][tx]) % 10;
                map[pos[0]][pos[1]] %= 10;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs(int y, int x, int set) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        setMap[y][x] = set;
        int cnt = 0;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos[0];
                int tx = dx[i] + pos[1];
                if (ty < 0 || ty >= N || tx < 0 || tx >= M) {
                    continue;
                }
                if (setMap[ty][tx] != 0 || map[ty][tx] != 0) {
                    continue;
                }
                setMap[ty][tx] = set;
                q.add(new int[]{ty, tx});
            }
        }
        setList.add(cnt);
    }
}
