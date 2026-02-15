package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q17837 {
    static final int[] dy = {0, 0, -1, 1};
    static final int[] dx = {1, -1, 0, 0};
    static final int[] changeDir = {1, 0, 3, 2};

    static int[][] map;
    static int[][] pos;
    static Stack<Integer>[][] board;

    static int N, K, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        board = new Stack[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new Stack<>();
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        pos = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            board[y][x].push(i);
            pos[i][0] = y;
            pos[i][1] = x;
            pos[i][2] = dir;
        }

        res = 0;
        while (res <= 1000) {
            for (int i = 0; i < K; i++) {
                move(i, false);
            }
            res++;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j].size() >= 4) {
                        System.out.println(res);
                        return;
                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static void move(int i, boolean re) {
        int ty = pos[i][0] + dy[pos[i][2]];
        int tx = pos[i][1] + dx[pos[i][2]];
        if (ty < 0 || ty >= N || tx < 0 || tx >= N || map[ty][tx] == 2) {
            if (!re) {
                pos[i][2] = changeDir[pos[i][2]];
                move(i, true);
            }
            return;
        } else if (map[ty][tx] == 1) {
            redGround(i, pos[i][0], pos[i][1], ty, tx);
        } else if (map[ty][tx] == 0) {
            whiteGround(i, pos[i][0], pos[i][1], ty, tx);
        }
        if (board[ty][tx].size() >= 4) {
            System.out.println(res + 1);
            System.exit(0);
        }
    }

    private static void whiteGround(int i, int y, int x, int ty, int tx) {
        Stack<Integer> temp = new Stack<>();
        while (board[y][x].peek() != i) {
            temp.push(board[y][x].pop());
        }
        temp.push(board[y][x].pop());
        while (!temp.isEmpty()) {
            int t = temp.pop();
            pos[t][0] = ty;
            pos[t][1] = tx;
            board[ty][tx].push(t);
        }
    }

    private static void redGround(int i, int y, int x, int ty, int tx) {
        while (board[y][x].peek() != i) {
            int t = board[y][x].pop();
            pos[t][0] = ty;
            pos[t][1] = tx;
            board[ty][tx].add(t);
        }
        pos[i][0] = ty;
        pos[i][1] = tx;
        board[ty][tx].add(board[y][x].pop());
    }
}
