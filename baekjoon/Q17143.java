package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17143 {
    static final int[] dy = {0, -1, 1, 0, 0};
    static final int[] dx = {0, 0, 0, 1, -1};
    static int R, C, M;
    static Shark[][][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Shark[R + 1][C + 1][C + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            map[r][c][1] = new Shark(s, d, z);
        }

        int res = 0;
        for (int i = 1; i <= C; i++) {
            for (int j = 1; j <= R; j++) {
                if (map[j][i][i] == null) {
                    continue;
                }
                res += map[j][i][i].z;
                map[j][i][i] = null;
                break;
            }
            if (i == C) {
                continue;
            }
            moveShark(i);
        }
        System.out.println(res);
    }

    private static void moveShark(int now) {
        int next = now + 1;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j][now] == null) {
                    continue;
                }
                Shark sh = map[i][j][now];
                int r = i + (dy[sh.d] * sh.s);
                int c = j + (dx[sh.d] * sh.s);
                int s = sh.s;
                int d = sh.d;
                int z = sh.z;
                while (r <= 0 || r > R) {
                    if (r <= 0) {
                        r = Math.abs(r) + 2;
                        d = 2;
                    }
                    if (r > R) {
                        r = R - (r - R);
                        d = 1;
                    }
                }
                while (c <= 0 || c > C) {
                    if (c <= 0) {
                        c = Math.abs(c) + 2;
                        d = 3;
                    }
                    if (c > C) {
                        c = C - (c - C);
                        d = 4;
                    }
                }

                if (map[r][c][next] != null) {
                    if (map[r][c][next].z > sh.z) {
                        continue;
                    }
                }
                map[r][c][next] = new Shark(s, d, z);
            }
        }
    }

    static class Shark {
        int s, d, z;

        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
