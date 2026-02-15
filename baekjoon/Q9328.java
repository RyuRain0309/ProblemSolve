package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q9328 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            solve();
        }
        System.out.println(sb);
    }

    private static void solve() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        char[][] map = new char[H + 2][W + 2];
        boolean[][] isVisited = new boolean[H + 2][W + 2];
        boolean[] hasKey = new boolean[26];
        ArrayList<Pos>[] doors = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            doors[i] = new ArrayList<>();
        }
        for (int i = 1; i <= H; i++) {
            String s = br.readLine();
            for (int j = 1; j <= W; j++) {
                if ('A' <= s.charAt(j - 1) && s.charAt(j - 1) <= 'Z') {
                    doors[s.charAt(j - 1) - 'A'].add(new Pos(i, j));
                }
                map[i][j] = s.charAt(j - 1);
            }
        }
        String s = br.readLine();
        if (s.charAt(0) != '0') {
            for (int i = 0; i < s.length(); i++) {
                hasKey[s.charAt(i) - 'a'] = true;
            }
        }
        Queue<Pos> q = new LinkedList<>();
        Queue<Integer> findKeys = new LinkedList<>();
        q.offer(new Pos(0, 0));
        isVisited[0][0] = true;
        int res = 0;
        while (!q.isEmpty()) {
            while (!q.isEmpty()) {
                Pos pos = q.poll();
                for (int i = 0; i < 4; i++) {
                    int ty = dy[i] + pos.y;
                    int tx = dx[i] + pos.x;
                    if (ty < 0 || ty >= H + 2 || tx < 0 || tx >= W + 2) {
                        continue;
                    }
                    if (map[ty][tx] == '*' || isVisited[ty][tx]) {
                        continue;
                    }
                    if (map[ty][tx] == '$') {
                        res++;
                    }
                    if ('A' <= map[ty][tx] && map[ty][tx] <= 'Z') {
                        if (!hasKey[map[ty][tx] - 'A']) {
                            isVisited[ty][tx] = true;
                            continue;
                        }
                    }
                    if ('a' <= map[ty][tx] && map[ty][tx] <= 'z') {
                        if (!hasKey[map[ty][tx] - 'a']) {
                            findKeys.offer(map[ty][tx] - 'a');
                        }
                    }
                    isVisited[ty][tx] = true;
                    q.add(new Pos(ty, tx));

                }
            }
            while (!findKeys.isEmpty()) {
                int i = findKeys.poll();
                hasKey[i] = true;
                for (Pos door : doors[i]) {
                    if (isVisited[door.y][door.x]) {
                        q.offer(door);
                    }
                }
            }
        }
        sb.append(res).append("\n");
    }


    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
