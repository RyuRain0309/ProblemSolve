package baekjoon;

import java.util.Scanner;

public class Q1110 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N, V, cnt = 0;
        N = scanner.nextInt();
        V = N;
        while (true) {
            int temp = V / 10 + V % 10;
            V = V % 10 * 10 + temp % 10;
            cnt++;
            if (V == N) {
                break;
            }
            continue;
        }
        System.out.print(cnt);
    }
}
