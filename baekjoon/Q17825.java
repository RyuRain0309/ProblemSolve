package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q17825 {
    static int[] score = new int[33];
    static int[] map = new int[33];
    static int[] blue = {5, 10, 15};
    static int[] blueMove = {21, 24, 26};
    static int[] dice = new int[10];
    static int[][][][] isVisited = new int[33][33][33][33];
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        setScore();
        setMap();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        int[] pos = new int[4];
        dfs(0, pos, 0);
        System.out.println(res);
    }

    private static void dfs(int depth, int[] pos, int ans) {
        if (depth == 10) {
            res = Math.max(res, ans);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int[] temp = pos.clone();

            if (temp[i] == 32) continue;

            int moveCnt = dice[depth];
            for (int j = 0; j < 3; j++) {
                if (temp[i] == blue[j]) {
                    moveCnt--;
                    temp[i] = blueMove[j];
                    break;
                }
            }

            temp[i] = move(temp[i], moveCnt);
            if (isCanNotMove(i, temp)) continue;

            int s = ans + score[temp[i]];
            Arrays.sort(temp);
            if (isVisited[temp[0]][temp[1]][temp[2]][temp[3]] == s) {
                continue;
            }
            isVisited[temp[0]][temp[1]][temp[2]][temp[3]] = s;
            dfs(depth + 1, temp, s);
        }
    }

    private static boolean isCanNotMove(int i, int[] temp) {
        if (temp[i] == 32) return false;

        for (int j = 0; j < 4; j++) {
            if (i == j) continue;
            if (temp[j] == temp[i]) {
                return true;
            }
        }
        return false;
    }

    private static int move(int pos, int cnt) {
        for (int i = 0; i < cnt; i++) {
            pos = map[pos];
        }
        return pos;
    }

    private static void setMap() {
        for (int i = 0; i < 32; i++) {
            map[i] = i + 1;
        }
        map[20] = 32;
        map[23] = 29;
        map[25] = 29;
        map[31] = 20;
        map[32] = 32;
    }

    private static void setScore() {
        for (int i = 0; i < 21; i++) {
            score[i] = i * 2;
        }
        score[21] = 13;
        score[22] = 16;
        score[23] = 19;
        score[24] = 22;
        score[25] = 24;
        score[26] = 28;
        score[27] = 27;
        score[28] = 26;
        score[29] = 25;
        score[30] = 30;
        score[31] = 35;
        score[32] = 0;
    }
}
