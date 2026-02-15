package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q2587 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = 5, sum = 0;
        int[] array = new int[5];
        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());
            array[n] = num;
            sum += num;
        }
        Arrays.sort(array);
        sb.append(sum / 5).append("\n").append(array[2]);
        System.out.print(sb);
        br.close();
    }
}
