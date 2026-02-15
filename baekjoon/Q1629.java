package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1629 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        System.out.print(divide(A, B, C));
    }

    private static long divide(long a, long b, long c) {
        if (b == 1) {
            return a % c;
        }
        long temp = divide(a, b / 2, c);
        if (b % 2 == 1) {
            return (temp * temp % c) * a % c;
        }
        return temp * temp % c;
    }
}
