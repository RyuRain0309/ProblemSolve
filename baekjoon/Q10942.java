package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][] dp = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
            if (arr[i] == arr[i - 1]) {
                dp[i - 1][i] = true;
            }
            for (int j = i - 1; j > 0; j--) {
                if (arr[i] == arr[j]) {
                    if (dp[j + 1][i - 1]) {
                        dp[j][i] = true;
                    }
                }
            }
        }
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            sb.append(dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }
}
