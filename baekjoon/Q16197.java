package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q16197 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N, M;
    static boolean[][] map;
    static boolean[][][][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        isVisited = new boolean[N][M][N][M];
        boolean firstCondition = true;
        BallPosition initBall = new BallPosition();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char temp = s.charAt(j);
                if (temp == 'o') {
                    if (firstCondition) {
                        initBall.firstBallY = i;
                        initBall.firstBallX = j;
                        firstCondition = false;
                    }
                    initBall.secondBallY = i;
                    initBall.secondBallX = j;
                }

                if (temp == '#') {
                    map[i][j] = true;
                }
            }
        }
        initBall.cnt = 0;
        PriorityQueue<BallPosition> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.cnt));
        q.add(initBall);
        while (!q.isEmpty()) {
            BallPosition ball = q.poll();
//            System.out.println(ball.firstBallY + " " + ball.firstBallX + " " + ball.secondBallY + " " + ball.secondBallX + " " + ball.cnt);
            if (ball.cnt >= 10) {
                System.out.println(-1);
                return;
            }
            for (int i = 0; i < 4; i++) {
                BallPosition moveBall = new BallPosition(
                        ball.firstBallY + dy[i],
                        ball.firstBallX + dx[i],
                        ball.secondBallY + dy[i],
                        ball.secondBallX + dx[i],
                        ball.cnt + 1);
                int cnt = 0;

                if (moveBall.firstBallY < 0 || moveBall.firstBallY >= N || moveBall.firstBallX < 0 || moveBall.firstBallX >= M) {
                    cnt++;
                }
                if (moveBall.secondBallY < 0 || moveBall.secondBallY >= N || moveBall.secondBallX < 0 || moveBall.secondBallX >= M) {
                    cnt++;
                }
                if (cnt == 1) {
                    System.out.println(moveBall.cnt);
                    return;
                }
                if (cnt == 0) {
                    int a = 0;
                    if (map[moveBall.firstBallY][moveBall.firstBallX]) {
                        moveBall.firstBallY -= dy[i];
                        moveBall.firstBallX -= dx[i];
                        a++;
                    }
                    if (map[moveBall.secondBallY][moveBall.secondBallX]) {
                        moveBall.secondBallY -= dy[i];
                        moveBall.secondBallX -= dx[i];
                        a++;
                    }
                    if (a != 2) {
                        if (!isVisited[moveBall.firstBallY][moveBall.firstBallX][moveBall.secondBallY][moveBall.secondBallX]) {
                            q.add(moveBall);
                            isVisited[moveBall.firstBallY][moveBall.firstBallX][moveBall.secondBallY][moveBall.secondBallX] = true;
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static class BallPosition {
        int firstBallY, firstBallX, secondBallY, secondBallX, cnt;

        public BallPosition() {
        }

        public BallPosition(int firstBallY, int firstBallX, int secondBallY, int secondBallX, int cnt) {
            this.firstBallY = firstBallY;
            this.firstBallX = firstBallX;
            this.secondBallY = secondBallY;
            this.secondBallX = secondBallX;
            this.cnt = cnt;
        }
    }
}


