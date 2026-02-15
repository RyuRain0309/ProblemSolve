package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q19238 {
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, -1, 0, 1};
    static int[][] map;
    static int N, M, fuel;
    static Loc taxi;
    static Loc[][] passenger;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        passenger = new Loc[M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        taxi = new Loc(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, fuel);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            passenger[i][0] = new Loc(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
            passenger[i][1] = new Loc(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
        }
        for (int i = 0; i < M; i++) {
            int passengerNum = pickUp();
            if (passengerNum == -1) {
                System.out.println(-1);
                return;
            }
            if (dropOff(passengerNum)) {
                passenger[passengerNum][0].y = -1;
                passenger[passengerNum][0].x = -1;
            } else {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(taxi.fuel);
    }

    private static boolean dropOff(int passengerNum) {
        boolean[][] isVisited = new boolean[N][N];
        Queue<Loc> q = new LinkedList<>();
        q.add(new Loc(taxi.y, taxi.x, taxi.fuel));
        isVisited[taxi.y][taxi.x] = true;
        while (!q.isEmpty()) {
            Loc loc = q.poll();
            if (loc.fuel == -1) {
                return false;
            }
            for (int i = 0; i < M; i++) {
                if (loc.y == passenger[passengerNum][1].y && loc.x == passenger[passengerNum][1].x) {
                    int useFuel = taxi.fuel - loc.fuel;
                    taxi = loc;
                    taxi.fuel += useFuel * 2;
                    return true;
                }
            }
            for (int i = 0; i < 4; i++) {
                int ty = loc.y + dy[i];
                int tx = loc.x + dx[i];
                if (ty < 0 || ty >= N || tx < 0 || tx >= N) {
                    continue;
                }
                if (map[ty][tx] == 1 || isVisited[ty][tx]) {
                    continue;
                }
                q.add(new Loc(ty, tx, loc.fuel - 1));
                isVisited[ty][tx] = true;
            }
        }
        return false;
    }

    private static int pickUp() {
        boolean[][] isVisited = new boolean[N][N];
        int findPassenger = 0;
        ArrayList<Integer> passengers = new ArrayList<>();
        Queue<Loc> q = new LinkedList<>();
        q.add(new Loc(taxi.y, taxi.x, taxi.fuel));
        isVisited[taxi.y][taxi.x] = true;
        while (!q.isEmpty()) {
            Loc loc = q.poll();
            if (loc.fuel == -1) {
                return -1;
            }
            if (findPassenger != 0 && loc.fuel < findPassenger) {
                break;
            }
            for (int i = 0; i < M; i++) {
                if (loc.y == passenger[i][0].y && loc.x == passenger[i][0].x) {
                    findPassenger = loc.fuel;
                    passengers.add(i);
                }
            }
            for (int i = 0; i < 4; i++) {
                int ty = loc.y + dy[i];
                int tx = loc.x + dx[i];
                if (ty < 0 || ty >= N || tx < 0 || tx >= N) {
                    continue;
                }
                if (map[ty][tx] == 1 || isVisited[ty][tx]) {
                    continue;
                }
                q.add(new Loc(ty, tx, loc.fuel - 1));
                isVisited[ty][tx] = true;
            }
        }
        if (findPassenger != 0) {
            passengers.sort((o1, o2) -> {
                if (passenger[o1][0].y == passenger[o2][0].y) {
                    return passenger[o1][0].x - passenger[o2][0].x;
                }
                return passenger[o1][0].y - passenger[o2][0].y;
            });
            int res = passengers.get(0);
            taxi = new Loc(passenger[res][0].y, passenger[res][0].x, findPassenger);
            return res;
        }
        return -1;
    }

    static class Loc {
        int y, x, fuel;

        public Loc(int y, int x, int fuel) {
            this.y = y;
            this.x = x;
            this.fuel = fuel;
        }
    }
}