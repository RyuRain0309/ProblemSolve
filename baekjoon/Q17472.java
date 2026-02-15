package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q17472 {
    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int dist;

        Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return dist - o.dist;
        }
    }

    static int[] rangeX = {1, 0, -1, 0};
    static int[] rangeY = {0, 1, 0, -1};
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] isVisited;
    static int cnt = 1;
    static int[] parent;
    static int[][] dist;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    map[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }

        parent = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            parent[i] = i;
        }
        dist = new int[cnt][cnt];


        for (int[] ints : dist) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                getDist(i, j);
            }
        }

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 1; i < cnt; i++) {
            for (int j = i + 1; j < cnt; j++) {
                edges.add(new Edge(i, j, dist[i][j]));
            }
        }

        Collections.sort(edges);

//        for(Edge edge : edges){
//            System.out.println(edge.a + " " + edge.b + " " + edge.dist);
//        }
//        System.out.println();

        int edgeCnt = 0;
        int ans = 0;

        for (Edge edge : edges) {
            if (edgeCnt == cnt - 1) break;
            if (find(edge.a) == find(edge.b)) continue;
            if (edge.dist == Integer.MAX_VALUE) {
                System.out.print(-1);
                System.exit(0);
            }
            union(edge.a, edge.b);
            ans += edge.dist;
            edgeCnt++;
        }

        System.out.print(ans);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }


    private static int find(int num) {
        if (num == parent[num]) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }

    private static void getDist(int y, int x) {
        int src = map[y][x];
        for (int i = 0; i < 4; i++) {
            int moveY = y;
            int moveX = x;
            int distNow = 0;
            while (true) {
                moveY += rangeY[i];
                moveX += rangeX[i];
                if (moveX < 0 || moveY < 0 || moveX >= M || moveY >= N) break;
                int desVal = map[moveY][moveX];
                if (desVal == src) break;
                if (desVal != 0) {
                    if (distNow < 2) break;

                    if (src > desVal) {
                        dist[desVal][src] = Math.min(dist[desVal][src], distNow);
                    } else {
                        dist[src][desVal] = Math.min(dist[src][desVal], distNow);
                    }
                    break;
                }
                distNow++;
            }
        }
    }

    private static void dfs(int y, int x) {
        map[y][x] = cnt;
        for (int i = 0; i < 4; i++) {
            int newY = y + rangeY[i];
            int newX = x + rangeX[i];
            if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
                continue;
            }
            if (map[newY][newX] == -1) {
                dfs(newY, newX);
            }
        }
    }
}
