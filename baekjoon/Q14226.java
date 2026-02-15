package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q14226 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());
        Queue<Emoticon> q = new LinkedList<>();
        q.add(new Emoticon(1, 0, 0));
        boolean[][] dp = new boolean[1011][1011];

        while (!q.isEmpty()) {
            Emoticon e = q.poll();
            if (e.cnt == S) {
                System.out.println(e.time);
                break;
            }
            if (!dp[e.cnt][e.cnt]) {
                q.add(new Emoticon(e.cnt, e.cnt, e.time + 1));
                dp[e.cnt][e.cnt] = true;
            }
            if (e.clipboard != 0 & e.cnt + e.clipboard <= 1010 && !dp[e.cnt + e.clipboard][e.clipboard]) {
                q.add(new Emoticon(e.cnt + e.clipboard, e.clipboard, e.time + 1));
                dp[e.cnt + e.clipboard][e.clipboard] = true;
            }
            if (e.cnt - 1 > 0 && !dp[e.cnt - 1][e.clipboard]) {
                q.add(new Emoticon(e.cnt - 1, e.clipboard, e.time + 1));
                dp[e.cnt - 1][e.clipboard] = true;
            }
        }
    }

    static class Emoticon {
        int cnt, clipboard, time;

        public Emoticon(int cnt, int clipboard, int time) {
            this.cnt = cnt;
            this.clipboard = clipboard;
            this.time = time;
        }
    }
}



