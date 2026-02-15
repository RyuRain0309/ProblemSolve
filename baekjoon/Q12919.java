package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Q12919 {

    static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        String T = br.readLine();
        dfs(T);
        System.out.println(0);
    }

    private static void dfs(String t) {
        if (S.length() > t.length()) {
            return;
        }
        if (S.length() == t.length()) {
            if (S.equals(t)) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }
        if (t.charAt(t.length() - 1) == 'A') {
            dfs(t.substring(0, t.length() - 1));
        }
        if (t.charAt(0) == 'B') {
            StringBuilder reverse = new StringBuilder();
            for (int i = t.length() - 1; i > 0; i--) {
                reverse.append(t.charAt(i));
            }
            dfs(reverse.toString());
        }
    }
}
