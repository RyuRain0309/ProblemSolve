package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Q1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] weight = new Integer[26];
        for (int i = 0; i < 26; i++) {
            weight[i] = 0;
        }
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int power = 1;
            for (int j = s.length() - 1; j >= 0; j--) {
                int index = s.charAt(j) - 'A';
                weight[index] += power;
                power *= 10;
            }
        }

        int res = 0;
        Arrays.sort(weight, Collections.reverseOrder());
        for (int i = 0; i < 10; i++) {
            res += weight[i] * (9 - i);
        }
        System.out.println(res);
    }
}
