package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q12869 {
    static int[] mutaliskA = {9, 9, 3, 3, 1, 1};
    static int[] mutaliskB = {3, 1, 9, 1, 9, 3};
    static int[] mutaliskC = {1, 3, 1, 9, 3, 9};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] SCV = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            SCV[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][][] dp = new boolean[61][61][61];
        Queue<SCVs> q = new LinkedList<>();
        q.add(new SCVs(SCV, 0));
        while (!q.isEmpty()) {
            SCVs scv = q.poll();
            for (int i = 0; i < 6; i++) {
                int tA = scv.SCV[0] - mutaliskA[i];
                int tB = scv.SCV[1] - mutaliskB[i];
                int tC = scv.SCV[2] - mutaliskC[i];
                tA = Math.max(tA, 0);
                tB = Math.max(tB, 0);
                tC = Math.max(tC, 0);
                if (tA == 0 && tB == 0 && tC == 0) {
                    System.out.println(scv.cnt + 1);
                    return;
                }
                int[] tSCV = {tA, tB, tC};
                Arrays.sort(tSCV);
                if (dp[tSCV[0]][tSCV[1]][tSCV[2]]) {
                    continue;
                }
                dp[tSCV[0]][tSCV[1]][tSCV[2]] = true;
                q.add(new SCVs(tSCV, scv.cnt + 1));
            }
        }
    }

    static class SCVs {
        int[] SCV = new int[3];
        int cnt;

        public SCVs(int[] SCV, int cnt) {
            this.SCV[0] = SCV[0];
            this.SCV[1] = SCV[1];
            this.SCV[2] = SCV[2];
            Arrays.sort(this.SCV);
            this.cnt = cnt;
        }
    }
}
