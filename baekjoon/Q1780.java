package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1780 {
    static int[][] map;
    static int[] res = new int[]{0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divide(0, 0, N);
        System.out.print(res[0] + "\n" + res[1] + "\n" + res[2]);
    }

    private static void divide(int y, int x, int size) {
        int std = map[y][x];
        boolean pass = true;
        a:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (std != map[y + i][x + j]) {
                    pass = false;
                    break a;
                }
            }
        }
        if (pass) {
            res[std + 1]++;
        } else {
            size /= 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    divide(y + i * size, x + j * size, size);
                }
            }
        }
    }
}
