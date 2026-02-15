package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q3015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> s = new Stack<>();
        Stack<Integer> sCnt = new Stack<>();
        long res = 0;
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            int cnt = 1;
            while (!s.empty() && s.peek() <= temp) {
                int tempPop = s.pop();
                int tempCnt = sCnt.pop();
                res += tempCnt;
                if (tempPop == temp) {
                    cnt += tempCnt;
                }
            }

            if (!s.empty()) {
                res++;
            }

            s.push(temp);
            sCnt.push(cnt);
        }
        System.out.println(res);
    }
}
