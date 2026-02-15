package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Q2667 {
    static int N;
    static boolean[][] isVisited;
    static int[] rangeX = {1, 0, -1, 0};
    static int[] rangeY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isVisited = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            for (int j = 1; j <= N; j++) {
                if (s.charAt(j - 1) == '1') {
                    isVisited[i][j] = true;
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (isVisited[i][j]) {
                    ans.add(dfs(i, j));
                }
            }
        }
        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for (Integer i : ans) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
    }

    private static int dfs(int y, int x) {
        isVisited[y][x] = false;
        int sum = 1;
        for (int i = 0; i < 4; i++) {
            int changeY = y + rangeY[i];
            int changeX = x + rangeX[i];
            if (changeX < 1 || changeY < 1 || changeY > N || changeX > N) {
                continue;
            }
            if (isVisited[y + rangeY[i]][x + rangeX[i]]) {
                sum += dfs(y + rangeY[i], x + rangeX[i]);
            }
        }
        return sum;
    }
}
