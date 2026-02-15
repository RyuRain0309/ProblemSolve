package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        int sPoint = 0;
        int ePoint = N - 1;
        long res = Long.MAX_VALUE;
        long sNum = 0;
        long eNum = 0;
        while (sPoint < ePoint) {
            long sum = arr[sPoint] + arr[ePoint];
            if (sum == 0) {
                sNum = arr[sPoint];
                eNum = arr[ePoint];
                break;
            }

            if (Math.abs(sum) < res) {
                res = Math.abs(sum);
                sNum = arr[sPoint];
                eNum = arr[ePoint];
            }
            if (sum < 0) {
                sPoint++;
            } else {
                ePoint--;
            }
        }

        System.out.print(sNum + " " + eNum);
    }
}
