package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2042 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[] tree;
    static long[] arr;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        int size = 1;
        int cnt = 1;
        while (N > size) {
            size *= 2;
            cnt++;
        }
        tree = new long[(int) Math.pow(2, cnt) + 1];
        tree[1] = dfs(1, N, 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            if (a == 1) {
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                long diff = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, diff);
            }
            if (a == 2) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                sb.append(getSum(1, N, 1, b, c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static long getSum(int start, int dest, int pos, int b, int c) {
        if (b < start && dest < c) {
            return tree[pos];
        }
        if (start == dest) {
            return tree[pos];
        }
        int mid = (start + dest) / 2;
        long left = 0;
        long right = 0;
        if (mid >= b) {
            left = getSum(start, mid, pos * 2, b, c);
        }
        if (mid + 1 <= c) {
            right = getSum(mid + 1, dest, pos * 2 + 1, b, c);
        }
        return left + right;
    }

    private static void update(int start, int dest, int pos, int b, long diff) {
        tree[pos] += diff;
        if (start == dest) {
            return;
        }
        int mid = (start + dest) / 2;
        if (b <= mid) {
            update(start, mid, pos * 2, b, diff);
        }
        if (mid + 1 <= b) {
            update(mid + 1, dest, pos * 2 + 1, b, diff);
        }
    }

    private static long dfs(int start, int dest, int pos) {
        if (start == dest) {
            tree[pos] = arr[start];
            return tree[pos];
        }
        int mid = (start + dest) / 2;
        long left = dfs(start, mid, pos * 2);
        long right = dfs(mid + 1, dest, pos * 2 + 1);
        tree[pos] = left + right;
        return tree[pos];
    }
}
