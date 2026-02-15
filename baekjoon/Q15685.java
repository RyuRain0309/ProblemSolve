package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15685 {

    static int y, x;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] track = new boolean[101][101];
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            track[y][x] = true;
            arr.clear();
            dragonCurve(d, g);
        }
        int res = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (track[i][j] && track[i + 1][j] && track[i][j + 1] && track[i + 1][j + 1]) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }

    private static void dragonCurve(int d, int g) {
        if (g == 0) {
            trace(d);
            return;
        }
        dragonCurve(d, g - 1);
        for (int i = arr.size() - 1; i >= 0; i--) {
            int t = (arr.get(i) + 1) % 4;
            trace(t);
        }
    }

    private static void trace(int d) {
        y += dy[d];
        x += dx[d];
        arr.add(d);
        track[y][x] = true;
    }
}
