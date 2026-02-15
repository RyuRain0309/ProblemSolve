package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2473 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        long res = Long.MAX_VALUE;
        int resP1 = 0;
        int resP2 = N - 1;
        int resP3 = 0;
        for (int i = 0; i < N; i++) {
            int p1 = 0;
            int p2 = N - 1;
            while (p1 < p2) {
                if (p1 == i) {
                    p1++;
                    continue;
                }
                if (p2 == i) {
                    p2--;
                    continue;
                }
                long sum = (long) arr[p1] + arr[p2] + arr[i];
                if (sum == 0) {
                    print(i, p1, p2);
                    return;
                }
                if (res > Math.abs(sum)) {
                    res = Math.abs(sum);
                    resP1 = p1;
                    resP2 = p2;
                    resP3 = i;
                }
                if (sum > 0) {
                    p2--;
                }
                if (sum < 0) {
                    p1++;
                }
            }
        }
        print(resP3, resP1, resP2);

    }

    private static void print(int i, int p1, int p2) {
        if (i < p1) {
            System.out.println(arr[i] + " " + arr[p1] + " " + arr[p2]);
            return;
        }
        if (i > p2) {
            System.out.println(arr[p1] + " " + arr[p2] + " " + arr[i]);
            return;
        }
        System.out.println(arr[p1] + " " + arr[i] + " " + arr[p2]);
    }

}
