package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11401 {
    final static long div = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long numer = factorial(N);
        long deno = pow(factorial(K) * factorial(N - K) % div);
        System.out.print((numer * deno) % div);
    }

    private static long pow(long n) {
        long expo = div - 2;
        long res = 1L;

        while (expo > 0) {
            if (expo % 2 == 1) {
                res *= n;
                res %= div;
            }
            n = n * n % div;
            expo /= 2;
        }
        return res;
    }

    private static long factorial(int n) {
        long res = 1L;
        while (n > 0) {
            res *= n;
            res %= div;
            n--;
        }
        return res;
    }
}
