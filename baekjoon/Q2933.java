package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2933 {

    static final int[] dy = {1, 0, 0, -1};
    static final int[] dx = {0, -1, 1, 0};
    static int H, W;
    static boolean[][] mineral;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        mineral = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                char c = s.charAt(j);
                if (c == 'x') {
                    mineral[i][j] = true;
                }
            }
        }
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            throwStick(i % 2, H - h);
            fallMineral();
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(mineral[i][j] ? "x" : ".");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void fallMineral() {
        isVisited = new boolean[H][W];
        for (int i = H - 1; i >= 0; i--) {
            for (int j = 0; j < W; j++) {
                if (isVisited[i][j] || !mineral[i][j]) {
                    continue;
                }
                bfs(i, j);
            }
        }
    }

    private static void bfs(int y, int x) {
        int minFall = getMinFall(y, x);
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> q2 = new LinkedList<>();
        mineral[y][x] = false;
        q.add(new int[]{y, x});
        q2.add(new int[]{y, x});
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = dy[i] + pos[0];
                int tx = dx[i] + pos[1];
                if (ty < 0 || tx < 0 || ty >= H || tx >= W) {
                    continue;
                }
                if (!mineral[ty][tx] || isVisited[ty][tx]) {
                    continue;
                }
                mineral[ty][tx] = false;
                q.add(new int[]{ty, tx});
                q2.add(new int[]{ty, tx});
                minFall = Math.min(minFall, getMinFall(ty, tx));
            }
        }

        while (!q2.isEmpty()) {
            int[] pos = q2.poll();
            int ty = pos[0] + minFall;
            int tx = pos[1];
            mineral[ty][tx] = true;
            isVisited[ty][tx] = true;
        }
    }

    private static int getMinFall(int y, int x) {
        int cnt = 0;
        while (true) {
            y++;
            if (y >= H || (mineral[y][x] && isVisited[y][x])) {
                return cnt;
            }
            cnt++;
        }
    }

    private static void throwStick(int dir, int height) {
        if (dir == 0) {
            for (int i = 0; i < W; i++) {
                if (mineral[height][i]) {
                    mineral[height][i] = false;
                    return;
                }
            }
        }
        if (dir == 1) {
            for (int i = W - 1; i >= 0; i--) {
                if (mineral[height][i]) {
                    mineral[height][i] = false;
                    return;
                }
            }
        }


    }
}
