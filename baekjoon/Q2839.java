package baekjoon;

import java.io.*;

public class Q2839 {

    static int N, cnt, temp = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int[] dp = {0, 0};
        while (N > temp) {
            temp += 5;
            dp[1]++;
        }
        if (temp == N) {
            System.out.print(dp[0] + dp[1]);
            return;
        }
        while (temp != 0) {
            temp -= 5;
            dp[1]--;
            dp[0] = 0;
            int temp2 = temp;
            while (N > temp2) {
                temp2 += 3;
                dp[0]++;
            }
            if (temp2 == N) {
                System.out.print(dp[0] + dp[1]);
                return;
            }
        }
        System.out.print(-1);
    }
}
