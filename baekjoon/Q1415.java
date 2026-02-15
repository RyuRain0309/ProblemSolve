package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Q1415 {
    static boolean[] prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        int[] arrCnt = new int[10_001];
        int sum = 0;
        int zeroCnt = 1;
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            if (temp == 0) {
                zeroCnt++;
                continue;
            }
            if (!arr.contains(temp)) {
                arr.add(temp);
            }
            sum += temp;
            arrCnt[temp]++;
        }
        eratosthenesSieve(sum + 1);
        Collections.sort(arr);
        int sum2 = 0;
        long[] dp = new long[sum + 1];
        dp[0] = 1;
        for (Integer n : arr) {
            for (int i = sum2; i >= 0; i--) {
                for (int j = 1; j <= arrCnt[n]; j++) {
                    if (i + (j * n) > sum) {
                        continue;
                    }
                    dp[i + (j * n)] += dp[i];
                }
            }
            sum2 += n * arrCnt[n];
        }
        long res = 0;
        for (int i = 2; i <= sum; i++) {
            if (!prime[i]) {
                res += dp[i];
            }
        }
        System.out.println(res * zeroCnt);
    }

    static void eratosthenesSieve(int MAX) {
        prime = new boolean[MAX + 1];
        for (int i = 2; i * i < MAX; i++) {
            if (prime[i]) continue;
            for (int j = i + i; j < MAX; j += i) {
                prime[j] = true;
            }
        }
    }
}
