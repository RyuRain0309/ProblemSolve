package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2805 {
    static int N;
    static long M;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        arr = new long[N];
        M = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        long max = Long.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        System.out.print(binarySearch(max));
    }

    private static Long binarySearch(long hi) {
        long lo = 0;
        long mid;
        while (lo < hi) {
            long cnt = 0;
            mid = (lo + hi) / 2;
            for (int i = 0; i < N; i++) {
                if (arr[i] > mid) {
                    cnt += arr[i] - mid;
                }
            }
            if (cnt >= M) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo - 1;
    }
}
