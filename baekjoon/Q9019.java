package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q9019 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            solution(start, dest);
        }
        System.out.print(sb);
    }

    private static void solution(int start, int dest) {
        int[] dp = new int[10_000];
        Arrays.fill(dp, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dp[start] = 0;
        while (!q.isEmpty()) {
            int pos = q.poll();
            if (pos == dest) {
                break;
            }
            int DPos = D(pos);
            if (dp[DPos] == -1) {
                dp[DPos] = pos;
                q.add(DPos);
            }
            int SPos = S(pos);
            if (dp[SPos] == -1) {
                dp[SPos] = pos;
                q.add(SPos);
            }
            int LPos = L(pos);
            if (dp[LPos] == -1) {
                dp[LPos] = pos;
                q.add(LPos);
            }
            int RPos = R(pos);
            if (dp[RPos] == -1) {
                dp[RPos] = pos;
                q.add(RPos);
            }
        }
        Stack<Character> s = new Stack<>();
        while (dest != start) {
            if (dest == D(dp[dest])) {
                s.push('D');
            } else if (dest == S(dp[dest])) {
                s.push('S');
            } else if (dest == L(dp[dest])) {
                s.push('L');
            } else if (dest == R(dp[dest])) {
                s.push('R');
            }
            dest = dp[dest];
        }
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        sb.append("\n");
    }

    private static int D(int pos) {
        return (pos * 2) % 10_000;
    }

    private static int S(int pos) {
        if (pos == 0) {
            return 9_999;
        }
        return pos - 1;
    }

    private static int L(int pos) {
        return (pos % 1000 * 10) + (pos / 1000);
    }

    private static int R(int pos) {
        return (pos % 10 * 1000) + (pos / 10);
    }
}
