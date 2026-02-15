package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1717 {
    static int[] uf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        uf = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            uf[i] = i;
        }
        int M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (u == 0) {
                a = unionFind(a);
                b = unionFind(b);
                if (a > b) {
                    uf[a] = b;
                } else {
                    uf[b] = a;
                }
            } else {
                if (unionFind(a) == unionFind(b)) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.print(sb);
    }

    private static int unionFind(int num) {
        if (num == uf[num]) {
            return num;
        }
        return uf[num] = unionFind(uf[num]);
    }
}
