package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q17071 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (N == K) {
            System.out.println(0);
            return;
        }
        int[][] visitedTime = new int[2][500_001];
        Arrays.fill(visitedTime[0], -1);
        Arrays.fill(visitedTime[1], -1);
        visitedTime[0][N] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{N, 0});
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int i = 0; i < 3; i++) {
                int tn = dn(poll[0], i);
                int cnt = poll[1] + 1;
                if (tn < 0 || tn > 500_000) {
                    continue;
                }
                if (visitedTime[cnt % 2][tn] != -1) {
                    continue;
                }
                visitedTime[cnt % 2][tn] = cnt;
                q.add(new int[]{tn, cnt});
            }
        }

        int i = 0;
        while (true) {
            i++;
            K += i;
            if (K > 500_000) {
                break;
            }
            if (visitedTime[i % 2][K] == -1) {
                continue;
            }
            if (visitedTime[i % 2][K] <= i) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    private static int dn(int n, int i) {
        if (i == 0) {
            return n - 1;
        }
        if (i == 1) {
            return n + 1;
        }
        if (i == 2) {
            return 2 * n;
        }
        return 0;
    }

}
