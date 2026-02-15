package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14890 {
    static int[] arr;
    static int N;
    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, arr, 0, N);
            res += solve() ? 1 : 0;
            for (int j = 0; j < N; j++) {
                arr[j] = map[j][i];
            }
            res += solve() ? 1 : 0;
        }

        System.out.println(res);
    }

    private static boolean solve() {
        int pre = arr[0];
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (Math.abs(arr[i] - pre) >= 2) {
                return false;
            }
            if (pre == arr[i]) {
                cnt++;
                continue;
            }

            if (arr[i] > pre) {
                if (cnt < L) {
                    return false;
                }
                pre = arr[i];
                cnt = 1;
                continue;
            }

            if (arr[i] < pre) {
                if (N - i < L) {
                    return false;
                }
                for (int j = 1; j < L; j++) {
                    if (arr[i] != arr[i + j]) {
                        return false;
                    }
                }
                i += L - 1;
                pre = arr[i];
                cnt = 0;
            }
        }
        return true;
    }
}
