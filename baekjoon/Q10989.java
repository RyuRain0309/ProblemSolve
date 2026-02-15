package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        while (N-- > 0) {
            array[N] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(array);
        for (Integer i : array) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
