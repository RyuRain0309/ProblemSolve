package baekjoon;

import java.util.Scanner;
import java.util.Arrays;

public class Q1157 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int[] cnt = new int[27];
        Arrays.fill(cnt, 0);
        for (int i = 0; i < s.length(); i++) {
            int temp;
            if (s.charAt(i) < 'a') {
                temp = s.charAt(i) - 64;
            } else {
                temp = s.charAt(i) - 96;
            }
            cnt[temp]++;
        }
        int most_alp = 0, num = 0;
        for (int i = 1; i < 27; i++) {
            if (cnt[i] > num) {
                most_alp = i;
                num = cnt[i];
            } else if (cnt[i] == num) {
                most_alp = 0;
            }
        }
        if (most_alp == 0) {
            System.out.println('?');
        } else {
            System.out.printf("%c", most_alp + 64);
        }
        scanner.close();
    }
}