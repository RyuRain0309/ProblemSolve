package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int X = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int sPoint = 0;
        int ePoint = N - 1;
        int cnt = 0;
        while (sPoint < ePoint) {
            int sum = arr[sPoint] + arr[ePoint];
            if (sum < X) {
                sPoint++;
            } else if (sum == X) {
                cnt++;
                sPoint++;
            } else {
                ePoint--;
            }
        }
        System.out.print(cnt);
    }
}
