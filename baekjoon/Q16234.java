package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16234 {
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int day = 0;
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            boolean isFlag = true;
            boolean[][][] borderLine = new boolean[N][N][4];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < 4; k++) {
                        int ty = i + dy[k];
                        int tx = j + dx[k];
                        if (ty < 0 || ty >= N || tx < 0 || tx >= N) {
                            continue;
                        }
                        int diff = Math.abs(map[i][j] - map[ty][tx]);
                        if (diff >= L && diff <= R) {
                            borderLine[i][j][k] = true;
                            isFlag = false;
                        }
                    }
                }
            }
            if (isFlag) {
                break;
            }

            boolean[][] isVisited = new boolean[N][N];
            Queue<int[]> track = new LinkedList<>();
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isVisited[i][j]) {
                        continue;
                    }
                    int sum = 0;
                    q.add(new int[]{i, j});
                    sum += map[i][j];
                    isVisited[i][j] = true;
                    track.add(new int[]{i, j});

                    while (!q.isEmpty()) {
                        int[] pos = q.poll();
                        for (int k = 0; k < 4; k++) {
                            if (!borderLine[pos[0]][pos[1]][k]) {
                                continue;
                            }
                            int ty = pos[0] + dy[k];
                            int tx = pos[1] + dx[k];
                            if (isVisited[ty][tx]) {
                                continue;
                            }
                            q.add(new int[]{ty, tx});
                            sum += map[ty][tx];
                            isVisited[ty][tx] = true;
                            track.add(new int[]{ty, tx});
                        }
                    }


                    int avg = sum / track.size();
                    while (!track.isEmpty()) {
                        int[] pos = track.poll();
                        map[pos[0]][pos[1]] = avg;
                    }
                }
            }
            day++;
        }
        System.out.println(day);
    }
}
