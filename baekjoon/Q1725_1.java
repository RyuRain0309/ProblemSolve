package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q1725_1 {
    static long[] histogram;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        histogram = new long[N];
        Stack<Long> valueStack = new Stack<>();
        Stack<Integer> posStack = new Stack<>();
        long max = 0;
        for (int i = 0; i < N; i++) {
            Long temp = Long.parseLong(br.readLine());
            while (!valueStack.empty() && temp < valueStack.peek()) {
                long height = valueStack.pop();
                long weidth = i;
                posStack.pop();
                if (!valueStack.empty()) {
                    weidth -= posStack.peek() + 1;
                }

                max = Math.max(max, height * weidth);
            }
            posStack.push(i);
            valueStack.push(temp);
        }
        while (!valueStack.empty()) {
            long height = valueStack.pop();
            long weidth = N;
            posStack.pop();
            if (!valueStack.empty()) {
                weidth -= posStack.peek() + 1;
            }

            max = Math.max(max, height * weidth);
        }
        System.out.print(max);
    }
}
