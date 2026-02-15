package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q14891 {
    static final int INIT = 9090;
    static int[][] cogWheel = new int[4][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                cogWheel[i][j] = c[j] - '0';
            }
        }
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            int[] move = new int[4];
            Arrays.fill(move, INIT);
            move[num] = dir;
            Queue<Integer> q = new LinkedList<>();
            q.add(num);
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur + 1 < 4 && move[cur + 1] == INIT) {
                    if (cogWheel[cur][2] != cogWheel[cur + 1][6]) {
                        move[cur + 1] = move[cur] == 1 ? -1 : 1;
                        q.add(cur + 1);
                    }
                }
                if (cur - 1 >= 0 && move[cur - 1] == INIT) {
                    if (cogWheel[cur - 1][2] != cogWheel[cur][6]) {
                        move[cur - 1] = move[cur] == 1 ? -1 : 1;
                        q.add(cur - 1);
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                if (move[j] != 0 && move[j] != INIT) {
                    cogWheelMove(j, move[j]);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 4; i++) {
            if (cogWheel[i][0] == 1) {
                res += 1 << i;
            }
        }
        System.out.println(res);
    }

    private static void cogWheelMove(int index, int dir) {
        int[] temp = new int[8];
        for (int i = 0; i < 8; i++) {
            int a = i - dir;
            if (a == 8) a = 0;
            if (a == -1) a = 7;
            temp[i] = cogWheel[index][a];
        }
        cogWheel[index] = temp;
    }
}
