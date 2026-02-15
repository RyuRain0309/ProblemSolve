package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q18291 {
    final static long DIV = (int) (1e9 + 7);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            if (N == 1) {
                sb.append(1).append("\n");
                continue;
            }
            long res = pow(N - 2);
            sb.append(res).append("\n");
        }
        System.out.print(sb);
    }

    static int pow(int n) {
        if (n == 0) {
            return 1;
        } else if (n % 2 == 0) {
            long res = pow(n / 2);
            return (int) (res * res % DIV);
        } else {
            long res = pow(n / 2);
            return (int) (2 * res * res % DIV);
        }
    }
}
