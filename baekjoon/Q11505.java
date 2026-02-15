package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11505 {

    static final int MOD = 1_000_000_007;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[] tree;
    static int[] arr;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
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
                int c = Integer.parseInt(st.nextToken());
                arr[b] = c;
                update(1, N, 1, b, c);
            }
            if (a == 2) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                sb.append(getMulti(1, N, 1, b, c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static long getMulti(int start, int dest, int pos, int b, int c) {
        if (b < start && dest < c) {
            return tree[pos];
        }
        if (start == dest) {
            return tree[pos];
        }
        int mid = (start + dest) / 2;
        long left = 1;
        long right = 1;
        if (mid >= b) {
            left = getMulti(start, mid, pos * 2, b, c);
        }
        if (mid + 1 <= c) {
            right = getMulti(mid + 1, dest, pos * 2 + 1, b, c);
        }
        return (left % MOD) * (right % MOD) % MOD;
    }

    private static long update(int start, int dest, int pos, int b, int c) {
        if (start == dest) {
            return tree[pos] = c;
        }
        int mid = (start + dest) / 2;
        long left = 0, right = 0;
        if (b <= mid) {
            left = update(start, mid, pos * 2, b, c);
            right = tree[pos * 2 + 1];
        }
        if (mid + 1 <= b) {
            left = tree[pos * 2];
            right = update(mid + 1, dest, pos * 2 + 1, b, c);
        }
        return tree[pos] = (left % MOD) * (right % MOD) % MOD;
    }

    private static long dfs(int start, int dest, int pos) {
        if (start == dest) {
            tree[pos] = arr[start];
            return tree[pos];
        }
        int mid = (start + dest) / 2;
        long left = dfs(start, mid, pos * 2);
        long right = dfs(mid + 1, dest, pos * 2 + 1);
        tree[pos] = (left % MOD) * (right % MOD) % MOD;
        return tree[pos];
    }
}
