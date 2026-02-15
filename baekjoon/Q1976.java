package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1976 {
    static int[] uf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        uf = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            uf[i] = i;
        }
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    int a = unionFind(i);
                    int b = unionFind(j);
                    if (a != b) {
                        if (a > b) {
                            uf[a] = b;
                        } else {
                            uf[b] = a;
                        }
                    }
                }
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        int std = unionFind(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < M; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (std != unionFind(temp)) {
                System.out.print("NO");
                System.exit(0);
            }
        }
        System.out.print("YES");
    }

    private static int unionFind(int num) {
        if (num == uf[num]) {
            return num;
        }
        return uf[num] = unionFind(uf[num]);
    }
}
