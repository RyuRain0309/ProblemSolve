package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int result = 0;
        int[] map = new int[W];
        int pivot = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < W; i++) {
            map[i] = Integer.parseInt(st.nextToken());
            int h = Math.min(map[i], map[pivot]);
            for (int j = pivot; j < i; j++) {
                if (map[j] == h) {
                    result += h - map[j];
                    map[j] = h;
                }
            }
            if (map[i] >= map[pivot]) {
                pivot = i;
            }
        }

        System.out.println(result);
    }
}
