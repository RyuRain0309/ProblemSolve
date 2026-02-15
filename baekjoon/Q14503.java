package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14503 {
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static int[][] map;
    static int[] pos = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine(), " ");
        pos[0] = Integer.parseInt(st.nextToken());
        pos[1] = Integer.parseInt(st.nextToken());
        pos[2] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = 1;
        map[pos[0]][pos[1]] = 2;
        while (true) {
            if (!isDirty()) {
                if (map[pos[0] - dy[pos[2]]][pos[1] - dx[pos[2]]] == 1) {
                    break;
                }
                pos[0] -= dy[pos[2]];
                pos[1] -= dx[pos[2]];
                continue;
            }

            while (true) {
                pos[2] -= 1;
                if (pos[2] < 0) {
                    pos[2] = 3;
                }
                int tempY = pos[0] + dy[pos[2]];
                int tempX = pos[1] + dx[pos[2]];
                if (map[tempY][tempX] == 0) {
                    pos[0] = tempY;
                    pos[1] = tempX;
                    map[tempY][tempX] = 2;
                    res++;
                    break;
                }
            }
        }

        System.out.println(res);
    }

    private static boolean isDirty() {
        for (int i = 0; i <= 3; i++) {
            if (map[pos[0] + dy[i]][pos[1] + dx[i]] == 0) {
                return true;
            }
        }
        return false;
    }
}
