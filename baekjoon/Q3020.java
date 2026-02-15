package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] u = new int[H + 2];
        int[] d = new int[H + 2];

        for (int i = 0; i < N / 2; i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            d[a]++;
            u[H + 1 - b]++;
        }

        for (int i = 1; i <= H; i++) {
            u[i] += u[i - 1];
        }

        for (int i = H; i > 0; i--) {
            d[i] += d[i + 1];
        }

        int least = Integer.MAX_VALUE;
        int leastCnt = 1;
        for (int i = 1; i <= H; i++) {
            int sum = u[i] + d[i];
            if (sum < least) {
                least = sum;
                leastCnt = 1;
            } else if (sum == least) {
                leastCnt++;
            }
        }
        System.out.print(least + " " + leastCnt);
    }
}
