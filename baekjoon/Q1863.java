package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] skyline = new int[1_000_002];
        int cur = 0;
        int h = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken());
            for (; cur < x; cur++) {
                skyline[cur] = h;
            }
            h = y;
        }
        skyline[cur] = h;

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        stack.push(0);
        for (int i = 0; i < cur + 2; i++) {
            while (!stack.isEmpty() && stack.peek() > skyline[i]) {
                stack.pop();
                res++;
            }
            if (!stack.isEmpty() && stack.peek() < skyline[i]) {
                stack.push(skyline[i]);
            }
        }

        System.out.println(res);
    }
}
