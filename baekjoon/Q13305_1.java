package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13305_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] distance = new long[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N - 1; i++) {
            distance[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        long last = Long.parseLong(st.nextToken());
        long res = 0;
        int lastPos = 0;
        for (int i = 1; i < N; i++) {
            long temp = Long.parseLong(st.nextToken());
            if (temp < last || i == N - 1) {
                for (int j = lastPos; j < i; j++) {
                    res += distance[j] * last;
                }
                last = temp;
                lastPos = i;
            }
        }
        System.out.print(res);
    }
}
