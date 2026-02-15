package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q17299 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] cnt = new int[10000001];
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] res = new int[N];
        Stack<Integer> s = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cnt[arr[i]]++;
        }
        for (int i = N - 1; i >= 0; i--) {
            while (true) {
                if (s.empty()) {
                    s.push(arr[i]);
                    res[i] = -1;
                    break;
                } else if (cnt[arr[i]] < cnt[s.peek()]) {
                    res[i] = s.peek();
                    s.push(arr[i]);
                    break;
                } else {
                    s.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.print(sb);
    }
}
