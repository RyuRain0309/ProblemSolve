package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Q11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> set = new HashSet<>();
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            final String[] s = br.readLine().split(" ");
            if (s[0].compareTo("add") == 0) {
                set.add(Integer.parseInt(s[1]));
            } else if (s[0].compareTo("check") == 0) {
                sb.append(set.contains(Integer.parseInt(s[1])) ? 1 : 0).append("\n");
            } else if (s[0].compareTo("remove") == 0) {
                set.remove(Integer.parseInt(s[1]));
            } else if (s[0].compareTo("toggle") == 0) {
                if (set.contains(Integer.parseInt(s[1]))) {
                    set.remove(Integer.parseInt(s[1]));
                } else {
                    set.add(Integer.parseInt(s[1]));
                }
            } else if (s[0].compareTo("all") == 0) {
                set.clear();
                for (int j = 1; j <= 20; j++) {
                    set.add(j);
                }
            } else if (s[0].compareTo("empty") == 0) {
                set.clear();
            }
        }
        System.out.println(sb);
    }
}
