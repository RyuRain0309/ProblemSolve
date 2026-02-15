package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int sPoint = 0;
        int ePoint = 1;
        int res = Integer.MAX_VALUE;
        while (sPoint <= N && ePoint <= N) {
            int sum = arr[ePoint] - arr[sPoint];
            if (sum >= S) {
                res = Math.min(res, ePoint - sPoint);
                sPoint++;
            } else {
                ePoint++;
            }
        }
        System.out.print(res == Integer.MAX_VALUE ? 0 : res);
    }
}
