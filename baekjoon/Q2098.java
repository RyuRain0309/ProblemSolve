package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2098 {
    static final int INF = 987654321;

    static int[][] W;
    static int[][] isVisited;
    static int visitedAll;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        isVisited = new int[N][1 << N];
        visitedAll = (1 << N) - 1;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            Arrays.fill(isVisited[i], -1);
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0, 1));
    }

    private static int dfs(int vertex, int bit) {
        if (bit == visitedAll) {
            if (W[vertex][0] == 0) return INF;
            return W[vertex][0];
        }

        if (isVisited[vertex][bit] != -1) {
            return isVisited[vertex][bit];
        }
        isVisited[vertex][bit] = INF;

        for (int i = 0; i < N; i++) {
            if (W[vertex][i] == 0) continue;
            if ((bit & (1 << i)) != 0) continue;
            isVisited[vertex][bit] = Math.min(isVisited[vertex][bit], dfs(i, bit | 1 << i) + W[vertex][i]);
        }
        return isVisited[vertex][bit];
    }
}
