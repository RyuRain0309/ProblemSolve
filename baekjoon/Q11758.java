package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] x = new int[3];
        int[] y = new int[3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        int dx = x[1] - x[0];
        int dy = y[1] - y[0];

        int dx1 = x[2] - x[1];
        int dy1 = y[2] - y[1];

        int ans = dx * dy1 - dx1 * dy;

        if (ans > 0) System.out.print(1);
        else if (ans == 0) System.out.print(0);
        else System.out.print(-1);
    }
}
