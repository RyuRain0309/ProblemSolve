package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1107 {

    static int channel = 100;
    static int res;
    public static int dest;
    static ArrayList<Integer> broken = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dest = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        if (n != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                broken.add(Integer.parseInt(st.nextToken()));
            }
        }

        res = Math.abs(dest - channel);
        for (int i = 0; i <= 1_000_000; i++) {
            if (isPossible(String.valueOf(i))) {
                getLeast(i);
            }
        }
        System.out.print(res);
    }

    private static boolean isPossible(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (broken.contains(Character.getNumericValue(s.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    private static void getLeast(int num) {
        int len = String.valueOf(num).length();
        res = Math.min(res, len + Math.abs(dest - num));
    }
}
