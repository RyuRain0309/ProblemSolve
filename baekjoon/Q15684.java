package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15684 {
    static int N, M, H;
    static boolean[][] map;
    static int[] ladderCount;
    static int res = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new boolean[H + 1][N + 1];
        ladderCount = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = true;
            ladderCount[b]++;
        }
        dfs(1, 1, 0);
        System.out.println(res > 3 ? -1 : res);
    }

    private static void dfs(int depth, int height, int cnt) {
        if (cnt >= res) {
            return;
        }
        if (depth >= N) {
            if (height >= H) {
                if (check()) {
                    res = cnt;
                }
                return;
            }
            dfs(1, height + 1, cnt);
            return;
        }

        dfs(depth + 1, height, cnt);
        if (map[height][depth] || map[height][depth + 1] || map[height][depth - 1]) {
            return;
        }

        map[height][depth] = true;
        ladderCount[depth]++;
        dfs(depth + 1, height, cnt + 1);
        map[height][depth] = false;
        ladderCount[depth]--;
    }

    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int line = i;
            for (int j = 1; j <= H; j++) {
                if (map[j][line]) {
                    line++;
                } else if (map[j][line - 1]) {
                    line--;
                }
            }
            if (line != i) {
                return false;
            }
        }
        return true;
    }
}
