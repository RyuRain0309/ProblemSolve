package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1525_TBU {
    static int FIR = 100_000_000;
    static int RES = 123_456_780;
    static int[] dz = {-1, 1, -3, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int num = 0;
        int zeroPos = 0;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                num *= 10;
                num += Integer.parseInt(st.nextToken());
                if (num % 10 == 0) {
                    zeroPos = (3 * i) + j;
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        set.add(num);
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{num, zeroPos, 0});
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int i = 0; i < 4; i++) {
                if (i == 0 && (poll[1] == 0 || poll[1] == 3 || poll[1] == 6)) {
                    continue;
                }
                if (i == 1 && (poll[1] == 2 || poll[1] == 5 || poll[1] == 8)) {
                    continue;
                }
                int nz = dz[i] + poll[1];
                if (nz < 0 || nz > 8) {
                    continue;
                }
                int swapNum = (poll[0] / (tenSquare(9 - nz))) % 10;
                int temp = poll[0] + swapNum * (tenSquare(9 - poll[1]));
                temp = (temp / tenSquare(9 - (nz)) + (temp % tenSquare(9 - nz)));
            }
        }
    }

    private static int tenSquare(int i) {
        int n = 1;
        for (int j = 0; j < i; j++) {
            n *= 10;
        }
        return n;
    }
}
