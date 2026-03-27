package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Q1027 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] h = new int[N];
        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        int[] visibleCount = new int[N];

        for (int i = 0; i < N - 1; i++) {
            double maxSlope = -Double.MAX_VALUE;

            for (int j = i + 1; j < N; j++) {
                double slope = (double) (h[j] - h[i]) / (j - i);

                if (j == i + 1 || slope > maxSlope) {
                    visibleCount[i]++;
                    visibleCount[j]++;
                    maxSlope = slope;
                }
            }
        }

        int maxVisible = 0;
        for (int i = 0; i < N; i++) {
            if (visibleCount[i] > maxVisible) {
                maxVisible = visibleCount[i];
            }
        }

        System.out.println(maxVisible);
    }
}