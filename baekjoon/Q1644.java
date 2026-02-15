package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Q1644 {
    static ArrayList<Integer> prime = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        getPrime(N + 1);
        int res = 0;
        int sPoint = 0;
        int ePoint = 0;
        while (ePoint < prime.size() && prime.get(ePoint) <= N) {
            int sum = 0;
            for (int i = sPoint; i <= ePoint; i++) {
                sum += prime.get(i);
                if (sum > N) {
                    break;
                }
            }
            if (sum > N) {
                sPoint++;
            } else if (sum == N) {
                res++;
                sPoint++;
            } else {
                ePoint++;
            }
        }
        System.out.print(res);
    }

    private static void getPrime(int MAX) {
        boolean[] isPrime = new boolean[MAX + 1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 3; i < Math.sqrt(MAX + 1); i += 2) {
            if (isPrime[i]) {
                for (int j = i + i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        prime.add(2);
        for (int i = 3; i <= MAX; i += 2) {
            if (isPrime[i]) {
                prime.add(i);
            }
        }
    }
}
