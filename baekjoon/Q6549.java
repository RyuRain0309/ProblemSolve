package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Q6549 {
    static StringBuilder sb = new StringBuilder();
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean stop = true;
        while (stop) {
            stop = solve(br.readLine());
        }
        System.out.print(sb);
        br.close();
    }

    private static boolean solve(String readLine) {
        StringTokenizer st = new StringTokenizer(readLine, " ");
        int N = Integer.parseInt(st.nextToken());
        if (N == 0) {
            return false;
        }
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        sb.append(divide(0, N - 1)).append("\n");
        arr = null;
        return true;
    }

    private static long divide(int start, int end) {
        if (start == end) {
            return arr[start];
        }
        int mid = (start + end) / 2;
        long left = divide(start, mid);
        long right = divide(mid + 1, end);

        long max = Math.max(left, right);
        max = Math.max(max, mySearch(start, mid, end));
        return max;
    }

    private static long mySearch(int start, int mid, int end) {
        int leftSearch = mid;
        int rightSearch = mid;
        long max = arr[mid];
        long height = arr[mid];
        while (leftSearch > start && rightSearch < end) {
            if (arr[leftSearch - 1] > arr[rightSearch + 1]) {
                leftSearch--;
                height = Math.min(height, arr[leftSearch]);
            } else {
                rightSearch++;
                height = Math.min(height, arr[rightSearch]);
            }
            max = Math.max(max, height * (rightSearch - leftSearch + 1));
        }
        while (leftSearch > start) {
            leftSearch--;
            height = Math.min(height, arr[leftSearch]);
            max = Math.max(max, height * (rightSearch - leftSearch + 1));
        }
        while (rightSearch < end) {
            rightSearch++;
            height = Math.min(height, arr[rightSearch]);
            max = Math.max(max, height * (rightSearch - leftSearch + 1));
        }

        return max;
    }
}
