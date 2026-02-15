package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Q12738 {
    static Vector<Integer> v = new Vector<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Long[] arr = new Long[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = (long) Integer.parseInt(st.nextToken());
        }
        v.add(Math.toIntExact(arr[0]));
        for (int i = 1; i < N; i++) {
            if (v.get(v.size() - 1) < arr[i]) {
                v.add(Math.toIntExact(arr[i]));
            } else {
                int pos = lowerBound(arr[i]);
                v.set(pos, Math.toIntExact(arr[i]));
            }
        }
        System.out.print(v.size());
    }

    private static int lowerBound(Long key) {
        int low = 0;
        int high = v.size() - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (key <= v.get(mid))
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }
}