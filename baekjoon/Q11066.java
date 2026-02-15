package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11066 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] arr = new int[K + 1];
            int[] sum = new int[K + 1];
            int[][] dp = new int[K + 1][K + 1];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + arr[i];
            }

            for (int n = 1; n <= K; n++) {
                for (int from = 1; from + n <= K; from++) {
                    int to = from + n;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int div = from; div < to; div++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][div] + dp[div + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }
            sb.append(dp[1][K]).append("\n");
        }
        System.out.print(sb);
    }
}
