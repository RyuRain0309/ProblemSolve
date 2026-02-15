package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3190 {
    static final int[] dy = {-1, 0, 1, 0}; // 상 우 하 좌
    static final int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        boolean[][] apple = new boolean[N + 1][N + 1];
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            apple[y][x] = true;
        }
        Queue<Info> info = new LinkedList<>();
        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            info.add(new Info(X, C));
        }
        int headY = 1, headX = 1;
        int direct = 1, time = 0;
        boolean[][] snakeMap = new boolean[N + 1][N + 1];
        Queue<Pos> snake = new LinkedList<>();
        snake.offer(new Pos(headY, headX));
        snakeMap[headY][headX] = true;
        while (true) {
            if (!info.isEmpty()) {
                if (time == info.peek().time) {
                    if (info.peek().dir == 'D') {
                        direct++;
                    } else {
                        direct--;
                    }
                    if (direct > 3) {
                        direct = 0;
                    }
                    if (direct < 0) {
                        direct = 3;
                    }
                    info.poll();
                }
            }
            headY += dy[direct];
            headX += dx[direct];
            time++;
            if (headY <= 0 || headY > N || headX <= 0 || headX > N) {
                break;
            }
            if (snakeMap[headY][headX]) {
                break;
            }
            if (!apple[headY][headX]) {
                Pos pos = snake.poll();
                snakeMap[pos.y][pos.x] = false;
            } else {
                apple[headY][headX] = false;
            }
            snake.offer(new Pos(headY, headX));
            snakeMap[headY][headX] = true;
        }
        System.out.println(time);
    }

    private static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class Info {
        int time;
        char dir;

        public Info(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }
}
