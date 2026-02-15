package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int ans = 1;
        int[] eatCnt = new int[d + 1];
        eatCnt[c]++;

        for (int i = 0; i < k; i++) {
            if (eatCnt[sushi[i]] == 0) {
                ans++;
            }
            eatCnt[sushi[i]]++;
        }

        int cnt = ans;
        for (int i = 0; i < N; i++) {
            int end = (i + k) % N;
            if (eatCnt[sushi[i]] == 1) {
                cnt--;
            }
            eatCnt[sushi[i]]--;

            if (eatCnt[sushi[end]] == 0) {
                cnt++;
            }
            eatCnt[sushi[end]]++;
            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }
}
