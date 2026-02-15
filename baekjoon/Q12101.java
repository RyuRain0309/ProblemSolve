package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q12101 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<String>[] arr = new ArrayList[N + 3];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }
        arr[1].add("1");
        arr[2].add("1+1");
        arr[2].add("2");
        arr[3].add("1+1+1");
        arr[3].add("2+1");
        arr[3].add("1+2");
        arr[3].add("3");
        for (int i = 4; i <= N; i++) {
            for (int j = i - 3; j < i; j++) {
                for (String s : arr[j]) {
                    arr[i].add(s + "+" + (i - j));
                }
            }
        }
        Collections.sort(arr[N]);
        if (K - 1 < arr[N].size()) {
            System.out.println(arr[N].get(K - 1));
            return;
        }
        System.out.println(-1);
    }
}
