package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            arr[i] = Math.max(arr[i], arr[i - 1]);
            st = new StringTokenizer(br.readLine(), " ");
            int T = Integer.parseInt(st.nextToken()) - 1;
            int P = Integer.parseInt(st.nextToken());
            if (i + T <= N) {
                arr[i + T] = Math.max(arr[i + T], arr[i - 1] + P);
            }
        }
        System.out.println(arr[N]);
    }
}
