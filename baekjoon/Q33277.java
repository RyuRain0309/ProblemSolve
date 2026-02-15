package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q33277 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int minute = (int) ((double) M / N * 1440.0);
        StringBuilder sb = new StringBuilder();
        if (minute / 60 < 10) {
            sb.append("0");
        }
        sb.append(minute / 60);
        sb.append(":");
        if (minute % 60 < 10) {
            sb.append("0");
        }
        sb.append(minute % 60);
        System.out.println(sb);
    }
}
