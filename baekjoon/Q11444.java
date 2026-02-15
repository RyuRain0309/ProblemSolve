package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11444 {
    static long[][] A = {{1, 1}, {1, 0}};
    static long[][] u0 = {{1}, {0}};
    static long mod = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        if (N == 0) {
            System.out.print(0);
        } else if (N == 1) {
            System.out.print(1);
        } else {
            long[][] res = pow(N - 1);
            res = matrixMulti(res, u0);
            System.out.print(res[0][0]);
        }
    }

    private static long[][] pow(long i) {
        if (i == 1) {
            return A;
        }
        long[][] res = pow(i / 2);
        res = matrixMulti(res, res);
        if (i % 2 == 1) {
            res = matrixMulti(res, A);
        }
        return res;
    }

    private static long[][] matrixMulti(long[][] A, long[][] B) {
        long[][] res = new long[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[i].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                    res[i][j] %= mod;
                }
            }
        }
        return res;
    }
}
