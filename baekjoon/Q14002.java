package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Q14002 {
    static Vector<Integer> v = new Vector<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] arrPos = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        v.add(arr[0]);
        arrPos[0] = 1;
        for (int i = 1; i < N; i++) {
            if (v.get(v.size() - 1) < arr[i]) {
                v.add(arr[i]);
                arrPos[i] = v.size();
            } else {
                int pos = lowerBound(arr[i]);
                v.set(pos, arr[i]);
                arrPos[i] = pos + 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(v.size()).append("\n");
        int[] arrRes = new int[v.size()];
        int cnt = v.size();
        for (int i = N - 1; i >= 0; i--) {
            if (arrPos[i] == cnt) {
                arrRes[cnt - 1] = arr[i];
                cnt--;
            }
        }
        for (int i : arrRes) {
            sb.append(i).append(" ");
        }
        System.out.print(sb);
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