package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1182 {
    static int[] arr;
    static int N, S, res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);
        if (S == 0) {
            res--;
        }
        System.out.println(res);
    }

    private static void dfs(int index, int sum) {
        if (index == N) {
            if (sum == S) {
                res++;
            }
            return;
        }

        dfs(index + 1, sum);
        dfs(index + 1, sum + arr[index]);
    }
}
