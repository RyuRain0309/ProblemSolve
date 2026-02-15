package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2239 {

    static int[][] sudoku;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = s.charAt(j) - '0';
            }
        }
        dfs(0);
    }

    private static void dfs(int depth) {
        if (depth >= 81) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(sudoku[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }
        int y = depth / 9;
        int x = depth % 9;
        if (sudoku[y][x] != 0) {
            dfs(depth + 1);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (checkEq(y, x, i)) {
                continue;
            }
            sudoku[y][x] = i;
            dfs(depth + 1);
            sudoku[y][x] = 0;
        }
    }

    private static boolean checkEq(int y, int x, int n) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[y][i] == n || sudoku[i][x] == n) {
                return true;
            }
        }
        int ty = (y / 3) * 3;
        int tx = (x / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku[ty + i][tx + j] == n) {
                    return true;
                }
            }
        }
        return false;
    }
}
