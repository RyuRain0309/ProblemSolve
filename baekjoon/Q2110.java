package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2110 {
    static int N;
    static int C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int lo = 1;
        int hi = arr[N - 1] - arr[0] + 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (isPass(mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        System.out.print(lo - 1);

    }

    private static boolean isPass(int num) {
        int cnt = 1;
        int lastloc = arr[0];
        for (int i = 1; i < N; i++) {
            if (arr[i] - lastloc >= num) {
                cnt++;
                lastloc = arr[i];
            }
        }
        return cnt >= C;
    }
}
