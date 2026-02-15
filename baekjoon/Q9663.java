package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9663 {
    static int[] col;
    static int N;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        col = new int[N];
        N_Queens(0);
        System.out.println(cnt);
    }

    private static void N_Queens(int y) {
        if (y == N) {
            cnt++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (promising(y, i)) {
                col[y] = i;
                N_Queens(y + 1);
                col[y] = -1;
            }
        }
    }

    private static boolean promising(int y, int x) {
        for (int i = 0; i < y; i++) {
            if (col[i] == x || ((y - i) == Math.abs(col[i] - x))) {
                return false;
            }
        }
        return true;
    }
}
