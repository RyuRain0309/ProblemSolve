package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q4195 {
    static int[] uf;
    static int[] lev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int F = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();
            uf = new int[2 * F];
            lev = new int[2 * F];
            for (int j = 0; j < 2 * F; j++) {
                uf[j] = j;
                lev[j] = 1;
            }

            int cnt = 0;
            for (int j = 0; j < F; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                String a = st.nextToken();
                String b = st.nextToken();

                if (!map.containsKey(a)) {
                    map.put(a, cnt++);
                }
                if (!map.containsKey(b)) {
                    map.put(b, cnt++);
                }

                sb.append(union(map.get(a), map.get(b))).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int union(int a, int b) {
        a = unionFind(a);
        b = unionFind(b);
        if (a != b) {
            if (a > b) {
                uf[b] = a;
                lev[a] += lev[b];
                return lev[a];
            } else {
                uf[a] = b;
                lev[b] += lev[a];
                return lev[b];
            }
        }
        return lev[a];
    }

    private static int unionFind(int num) {
        if (num == uf[num]) {
            return num;
        }
        return uf[num] = unionFind(uf[num]);
    }
}
