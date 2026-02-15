package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15661 {
    static int[][] map;
    static boolean[] v;
    static int n;
    static int answer = Integer.MAX_VALUE;
    static int t = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        v = new boolean[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (t = 1; t < n; t++) {
            backtracking(0, 0);
        }
        System.out.println(answer);
    }

    public static void backtracking(int depth, int start) {
        if (depth == t) {
            answer = Math.min(answer, diff());
            if (answer == 0) {
                System.out.println(answer);
                System.exit(0);
            }
            return;
        }
        for (int i = start; i < n; i++) {
            if (!v[i]) {
                v[i] = true;
                backtracking(depth + 1, i + 1);
                v[i] = false;
            }
        }
    }

    public static int diff() {
        int start = 0;
        int link = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (v[i] && v[j]) {
                    start += (map[i][j] + map[j][i]);
                } else if (!v[i] && !v[j]) {
                    link += (map[i][j] + map[j][i]);
                }
            }
        }
        return Math.abs(start - link);
    }
}