package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1759 {

    static int L, C;
    static char[] arr;
    static String vowel = "aeiou";
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        dfs(0, "");
        System.out.print(sb);
    }

    private static void dfs(int cnt, String res) {
        if (res.length() == L) {
            if (isPossible(res)) {
                sb.append(res).append("\n");
            }
            return;
        }

        if (cnt >= C) {
            return;
        }

        dfs(cnt + 1, res + arr[cnt]);
        dfs(cnt + 1, res);
    }

    private static boolean isPossible(String res) {
        int cnt = 0;
        for (int i = 0; i < res.length(); i++) {
            for (int j = 0; j < vowel.length(); j++) {
                if (res.charAt(i) == vowel.charAt(j)) {
                    cnt++;
                }
            }
        }
        return cnt >= 1 && cnt <= L - 2;
    }
}
