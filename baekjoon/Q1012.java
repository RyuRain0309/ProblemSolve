package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1012 {
    static int row;
    static int col;
    static boolean[][] map;
    static int[] rangeX = {1, 0, -1, 0};
    static int[] rangeY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int repeat = Integer.parseInt(br.readLine());
        while (repeat-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            map = new boolean[row][col];
            while (N-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
            }
            int cnt = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (map[i][j]) {
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int movedY = y + rangeY[i];
            int movedX = x + rangeX[i];
            if (movedY < 0 || movedX < 0 || movedY >= row || movedX >= col) {
                continue;
            }
            if (map[movedY][movedX]) {
                map[movedY][movedX] = false;
                dfs(movedY, movedX);
            }
        }
    }
}
