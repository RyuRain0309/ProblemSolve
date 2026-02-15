package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] sum = new int[N];
        int[] remain = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        sum[0] = Integer.parseInt(st.nextToken()) % M;
        remain[sum[0]]++;
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
            sum[i] %= M;
            remain[sum[i]]++;
        }
        long res = remain[0];
        for (int i = 0; i < M; i++) {
            res += (long) remain[i] * (remain[i] - 1) / 2;
        }
        System.out.print(res);
    }
}
