package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q1450 {
    static int N, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        dfs(0, N / 2 - 1, 0, left);
        dfs(N / 2, N - 1, 0, right);
        Collections.sort(left);
        Collections.sort(right);

        int ans = 0;
        int point = right.size() - 1;
        for (Integer integer : left) {
            while (point >= 0 && integer + right.get(point) > C) {
                point--;
            }
            ans += point + 1;
        }

        System.out.print(ans);
    }

    private static void dfs(int lo, int hi, int sum, ArrayList<Integer> list) {
        if (sum > C) {
            return;
        }
        if (lo > hi) {
            list.add(sum);
            return;
        }
        dfs(lo + 1, hi, sum, list);
        dfs(lo + 1, hi, sum + arr[lo], list);
    }
}
