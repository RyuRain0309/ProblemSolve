package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dpMax = new int[N][3];
        int[][] dpMin = new int[N][3];
        String[] s = br.readLine().split(" ");
        dpMax[0][0] = Integer.parseInt(s[0]);
        dpMax[0][1] = Integer.parseInt(s[1]);
        dpMax[0][2] = Integer.parseInt(s[2]);

        dpMin[0][0] = Integer.parseInt(s[0]);
        dpMin[0][1] = Integer.parseInt(s[1]);
        dpMin[0][2] = Integer.parseInt(s[2]);
        for (int i = 1; i < N; i++) {
            s = br.readLine().split(" ");
            dpMax[i][0] = Math.max(dpMax[i - 1][0], dpMax[i - 1][1]) + Integer.parseInt(s[0]);
            dpMax[i][1] = Math.max(dpMax[i - 1][0], Math.max(dpMax[i - 1][1], dpMax[i - 1][2])) + Integer.parseInt(s[1]);
            dpMax[i][2] = Math.max(dpMax[i - 1][1], dpMax[i - 1][2]) + Integer.parseInt(s[2]);

            dpMin[i][0] = Math.min(dpMin[i - 1][0], dpMin[i - 1][1]) + Integer.parseInt(s[0]);
            dpMin[i][1] = Math.min(dpMin[i - 1][0], Math.min(dpMin[i - 1][1], dpMin[i - 1][2])) + Integer.parseInt(s[1]);
            dpMin[i][2] = Math.min(dpMin[i - 1][1], dpMin[i - 1][2]) + Integer.parseInt(s[2]);
        }
        Arrays.sort(dpMax[N - 1]);
        Arrays.sort(dpMin[N - 1]);
        System.out.println(dpMax[N - 1][2] + " " + dpMin[N - 1][0]);
    }
}
