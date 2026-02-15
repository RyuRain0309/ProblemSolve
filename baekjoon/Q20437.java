package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            ArrayList<Integer>[] arr = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                arr[i] = new ArrayList<>();
            }
            char[] s = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());

            for (int i = 0; i < s.length; i++) {
                int n = s[i] - 'a';
                arr[n].add(i);
            }

            int shortLen = Integer.MAX_VALUE;
            int longLen = Integer.MIN_VALUE;

            for (ArrayList<Integer> alphabet : arr) {
                if (alphabet.size() < K) {
                    continue;
                }
                for (int j = 0; j + K - 1 < alphabet.size(); j++) {
                    int len = alphabet.get(j + K - 1) - alphabet.get(j) + 1;
                    shortLen = Math.min(shortLen, len);
                    longLen = Math.max(longLen, len);
                }
            }
            if (shortLen == Integer.MAX_VALUE || longLen == 0) {
                sb.append("-1\n");
                continue;
            }
            sb.append(shortLen).append(" ").append(longLen).append("\n");
        }
        System.out.print(sb);
    }
}
