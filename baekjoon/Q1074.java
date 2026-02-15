package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1074 {
    static int r, c, res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, N);
//        System.out.println("N = " + N);
        recur(N / 2);
        System.out.println(res);
    }

    private static void recur(int n) {
//        System.out.println(n + " " + res + " " + r + " " + c);
        if (n == 1) {
            res += 2 * r + c;
            return;
        }

        if (r < n && c < n) {  //1사분면
            recur(n / 2);
        } else if (r < n) {  //3사분면
            res += (n * n);
            c -= n;
            recur(n / 2);
        } else if (c < n) {  //2사분면
            res += 2 * (n * n);
            r -= n;
            recur(n / 2);
        } else {
            res += 3 * (n * n);
            r -= n;
            c -= n;
            recur(n / 2);
        }
    }
}
