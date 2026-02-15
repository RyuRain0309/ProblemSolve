package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17136 {
    static int res = Integer.MAX_VALUE;
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static boolean[][] map = new boolean[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        dfs(0, 0);
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static void dfs(int depth, int use) {
        if (use >= res) {
            return;
        }
        if (depth == 100) {
            res = use;
            return;
        }
        int y = depth / 10;
        int x = depth % 10;

        if (!map[y][x]) {
            dfs(depth + 1, use);
            return;
        }

        for (int i = 5; i >= 1; i--) {
            if (paper[i] == 0) {
                continue;
            }

            if ((y + i) > 10 || (x + i) > 10) {
                continue;
            }

            boolean flag = true;
            for (int j = 0; j < i; j++) {
                if (!flag) {
                    break;
                }
                for (int k = 0; k < i; k++) {
                    if (!map[y + j][x + k]) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                for (int j = 0; j < i; j++) {
                    for (int k = 0; k < i; k++) {
                        map[y + j][x + k] = false;
                    }
                }
                paper[i]--;
                dfs(depth + 1, use + 1);
                for (int j = 0; j < i; j++) {
                    for (int k = 0; k < i; k++) {
                        map[y + j][x + k] = true;
                    }
                }
                paper[i]++;
            }
        }
    }
}
