package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] map = new int[101];
        boolean[] isVisited = new boolean[101];
        for (int i = 0; i < 101; i++) {
            map[i] = i;
        }
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(map[1], 0));
        isVisited[map[1]] = true;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            //System.out.println(pair.val + " " + pair.cnt);
            if (pair.val == 100) {
                System.out.print(pair.cnt);
                break;
            }
            for (int i = 6; i > 0; i--) {
                if (pair.val + i > 100) {
                    continue;
                }
                if (!isVisited[map[pair.val + i]]) {
                    q.add(new Pair(map[pair.val + i], pair.cnt + 1));
                    isVisited[map[pair.val + i]] = true;
                }
            }
        }
    }

    private static class Pair {
        private final int val;
        private final int cnt;

        Pair(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }
}
