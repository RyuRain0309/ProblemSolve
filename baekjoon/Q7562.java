package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7562 {
    static int[] rangeX = {2, 2, -2, -2, 1, -1, 1, -1};
    static int[] rangeY = {1, -1, 1, -1, 2, 2, -2, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int repeat = Integer.parseInt(br.readLine());
        while (repeat-- > 0) {
            Queue<Pair> q = new LinkedList<>();
            int size = Integer.parseInt(br.readLine());
            boolean[][] isVisited = new boolean[size][size];
            st = new StringTokenizer(br.readLine(), " ");
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            q.add(new Pair(startY, startX, 0));
            st = new StringTokenizer(br.readLine(), " ");
            int endY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());

            isVisited[startY][startX] = true;
            while (!q.isEmpty()) {
                Pair pair = q.poll();
                if (pair.y == endY && pair.x == endX) {
                    sb.append(pair.cnt).append("\n");
                    break;
                }
                for (int i = 0; i < 8; i++) {
                    int moveY = pair.y + rangeY[i];
                    int moveX = pair.x + rangeX[i];
                    if (moveY < 0 || moveX < 0 || moveY >= size || moveX >= size) {
                        continue;
                    }
                    if (!isVisited[moveY][moveX]) {
                        q.add(new Pair(moveY, moveX, pair.cnt + 1));
                        isVisited[moveY][moveX] = true;
                    }
                }
            }
        }
        System.out.print(sb);
    }

    private static class Pair {
        private final int cnt;
        private final int y;
        private final int x;

        Pair(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
