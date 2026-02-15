package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2565 {
    static HashMap<Integer, Integer> map = new HashMap<>();
    static Vector<Integer> v = new Vector<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] key = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            key[i] = Integer.parseInt(st.nextToken());
            map.put(key[i], Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(key);
        boolean first = true;
        for (int i : key) {
            if (first) {
                v.add(map.get(i));
                first = false;
                continue;
            }
            if (v.get(v.size() - 1) < map.get(i)) {
                v.add(map.get(i));
            } else {
                int pos = lowerBound(map.get(i));
                v.set(pos, map.get(i));
            }
        }
        System.out.print(N - v.size());
    }

    private static int lowerBound(int key) {
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
