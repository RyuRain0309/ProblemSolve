package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2566 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int max = 0;
        int xPos = 1, yPos = 1;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp > max) {
                    max = temp;
                    yPos = i + 1;
                    xPos = j + 1;
                }
            }
        }
        sb.append(max).append("\n").append(yPos).append(" ").append(xPos);
        System.out.print(sb);
        br.close();
    }
}