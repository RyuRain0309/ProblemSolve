package baekjoon;

import java.util.Scanner;

public class Q4344 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner((System.in));
        int C = scanner.nextInt();

        for (int i = 0; i < C; i++) {
            double mean;
            double cnt = 0, sum = 0;
            int N = scanner.nextInt();
            int[] a = new int[N];
            for (int j = 0; j < N; j++) {
                a[j] = scanner.nextInt();
                sum += a[j];
            }
            mean = sum / N;

            for (int j = 0; j < N; j++) {
                if (a[j] > mean) {
                    cnt++;
                }
            }
            System.out.printf("%.3f%%\n", (cnt / N) * 100);
        }
        scanner.close();
    }
}
