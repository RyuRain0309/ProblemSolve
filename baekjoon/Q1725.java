package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Q1725 {
    static long[] histogram;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        histogram = new long[N];
        Stack<ArrayList<Integer>> s = new Stack<>();
        for (int i = 0; i < N; i++) {
            histogram[i] = Long.parseLong(br.readLine());
        }
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(0);
        temp.add(N - 1);
        s.push(temp);
        long res = 0;
        while (!s.empty()) {
            int lo = s.peek().get(0);
            int hi = s.peek().get(1);
            s.pop();
            if (lo == hi) {
                res = Math.max(res, histogram[lo]);
                continue;
            }
            int mid = (lo + hi) / 2;
            ArrayList<Integer> left = new ArrayList<>();
            left.add(lo);
            left.add(mid);
            ArrayList<Integer> right = new ArrayList<>();
            right.add(mid + 1);
            right.add(hi);
            s.push(left);
            s.push(right);
            res = Math.max(res, mySearch(lo, mid, hi));
        }
        System.out.print(res);
    }

    private static long mySearch(int start, int mid, int end) {
        int leftSearch = mid;
        int rightSearch = mid;
        long max = histogram[mid];
        long height = histogram[mid];

        while (leftSearch > start && rightSearch < end) {
            if (histogram[leftSearch - 1] > histogram[rightSearch + 1]) {
                leftSearch--;
                height = Math.min(height, histogram[leftSearch]);
            } else {
                rightSearch++;
                height = Math.min(height, histogram[rightSearch]);
            }
            max = Math.max(max, height * (rightSearch - leftSearch + 1));
        }
        while (leftSearch > start) {
            leftSearch--;
            height = Math.min(height, histogram[leftSearch]);
            max = Math.max(max, height * (rightSearch - leftSearch + 1));
        }
        while (rightSearch < end) {
            rightSearch++;
            height = Math.min(height, histogram[rightSearch]);
            max = Math.max(max, height * (rightSearch - leftSearch + 1));
        }

        return max;
    }
}
