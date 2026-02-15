package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String[] s = new String[N];
        while (N-- > 0) {
            s[N] = br.readLine();
        }
        Arrays.sort(s, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            } else {
                return o1.length() - o2.length();
            }
        });
        sb.append(s[0]).append("\n");
        for (int i = 1; i < s.length; i++) {
            if (s[i - 1].compareTo(s[i]) == 0) {
                continue;
            }
            sb.append(s[i]).append("\n");
        }
        System.out.print(sb);
    }
}
