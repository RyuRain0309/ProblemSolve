package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2630 {
    static int[][] map;
    static int[] color = {0, 0};

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
        System.out.print(color[0] + "\n" + color[1]);
    }

    private static void divide(int y, int x, int size) {
        int standard = map[y][x];
        boolean isEqual = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (standard != map[y + i][x + j]) {
                    isEqual = false;
                    break;
                }
            }
        }
        if (isEqual) {
            color[standard]++;
        } else {
            size = size / 2;
            divide(y, x, size);
            divide(y, x + size, size);
            divide(y + size, x, size);
            divide(y + size, x + size, size);
        }
    }
}
