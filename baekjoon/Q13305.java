package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] distance = new long[N - 1];
        long[][] oil = new long[N][2];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N - 1; i++) {
            distance[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            oil[i][0] = Integer.parseInt(st.nextToken());
            oil[i][1] = i;
        }
        Arrays.sort(oil, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Math.toIntExact(o1[1] - o2[1]);
            }
            return Math.toIntExact(o1[0] - o2[0]);
        });
        int res = 0;
        int last = N - 1;
        for (int i = 0; i < N; i++) {
            if (oil[i][1] < last) {
                for (int j = (int) oil[i][1]; j < last; j++) {
                    res += distance[j] * oil[i][0];
                }
                last = (int) oil[i][1];
            }
            if (last == 0) {
                break;
            }
        }
        System.out.print(res);
    }
}
