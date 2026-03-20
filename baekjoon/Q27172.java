package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q27172 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] x = new int[N];
        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, x[i]);
        }

        boolean[] arr = new boolean[max + 1];
        int[] cnt = new int[max + 1];
        for (int i : x) {
            arr[i] = true;
        }

        for (int i : x) {
            for (int j = i; j <= max; j += i) {
                if (arr[j]) {
                    cnt[i]++;
                    cnt[j]--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : x) {
            sb.append(cnt[i]).append(" ");
        }
        System.out.println(sb);
    }
}
