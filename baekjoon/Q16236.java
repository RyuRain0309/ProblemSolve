package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16236 {

    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    static int[][] map;
    static boolean[][] isVisited;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Queue<Pos> q = new LinkedList<>();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    q.add(new Pos(i, j, 0));
                    map[i][j] = 0;
                }
            }
        }

        int res = 0;
        int sharkSize = 2;
        int eatFishNumber = 0;
        while (true) {
            ArrayList<Pos> fishes = new ArrayList<>();
            isVisited = new boolean[N][N];
            int breakCnt = Integer.MAX_VALUE;
            while (!q.isEmpty()) {
                Pos pos = q.poll();
                pos.cnt++;
                if (pos.cnt > breakCnt) {
                    q.clear();
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int ty = dy[i] + pos.y;
                    int tx = dx[i] + pos.x;
                    if (ty < 0 || ty >= N || tx < 0 || tx >= N) {
                        continue;
                    }
                    if (isVisited[ty][tx] || map[ty][tx] > sharkSize) {
                        continue;
                    }
                    if (map[ty][tx] != 0 && map[ty][tx] < sharkSize) {
                        breakCnt = pos.cnt;
                        fishes.add(new Pos(ty, tx, pos.cnt));
                    }
                    q.add(new Pos(ty, tx, pos.cnt));
                    isVisited[ty][tx] = true;
                }
            }
            if (fishes.isEmpty()) {
                System.out.println(res);
                return;
            }
            fishes.sort((o1, o2) -> {
                if (o1.y == o2.y) {
                    return o1.x - o2.x;
                }
                return o1.y - o2.y;
            });
            q.add(new Pos(fishes.get(0).y, fishes.get(0).x, 0));
            res += fishes.get(0).cnt;
            map[fishes.get(0).y][fishes.get(0).x] = 0;
            eatFishNumber++;
            if (sharkSize == eatFishNumber) {
                sharkSize++;
                eatFishNumber = 0;
            }
        }
    }

    private static class Pos {
        int y, x, cnt;

        public Pos(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
