package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q16936 {

    static int N;
    static Num[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Num[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = new Num(Long.parseLong(st.nextToken()));
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (Num num : arr) {
            sb.append(num.num).append(" ");
        }
        System.out.println(sb);
    }

    static class Num implements Comparable<Num> {
        int square2, square3;
        long num;

        public Num(long num) {
            this.num = num;
            while (num % 2 == 0) {
                num = num / 2;
                square2++;
                if (num == 0) {
                    return;
                }
            }
            while (num % 3 == 0) {
                num = num / 3;
                square3++;
                if (num == 0) {
                    return;
                }
            }

        }

        @Override
        public int compareTo(Num o) {
            if (this.square3 == o.square3) {
                return this.square2 - o.square2;
            }
            return o.square3 - this.square3;
        }
    }
}
