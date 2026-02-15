package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1062 {
    static int N, K, res = 0;
    static String[] strs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        strs = new String[N];
        for (int i = 0; i < N; i++) {
            strs[i] = br.readLine();
        }
        if (K < 5) {
            System.out.println(0);
            return;
        }
        int bit = 0;
        bit = bit | (1 << (0));
        bit = bit | (1 << ('c' - 'a'));
        bit = bit | (1 << ('i' - 'a'));
        bit = bit | (1 << ('n' - 'a'));
        bit = bit | (1 << ('t' - 'a'));
        dfs(0, 5, bit);
        System.out.println(res);
    }

    private static void dfs(int depth, int k, int bit) {
        if (k > K) {
            return;
        }
        if (depth == 26) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                boolean flag = true;
                for (int j = 0; j < strs[i].length(); j++) {
                    if ((bit & 1 << (strs[i].charAt(j) - 'a')) == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    cnt++;
                }
            }
            res = Math.max(res, cnt);
            return;
        }
        dfs(depth + 1, k, bit);
        if ((bit & 1 << depth) == 0) {
            dfs(depth + 1, k + 1, bit | 1 << depth);
        }
    }
}
