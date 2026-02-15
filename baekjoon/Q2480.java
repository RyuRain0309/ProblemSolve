package baekjoon;

import java.util.Scanner;

public class Q2480 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int res, a, b, c;
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        if (a == b && b == c) {
            res = 10000 + a * 1000;
        } else if (a == b || a == c) {
            res = 1000 + a * 100;
        } else if (b == c) {
            res = 1000 + b * 100;
        } else {
            if (a > b && a > c) {
                res = a * 100;
            } else if (b > c) {
                res = b * 100;
            } else {
                res = c * 100;
            }
        }
        System.out.print(res);
    }
}
