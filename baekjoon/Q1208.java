package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.StringTokenizer;

public class Q1208 {
    static int[] arr;
    static int N, S;
    static long res = 0;
    static ArrayList<Long> p1 = new ArrayList<>();
    static ArrayList<Long> p2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, N / 2, 0L, true);
        dfs(N / 2, N, 0L, false);

        Collections.sort(p1);
        Collections.sort(p2);
        int left = 0;
        int right = p2.size() - 1;
        while (left < p1.size() && right >= 0) {
            long sum = p1.get(left) + p2.get(right);
            if (sum == S) {
                int tl = 1, tr = 1;
                left++;
                right--;
                while (left < p1.size() && Objects.equals(p1.get(left), p1.get(left - 1))) {
                    tl++;
                    left++;
                }
                while (right >= 0 && Objects.equals(p2.get(right), p2.get(right + 1))) {
                    tr++;
                    right--;
                }
                res += (long) tl * tr;
            }
            if (sum < S) {
                left++;
            }
            if (sum > S) {
                right--;
            }
        }
        if (S == 0) {
            res--;
        }
        System.out.println(res);
    }

    private static void dfs(int depth, int end, long sum, boolean isP1) {
        if (depth == end) {
            if (isP1) {
                p1.add(sum);
                return;
            }
            p2.add(sum);
            return;
        }

        dfs(depth + 1, end, sum, isP1);
        dfs(depth + 1, end, sum + arr[depth], isP1);
    }
}
