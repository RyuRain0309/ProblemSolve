package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q14889 {
    static int[][] map;
    static int N;
    static int dif = Integer.MAX_VALUE;
    static Stack<Integer> team1 = new Stack<>();
    static Stack<Integer> team2 = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backTracking(0);
        System.out.println(dif);
    }

    private static void backTracking(int n) {
        if (n == N) {
            int team1Cnt = 0, team2Cnt = 0;
            for (Integer i : team1) {
                for (Integer j : team1) {
                    team1Cnt += map[i][j];
                }
            }
            for (Integer i : team2) {
                for (Integer j : team2) {
                    team2Cnt += map[i][j];
                }
            }
            if (Math.abs(team1Cnt - team2Cnt) < dif) {
                dif = Math.abs(team1Cnt - team2Cnt);
            }
        }
        if (team1.size() < N / 2) {
            team1.push(n);
            backTracking(n + 1);
            team1.pop();
        }
        if (team2.size() < N / 2) {
            team2.push(n);
            backTracking(n + 1);
            team2.pop();
        }
    }
}