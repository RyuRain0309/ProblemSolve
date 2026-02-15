package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1981 {

    static final int[] dy = {1, 0, -1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }
        int end = max - min;
        int start = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            boolean flag = true;
            for (int i = min; i + mid <= max; i++) {
                if (can(i, i + mid)) {
                    end = mid;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }

    private static boolean can(int min, int max) {
        boolean[][] isVisit = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        if (map[0][0] < min || map[0][0] > max) return false;
        q.add(new int[]{0, 0});
        isVisit[0][0] = true;
        while (!q.isEmpty()) {
            final int[] pos = q.poll();
            if (pos[0] == N - 1 && pos[1] == N - 1) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int ty = pos[0] + dy[i];
                int tx = pos[1] + dx[i];
                if (ty < 0 || ty >= N || tx < 0 || tx >= N) {
                    continue;
                }
                if (isVisit[ty][tx] || map[ty][tx] < min || map[ty][tx] > max) {
                    continue;
                }
                q.add(new int[]{ty, tx});
                isVisit[ty][tx] = true;
            }
        }
        return false;
    }
}
