package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q12886 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        boolean[][] isVisited = new boolean[1501][1501];
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        if ((a + b + c) % 3 != 0) {
            System.out.println(0);
            return;
        }

        Queue<Stone> q = new LinkedList<>();
        q.add(new Stone(a, b, c));
        while (!q.isEmpty()) {
            Stone stone = q.poll();
            if (stone.stones[0] == stone.stones[1]) {
                if (stone.stones[0] == stone.stones[2]) {
                    System.out.println(1);
                    return;
                }
            }
            Stone s1 = new Stone(stone.stones[0] * 2, stone.stones[1], stone.stones[2] - stone.stones[0]);
            Stone s2 = new Stone(stone.stones[0], stone.stones[1] * 2, stone.stones[2] - stone.stones[1]);
            if (!isVisited[s1.stones[0]][s1.stones[1]]) {
                isVisited[s1.stones[0]][s1.stones[1]] = true;
                q.add(s1);
            }
            if (!isVisited[s2.stones[0]][s2.stones[1]]) {
                isVisited[s2.stones[0]][s2.stones[1]] = true;
                q.add(s2);
            }
        }
        System.out.println(0);
    }

    private static class Stone {
        int[] stones = new int[3];

        public Stone(int a, int b, int c) {
            stones[0] = a;
            stones[1] = b;
            stones[2] = c;
            Arrays.sort(stones);
        }
    }
}
