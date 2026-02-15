package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> rank = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] temp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(temp);
        int cnt = 0;
        for (int i : temp) {
            if (rank.containsKey(i)) {
                continue;
            }
            rank.put(i, cnt++);
        }

        for (Integer i : arr) {
            int val = rank.get(i);
            sb.append(val).append(" ");
        }
        System.out.println(sb);
        br.close();
    }
}
