package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] res = new int[k + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        for (int i = 1; i <= k; i++) {
            for (int a : arr) {
                if (i >= a) {
                    if (res[i - a] != Integer.MAX_VALUE) {
                        res[i] = Math.min(res[i], res[i - a] + 1);
                    }
                }
            }
        }
        System.out.println(res[k] == Integer.MAX_VALUE ? -1 : res[k]);
    }
}
