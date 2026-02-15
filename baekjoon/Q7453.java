package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q7453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];
        int[] AB = new int[N * N];
        int[] CD = new int[N * N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[index] = A[i] + B[j];
                CD[index] = C[i] + D[j];
                index++;
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);
        long res = solve(CD, AB);
        System.out.println(res);
    }

    private static long solve(int[] CD, int[] AB) {
        long res = 0;
        int left = 0, right = CD.length - 1;
        while (left < AB.length && right >= 0) {
            if (AB[left] + CD[right] < 0) {
                left++;
            } else if (AB[left] + CD[right] > 0) {
                right--;
            } else {
                left++;
                right--;
                long leftCnt = 1L, rightCnt = 1L;
                while (left < AB.length && AB[left] == AB[left - 1]) {
                    left++;
                    leftCnt++;
                }
                while (right >= 0 && CD[right] == CD[right + 1]) {
                    right--;
                    rightCnt++;
                }
                res += leftCnt * rightCnt;
            }
        }
        return res;
    }
}
