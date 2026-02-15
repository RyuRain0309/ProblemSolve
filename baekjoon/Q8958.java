package baekjoon;

import java.util.Scanner;
import java.lang.String;

public class Q8958 {
    public static void main(String[] args) {
        int n, pre, res;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            pre = 1;
            res = 0;
            String s = scanner.nextLine();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'O') {
                    res += pre++;
                } else {
                    pre = 1;
                }
            }
            System.out.println(res);
        }
    }
}
