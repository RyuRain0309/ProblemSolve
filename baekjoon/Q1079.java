package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1079 {
    static int N, day = 0, mafia;
    static int[] guilty;
    static int[][] R;
    static boolean[] isDead;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        guilty = new int[N];
        isDead = new boolean[N];
        R = new int[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            guilty[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        mafia = Integer.parseInt(br.readLine());
        dfs(0, N);
        System.out.println(day);
    }

    private static void dfs(int depth, int remain) {
        if (remain == 1) {
            day = Math.max(day, depth);
            return;
        }
        if (remain % 2 == 0) {
            for (int i = 0; i < N; i++) {
                if (i == mafia || isDead[i]) {
                    continue;
                }
                isDead[i] = true;
                for (int j = 0; j < N; j++) {
                    guilty[j] += R[i][j];
                }
                dfs(depth + 1, remain - 1);
                for (int j = 0; j < N; j++) {
                    guilty[j] -= R[i][j];
                }
                isDead[i] = false;
            }
        } else {
            int suspect = -1;
            int index = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                if (isDead[i]) {
                    continue;
                }
                if (guilty[i] > index) {
                    suspect = i;
                    index = guilty[i];
                }
            }
            if (suspect == mafia) {
                day = Math.max(day, depth);
                return;
            }
            isDead[suspect] = true;
            dfs(depth, remain - 1);
            isDead[suspect] = false;
        }
    }
}
