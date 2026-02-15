package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int p1 = 0;
        int p2 = N - 1;
        Arrays.sort(arr);
        int res = Integer.MAX_VALUE;
        int resP1 = 0;
        int resP2 = N - 1;
        while (p1 < p2) {
            int sum = arr[p1] + arr[p2];
            if (sum == 0) {
                System.out.println(arr[p1] + " " + arr[p2]);
                return;
            }
            if (res > Math.abs(sum)) {
                res = Math.abs(sum);
                resP1 = p1;
                resP2 = p2;
            }
            if (sum > 0) {
                p2--;
            }
            if (sum < 0) {
                p1++;
            }
        }
        System.out.println(arr[resP1] + " " + arr[resP2]);
    }
}
