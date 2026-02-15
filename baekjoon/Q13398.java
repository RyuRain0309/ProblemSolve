package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13398 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];
        int[] dpReverse = new int[N + 1];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
            res = Math.max(res, dp[i]);
        }
        dpReverse[N] = arr[N];
        for (int i = N - 1; i > 0; i--) {
            dpReverse[i] = Math.max(arr[i], dpReverse[i + 1] + arr[i]);
        }
        for (int i = 2; i < N; i++) {
            res = Math.max(res, dp[i - 1] + dpReverse[i + 1]);
        }
        System.out.println(res);
    }
}
