package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        String[] arr = new String[N];
        int M = Integer.parseInt(st.nextToken());
        while (N-- > 0) {
            arr[N] = br.readLine();
        }
        int cnt = 0;
        while (M-- > 0) {
            String temp = br.readLine();
            for (String s : arr) {
                if (temp.equals(s)) {
                    cnt++;
                }
            }
        }
        System.out.print(cnt);
        br.close();
    }
}
