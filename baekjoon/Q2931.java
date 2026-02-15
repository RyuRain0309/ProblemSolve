package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Q2931 {
    // 0 UP 1 LEFT 2 DOWN 3 RIGHT
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, -1, 0, 1};
    static int[][] block = {{},
            {3, 2, -1, -1}, {-1, 0, 3, -1}, {-1, -1, 1, 0}, {1, -1, -1, 2},
            {0, -1, 2, -1}, {-1, 1, -1, 3}, {0, 1, 2, 3}};
    static Map<Character, Integer> pipe = new HashMap<>();
    static int[][] map;
    static boolean[][] isFlow;
    static int[] dest = new int[2];
    static int[] start = new int[2];
    static int[] res = new int[3];
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        setPipe();
        map = new int[R][C];
        isFlow = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (c[j] == 'M') {
                    start[0] = i;
                    start[1] = j;

                } else if (c[j] == 'Z') {
                    dest[0] = i;
                    dest[1] = j;
                } else map[i][j] = pipe.get(c[j]);
            }
        }
        for (int i = 0; i < 4; i++) {
            dfs(start[0], start[1], i, false);
        }
    }

    private static void setPipe() {
        pipe.put('.', 0);
        pipe.put('1', 1);
        pipe.put('2', 2);
        pipe.put('3', 3);
        pipe.put('4', 4);
        pipe.put('|', 5);
        pipe.put('-', 6);
        pipe.put('+', 7);
    }

    private static void dfs(int y, int x, int dir, boolean isMade) {
        int ty = y + dy[dir];
        int tx = x + dx[dir];
        if (ty < 0 || ty >= R || tx < 0 || tx >= C) {
            return;
        }
        if (ty == dest[0] && tx == dest[1]) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 0) {
                        continue;
                    }
                    if (isFlow[i][j]) {
                        continue;
                    }
                    return;
                }
            }
            res[0]++;
            res[1]++;
            char resPipe = 'x';
            for (Character c : pipe.keySet()) {
                if (pipe.get(c).equals(res[2])) {
                    resPipe = c;
                }
            }
            System.out.println(res[0] + " " + res[1] + " " + resPipe);
            System.exit(0);
        }
        if (map[ty][tx] == 0) {
            if (isMade) {
                return;
            }
            for (int i = 1; i <= 7; i++) {
                if (block[i][dir] != -1) {
                    res[0] = ty;
                    res[1] = tx;
                    res[2] = i;
                    map[ty][tx] = i;
                    isFlow[ty][tx] = true;
                    dfs(ty, tx, block[i][dir], true);
                    map[ty][tx] = 0;
                    isFlow[ty][tx] = true;
                }
            }
        } else if (block[map[ty][tx]][dir] != -1) {
            isFlow[ty][tx] = true;
            dfs(ty, tx, block[map[ty][tx]][dir], isMade);
            isFlow[ty][tx] = false;
        }

    }
}
