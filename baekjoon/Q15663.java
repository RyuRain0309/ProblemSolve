package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제
 * N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * <p>
 * N개의 자연수 중에서 M개를 고른 수열
 * 입력
 * 첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 * <p>
 * 둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
 */
public class Q15663 {
    static int N, M;
    static int[] arr;
    static Stack<Integer> list = new Stack<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        perm(0, 0);
        System.out.print(sb);
    }

    private static void perm(int depth, int flag) {
        if (depth == M) {
            for (int i : list) sb.append(i).append(" ");
            sb.deleteCharAt(sb.length() - 1);
            sb.append('\n');
            return;
        }

        int lastNum = -1;
        for (int i = 0; i < N; i++) {
            if (lastNum == arr[i]) continue;
            if ((flag & (1 << i)) != 0) continue;

            lastNum = arr[i];
            list.push(arr[i]);
            perm(depth + 1, flag | (1 << i));
            list.pop();
        }
    }
}
