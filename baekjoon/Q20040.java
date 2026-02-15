package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20040 {
    static int[] uf;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        uf = new int[N];
        isVisited = new boolean[N];
        for (int i = 0; i < N; i++) {
            uf[i] = i;
        }
        int ans = 0;
        boolean getAns = false;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (getAns) continue;

            int aUni = unionFind(a);
            int bUni = unionFind(b);
            if (aUni == bUni) {
                if (isVisited[a] && isVisited[b]) {
                    ans = i;
                    getAns = true;
                }
            } else {
                if (aUni > bUni) {
                    uf[aUni] = bUni;
                } else {
                    uf[bUni] = aUni;
                }
            }
            isVisited[a] = true;
            isVisited[b] = true;
        }
        System.out.println(ans);
    }

    private static int unionFind(int num) {
        if (num == uf[num]) {
            return num;
        }

        return uf[num] = unionFind(uf[num]);
    }
}
