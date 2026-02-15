package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] res = new int[N + 1];
        Stack<int[]> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            int H = Integer.parseInt(st.nextToken());
            while (!stack.empty()) {
                if (stack.peek()[0] < H) {
                    stack.pop();
                } else {
                    res[i] = stack.peek()[1];
                    stack.push(new int[]{H, i});
                    break;
                }
            }
            if (stack.empty()) {
                res[i] = 0;
                stack.push(new int[]{H, i});
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
    }
}
