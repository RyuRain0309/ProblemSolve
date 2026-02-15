package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11729 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        sb.append((int) Math.pow(2, N) - 1).append("\n");
        recur(N, 1, 2, 3);
        System.out.println(sb);
    }

    private static void recur(int N, int start, int mid, int dest) {
        if (N == 1) {
            sb.append(start).append(" ").append(dest).append("\n");
            return;
        }
        recur(N - 1, start, dest, mid);

        sb.append(start).append(" ").append(dest).append("\n");

        recur(N - 1, mid, start, dest);
    }
}
