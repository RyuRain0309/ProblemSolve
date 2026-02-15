package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q15652 {

    static Stack<Integer> stack = new Stack<>();
    static int N, M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        backTracking(M);
        System.out.print(sb);
    }

    public static void backTracking(int cnt) {
        if (cnt == 0) {
            for (Integer i : stack) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!stack.empty()) {
                if (stack.peek() > i) {
                    continue;
                }
            }
            stack.push(i);
            backTracking(cnt - 1);
            stack.pop();
        }
    }
}
