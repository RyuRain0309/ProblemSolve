package baekjoon;

import java.util.Scanner;

class Q2525 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int x = scanner.nextInt();

        int temp = n * 60 + m + x;
        m = temp % 60;
        temp /= 60;
        n = temp % 24;
        System.out.print(n + " " + m);
    }
}
