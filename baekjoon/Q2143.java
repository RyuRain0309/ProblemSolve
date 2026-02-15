package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = Long.parseLong(br.readLine());

        int a = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arrA = new int[a + 1];
        for (int i = 1; i <= a; i++) {
            arrA[i] = arrA[i - 1] + Integer.parseInt(st.nextToken());
        }

        int b = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arrB = new int[b + 1];
        for (int i = 1; i <= b; i++) {
            arrB[i] = arrB[i - 1] + Integer.parseInt(st.nextToken());
        }

        ArrayList<Long> sumA = new ArrayList<>();
        for (int i = 1; i <= a; i++) {
            for (int j = 0; j < i; j++) {
                sumA.add((long) (arrA[i] - arrA[j]));
            }
        }

        ArrayList<Long> sumB = new ArrayList<>();
        for (int i = 1; i <= b; i++) {
            for (int j = 0; j < i; j++) {
                sumB.add((long) (arrB[i] - arrB[j]));
            }
        }

        sumA.sort(Comparator.naturalOrder());
        sumB.sort(Comparator.naturalOrder());

        long res = getRes(sumB, sumA, T);
        System.out.println(res);
    }

    private static long getRes(ArrayList<Long> sumB, ArrayList<Long> sumA, long T) {
        long res = 0;
        int left = 0;
        int right = sumB.size() - 1;
        while (left < sumA.size() && right >= 0) {
            long valA = sumA.get(left);
            long valB = sumB.get(right);
            long sum = valA + valB;
            if (sum == T) {
                long ac = 0, bc = 0;
                while (left < sumA.size() && Objects.equals(sumA.get(left), valA)) {
                    left++;
                    ac++;
                }
                while (right >= 0 && Objects.equals(sumB.get(right), valB)) {
                    right--;
                    bc++;
                }
                res += ac * bc;
            }


            if (sum < T) left++;
            else if (sum > T) right--;
        }
        return res;
    }
}
