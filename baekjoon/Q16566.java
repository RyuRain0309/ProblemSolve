package baekjoon;

import java.io.*;
import java.util.*;

public class Q16566 {
    static int[] parent;

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int rootX = find(a);
        int rootY = find(b);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }

    static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] cards = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        parent = new int[M + 1];
        for (int i = 0; i <= M; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            int minus = Integer.parseInt(st.nextToken());

            int idx = upperBound(cards, minus);
            int realIdx = find(idx);
            sb.append(cards[realIdx]).append("\n");
            union(realIdx, realIdx + 1);
        }

        System.out.print(sb);
    }
}