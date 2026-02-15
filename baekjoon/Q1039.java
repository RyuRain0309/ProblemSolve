package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1039 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String N = st.nextToken();
        int len = N.length();
        int K = Integer.parseInt(st.nextToken());
        boolean[][] isVisit = new boolean[1_000_001][11];
        Queue<EX> q = new LinkedList<>();
        q.add(new EX(N, 0));
        String ans = "-1";
        while (!q.isEmpty()) {
            final EX ex = q.poll();
            if (ex.k == K) {
                if (Integer.parseInt(ex.n) > Integer.parseInt(ans)) {
                    ans = ex.n;
                }
                continue;
            }
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (i == j) continue;
                    if ((ex.n.charAt(i) == '0' || ex.n.charAt(j) == '0') && (i == 0 || j == 0)) continue;
                    String temp = ex.n.substring(0, i) + ex.n.charAt(j) + ex.n.substring(i + 1, j) + ex.n.charAt(i) + ex.n.substring(j + 1, len);
                    if (isVisit[Integer.parseInt(temp)][ex.k]) continue;

                    q.add(new EX(temp, ex.k + 1));
                    isVisit[Integer.parseInt(temp)][ex.k] = true;
                }
            }
        }
        System.out.println(ans);
    }

    static class EX {
        String n;
        int k;

        public EX(String n, int k) {
            this.n = n;
            this.k = k;
        }
    }
}
