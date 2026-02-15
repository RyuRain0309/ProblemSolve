package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1645 {
    static int K;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken());
        arr = new int[K];
        N = Integer.parseInt(st.nextToken());
        long res = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            res = Math.max(res, arr[i]);
        }
        res = binarySearch(res + 1);
        System.out.print(res);
    }

    private static long binarySearch(long max) {
        long min = 0;
        long mid;
        while (min < max) {
            long cnt = 0;
            mid = (min + max) / 2;
            for (int i = 0; i < K; i++) {
                cnt += (arr[i] / mid);
            }

            if (cnt >= N) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min - 1;
    }
}
