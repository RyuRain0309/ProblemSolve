package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        while (N-- > 0) {
            arr[N] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;
        for (int v : arr) {
            while (v <= K) {
                K -= v;
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}
