package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] R = new int[N];
        int[] G = new int[N];
        int[] B = new int[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            R[i] = Integer.parseInt(st.nextToken());
            G[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N; i++) {
            R[i] = R[i] + Math.min(B[i - 1], G[i - 1]);
            G[i] = G[i] + Math.min(R[i - 1], B[i - 1]);
            B[i] = B[i] + Math.min(R[i - 1], G[i - 1]);
        }
        System.out.print(Math.min(Math.min(R[N - 1], G[N - 1]), B[N - 1]));
    }
}