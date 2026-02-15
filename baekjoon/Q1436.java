package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num = 666;
        while (N > 0) {
            if (check(num)) {
                N--;
            }
            num++;
        }
        System.out.println(num - 1);
    }

    public static boolean check(int num) {
        char[] c = Integer.toString(num).toCharArray();
        int cnt = 0;
        for (char value : c) {
            if (value == '6') {
                cnt++;
            } else {
                cnt = 0;
            }
            if (cnt == 3) {
                return true;
            }
        }
        return false;
    }
}
