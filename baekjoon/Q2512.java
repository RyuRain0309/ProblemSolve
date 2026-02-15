package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2512 {
    static int N;
    static int[] eachBudget;
    static int totalBudget;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eachBudget = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int biggestNum = 0;
        int total = 0;
        for (int i = 0; i < N; i++) {
            eachBudget[i] = Integer.parseInt(st.nextToken());
            total += eachBudget[i];
            biggestNum = Math.max(biggestNum, eachBudget[i]);
        }
        totalBudget = Integer.parseInt(br.readLine());
        if (totalBudget >= total) {
            System.out.println(biggestNum);
        } else {
            System.out.println(findBudget(1, totalBudget));
        }
    }

    private static int findBudget(int lo, int hi) {
        if (lo > hi) {
            return hi;
        }

        int mid = (lo + hi) / 2;
        int tempBudget = 0;
        for (int i = 0; i < N; i++) {
            tempBudget += Math.min(eachBudget[i], mid);
        }
        if (tempBudget == totalBudget) {
            return mid;
        } else if (tempBudget > totalBudget) {
            return findBudget(lo, mid - 1);
        } else {
            return findBudget(mid + 1, hi);
        }
    }
}
