package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2563 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] paper = new int[100][100];
        for (int[] ints : paper) {
            Arrays.fill(ints, 0);
        }
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = 100 - Integer.parseInt(st.nextToken());
            for (int i = y; i > y - 10; i--) {
                for (int j = x; j < x + 10; j++) {
                    paper[i][j] = 1;
                }
            }
        }
        int sum = 0;
        for (int[] ints : paper) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
        System.out.print(sum);
    }
}
