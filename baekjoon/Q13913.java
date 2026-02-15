package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q13913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[100_002];
        int[] before = new int[100_002];
        Arrays.fill(dp, -1);
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, N));
        dp[N] = 0;
        while (!q.isEmpty()) {
            Pair pair = q.poll();

            if (pair.pos * 2 <= K + 1 && dp[pair.pos * 2] == -1) {
                q.add(new Pair(pair.cnt + 1, pair.pos * 2));
                dp[pair.pos * 2] = pair.cnt + 1;
                before[pair.pos * 2] = pair.pos;
            }

            if (pair.pos + 1 <= K && dp[pair.pos + 1] == -1) {
                q.add(new Pair(pair.cnt + 1, pair.pos + 1));
                dp[pair.pos + 1] = pair.cnt + 1;
                before[pair.pos + 1] = pair.pos;
            }


            if (pair.pos - 1 >= 0 && dp[pair.pos - 1] == -1) {
                q.add(new Pair(pair.cnt + 1, pair.pos - 1));
                dp[pair.pos - 1] = pair.cnt + 1;
                before[pair.pos - 1] = pair.pos;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[K]).append("\n");
        Stack<Integer> s = new Stack<>();
        while (N != K) {
            s.push(K);
            K = before[K];
        }
        s.push(K);
        while (!s.empty()) {
            sb.append(s.pop()).append(" ");
        }
        System.out.print(sb);
    }

    private static class Pair {
        int cnt;
        int pos;

        Pair(int cnt, int pos) {
            this.cnt = cnt;
            this.pos = pos;
        }
    }
}
