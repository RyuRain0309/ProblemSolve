package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q10816 {
    static Map<Integer, Integer> map = new HashMap<>();
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        arr = new ArrayList<>(map.keySet());
        Collections.sort(arr);

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int ans = binarySearch(Integer.parseInt(st.nextToken()), 0, arr.size());
            sb.append(ans).append(" ");
        }
        System.out.print(sb);
    }

    private static int binarySearch(int target, int lo, int hi) {
        if (lo == hi) return 0;

        int mid = (hi + lo) / 2;
        if (arr.get(mid) == target) {
            return map.get(target);
        } else if (arr.get(mid) < target) {
            return binarySearch(target, mid + 1, hi);
        } else {
            return binarySearch(target, lo, mid);
        }
    }
}
