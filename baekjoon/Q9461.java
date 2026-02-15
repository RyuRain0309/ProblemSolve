package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9461 {
    static long[] P = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        setP();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(P[N]).append("\n");
        }
        System.out.print(sb);
    }

    private static void setP() {
        P[1] = 1L;
        P[2] = 1L;
        P[3] = 1L;
        P[4] = 2L;
        P[5] = 2L;
        P[6] = 3L;
        P[7] = 4L;
        P[8] = 5L;
        P[9] = 7L;
        P[10] = 10L;
        for (int i = 7; i < 101; i++) {
            P[i] = P[i - 1] + P[i - 5];
        }
    }
}
