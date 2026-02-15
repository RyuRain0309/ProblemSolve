package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1385 {

    static int[][] map = new int[1500][1500];
    static int[][] root = new int[1500][1500];
    /*  7 2 0
        6 1 3
        0 5 4 */
    static int[] dy = {0, 1, 1, 0, -1, -1};
    static int[] dx = {1, 1, 0, -1, -1, 0};

    static int a, b;
    static int sY = 750;
    static int sX = 750;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        setMap(Math.max(a, b) - 2);

        Queue<Honeycomb> q = new LinkedList<>();
        q.add(new Honeycomb(sY, sX, 0));

        while (!q.isEmpty()) {
            Honeycomb honeycomb = q.poll();
            for (int i = 0; i < 6; i++) {
                int ty = dy[i] + honeycomb.y;
                int tx = dx[i] + honeycomb.x;
                if (ty < 0 || ty >= 1500 || tx < 0 || tx >= 1500) {
                    continue;
                }
                if (map[ty][tx] == 0 || map[ty][tx] == a || root[ty][tx] != 0) {
                    continue;
                }
                root[ty][tx] = honeycomb.cnt + 1;
                q.add(new Honeycomb(ty, tx, root[ty][tx]));
                if (map[ty][tx] == b) {
                    sY = ty;
                    sX = tx;
                    q.clear();
                }
            }
        }
//        for (int i = 730; i < 770; i++) {
//            for (int j = 730; j < 770; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        backTracking(sY, sX);
        System.out.println(root[sY][sX]);
        System.out.println(sb);
    }

    private static void backTracking(int y, int x) {
        if (map[y][x] == a) {
            sb.append(map[y][x]).append(" ");
            return;
        }

        int now = root[y][x];
        for (int i = 0; i < 6; i++) {
            int ty = dy[i] + y;
            int tx = dx[i] + x;
            if (ty < 0 || ty >= 1500 || tx < 0 || tx >= 1500) {
                continue;
            }
            if (map[ty][tx] == 0) {
                continue;
            }
            if (root[ty][tx] == 0) {
                if (now == 1 && map[ty][tx] == a) {
                    backTracking(ty, tx);
                    sb.append(map[y][x]).append(" ");
                    return;
                }
                continue;
            }
            if (root[ty][tx] == now - 1) {
                backTracking(ty, tx);
                sb.append(map[y][x]).append(" ");
                return;
            }
        }
    }

    private static void setMap(int max) {
        int until = 1;
        for (int i = 1; true; i++) {
            until += 6 * i;
            if (until > max) {
                break;
            }
        }

        until = Math.min(until, 1_000_000);

        int y = 750;
        int x = 750;
        int cnt = 1;
        int line = 1;
        map[y][x] = cnt++;
        while (cnt <= until) {
            map[--y][x] = cnt++;
            for (int i = 0; i < 6; i++) {
                int repeat = line;
                if (i == 0) {
                    repeat -= 1;
                }
                for (int j = 0; j < repeat; j++) {
                    y += dy[i];
                    x += dx[i];
                    if (cnt == a) {
                        sY = y;
                        sX = x;
                    }
                    map[y][x] = cnt++;
                }
            }
            line++;
        }
    }

    private static class Honeycomb {
        int y, x, cnt;

        public Honeycomb(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
