package baekjoon;

import java.util.Scanner;

public class Q2884 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        if (m >= 45) {
            m -= 45;
        } else {
            if (n == 0) {
                n = 23;
            } else {
                n -= 1;
            }
            m += 15;
        }
        System.out.print(n + " " + m);
    }
}
