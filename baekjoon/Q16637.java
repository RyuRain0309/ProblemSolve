package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16637 {
    static int N;
    static int res = Integer.MIN_VALUE;

    static char[] operator;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = (Integer.parseInt(br.readLine()) + 1) / 2;
        char[] arr = br.readLine().toCharArray();
        operator = new char[N];
        nums = new int[N];
        nums[0] = arr[0] - '0';
        operator[0] = '+';
        int index = 1;
        int index2 = 1;
        for (int i = 1; i < arr.length; i += 2) {
            operator[index++] = arr[i];
            nums[index2++] = arr[i + 1] - '0';
        }
        dfs(0, 0);
        System.out.println(res);
    }

    private static void dfs(int sum, int depth) {
        if (depth >= N) {
            res = Math.max(res, sum);
            return;
        }
        if (depth + 1 < N) {
            dfs(calc(sum, calc(nums[depth], nums[depth + 1], operator[depth + 1]), operator[depth]), depth + 2);
        }
        dfs(calc(sum, nums[depth], operator[depth]), depth + 1);
    }

    private static int calc(int a, int b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        }
        return -1;
    }
}
