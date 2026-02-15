package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q12933 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] cnt = new int[6];
        int res = 0;
        int maxRes = 0;
        cnt[0] = Integer.MAX_VALUE;
        for (char c : s.toCharArray()) {
            int index = 1;
            if (c == 'q') {
                res++;
                maxRes = Math.max(maxRes, res);
            }
            if (c == 'u') {
                index = 2;
            }
            if (c == 'a') {
                index = 3;
            }
            if (c == 'c') {
                index = 4;
            }
            if (c == 'k') {
                res--;
                index = 5;
            }
            cnt[index]++;
            if (cnt[index] > cnt[index - 1]) {
                System.out.println(-1);
                return;
            }
        }
        if (cnt[1] == cnt[2] && cnt[2] == cnt[3] && cnt[3] == cnt[4] && cnt[4] == cnt[5]) {
            System.out.println(maxRes);
        } else {
            System.out.println(-1);
        }
    }
}
