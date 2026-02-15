package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Q1063 {
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Loc[] king = new Loc[2];
        map.put('R', 0);
        map.put('L', 1);
        map.put('B', 2);
        map.put('T', 3);
        for (int i = 0; i < 2; i++) {
            String s = st.nextToken();
            int x = s.charAt(0) - 'A';
            int y = s.charAt(1) - '1';
            king[i] = new Loc(y, x);
        }
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            String s = br.readLine();
            int my = 0;
            int mx = 0;
            for (int j = 0; j < s.length(); j++) {
                my += dy[map.get(s.charAt(j))];
                mx += dx[map.get(s.charAt(j))];
            }
            int ty = king[0].y + my;
            int tx = king[0].x + mx;
            if (ty < 0 || ty >= 8 || tx < 0 || tx >= 8) {
                continue;
            }
            if (king[1].y == ty && king[1].x == tx) {
                int sty = king[1].y + my;
                int stx = king[1].x + mx;
                if (sty < 0 || sty >= 8 || stx < 0 || stx >= 8) {
                    continue;
                }
                king[1].y = sty;
                king[1].x = stx;
            }
            king[0].y = ty;
            king[0].x = tx;
        }

        StringBuilder sb = new StringBuilder();
        sb.append((char) (king[0].x + 'A'));
        sb.append(king[0].y + 1);
        sb.append("\n");
        sb.append((char) (king[1].x + 'A'));
        sb.append(king[1].y + 1);
        System.out.println(sb);
    }

    static class Loc {
        int y, x;

        public Loc(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
