package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Q2613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N];
        int max = 0;
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max += arr[i];
            min = Math.max(min, arr[i]);
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (min < max) {
            ArrayList<Integer> temp = new ArrayList<>();
            int median = (min + max) / 2;

            int cnt = 0, sum = 0;
            for (int i = 0; i < N; i++) {
                if (sum + arr[i] <= median) {
                    cnt++;
                    sum += arr[i];
                } else {
                    temp.add(cnt);
                    cnt = 1;
                    sum = arr[i];
                }
            }
            if (cnt != 0) {
                temp.add(cnt);
            }
            if (temp.size() <= M) {
                res = new ArrayList<>(temp);
                max = median;
            } else {
                min = median + 1;
            }
        }
        while (res.size() < M) {
            for (int i = res.size() - 1; i >= 0; i--) {
                if (res.get(i) != 1) {
                    int temp = res.get(i);
                    res.remove(i);
                    res.add(i, 1);
                    res.add(i, --temp);
                    break;
                }
            }
        }
        System.out.println(max);
        System.out.println(res.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }
}
