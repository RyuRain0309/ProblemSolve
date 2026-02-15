package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1917 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 3; i++) {
            solve();
        }
        System.out.print(sb);
    }

    private static void solve() throws IOException {
        map = new boolean[6][6];
        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 6; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    map[i][j] = true;
                }
            }
        }
        int[] pos = checkRow();
        int Ucnt = 0;
        int Dcnt = 0;
        if (pos[0] != 0 && pos[0] != 5)
            for (int i = pos[1] - 3; i <= pos[1]; i++) {
                if (map[pos[0] - 1][i]) {
                    Ucnt++;
                }
                if (map[pos[0] + 1][i]) {
                    Dcnt++;
                }
            }
        if (Ucnt == 1 && Dcnt == 1) {
            sb.append("yes").append("\n");
            return;
        }

        pos = checkColumn();
        Ucnt = 0;
        Dcnt = 0;
        if (pos[0] != 0 && pos[0] != 5)
            for (int i = pos[1] - 3; i <= pos[1]; i++) {
                if (map[i][pos[0] - 1]) {
                    Ucnt++;
                }
                if (map[i][pos[0] + 1]) {
                    Dcnt++;
                }
            }
        if (Ucnt == 1 && Dcnt == 1) {
            sb.append("yes").append("\n");
            return;
        }
        sb.append("no").append("\n");
    }

    private static int[] checkColumn() {
        int len = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[j][i]) {
                    len++;
                } else {
                    len = 0;
                }
                if (len == 4) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    private static int[] checkRow() {
        int len = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j]) {
                    len++;
                } else {
                    len = 0;
                }
                if (len == 4) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
}
