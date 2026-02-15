package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q9935 {
    static String str;
    static String boom;
    static Stack<Character> s = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        boom = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            s.push(str.charAt(i));
            if (s.size() >= boom.length()) {
                boolean isBoom = checkBoom();
                if (isBoom) {
                    for (int j = 0; j < boom.length(); j++) {
                        s.pop();
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (s.empty()) {
            System.out.print("FRULA");
            System.exit(0);
        }
        for (Character character : s) {
            sb.append(character);
        }
        System.out.println(sb);
    }

    private static boolean checkBoom() {
        for (int i = 0; i < boom.length(); i++) {
            if (boom.charAt(i) != s.get(s.size() - boom.length() + i)) {
                return false;
            }
        }
        return true;
    }
}
