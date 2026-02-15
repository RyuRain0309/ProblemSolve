package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9020 {
    public static boolean[] prime = new boolean[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        getPrime();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int firstPartition = n / 2;
            int secondPartition = n / 2;

            while (true) {
                if (!prime[firstPartition] && !prime[secondPartition]) {
                    sb.append(firstPartition).append(' ').append(secondPartition).append('\n');
                    break;
                }
                firstPartition--;
                secondPartition++;
            }
        }
        System.out.print(sb);
    }

    static void getPrime() {

        prime[0] = prime[1] = true;

        for (int i = 2; i <= Math.sqrt(prime.length); i++) {
            if (prime[i]) continue;

            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}