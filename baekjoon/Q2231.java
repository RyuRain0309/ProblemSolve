package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = N;
        int res = 0;
        while (M-- > 0) {
            int temp = getDiv(M);
            if (temp == N) {
                res = M;
            }
        }
        System.out.print(res);
        br.close();
    }

    public static int getDiv(int x) {
        int res = x;
        char[] c = Integer.toString(x).toCharArray();
        for (char value : c) {
            res += Character.getNumericValue(value);
        }
        return res;
    }
}
