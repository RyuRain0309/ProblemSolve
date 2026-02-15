package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q8111 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            bfs(N);
        }
        System.out.println(sb);
    }

    private static void bfs(int n) {
        Queue<StrAndMod> q = new LinkedList<>();
        boolean[] isVisited = new boolean[n];
        q.add(new StrAndMod("1", 1));
        while (!q.isEmpty()) {
            StrAndMod sam = q.poll();
            if (sam.s.length() > 100) {
                sb.append("BRAK").append("\n");
                return;
            }
            int mod = sam.mod * 10;
            if (mod % n == 0) {
                sb.append(sam.s).append("0").append("\n");
                return;
            }
            if ((mod + 1) % n == 0) {
                sb.append(sam.s).append("1").append("\n");
                return;
            }
            if (!isVisited[mod % n]) {
                q.add(new StrAndMod(sam.s + "0", mod % n));
                isVisited[mod % n] = true;
            }
            if (!isVisited[(mod + 1) % n]) {
                q.add(new StrAndMod(sam.s + "1", (mod + 1) % n));
                isVisited[(mod + 1) % n] = true;
            }
        }
    }

    static class StrAndMod {
        String s;
        int mod;

        public StrAndMod(String s, int mod) {
            this.s = s;
            this.mod = mod;
        }
    }
}
