package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2618 {

    static int N;
    static int W;
    static int[][] dp;
    static int[][] eventPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        dp = new int[W + 2][W + 2];
        eventPos = new int[W + 2][2];

        StringTokenizer st;

        eventPos[0][0] = 1;
        eventPos[0][1] = 1;
        eventPos[1][0] = N;
        eventPos[1][1] = N;
        for (int i = 2; i < W + 2; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            eventPos[i][0] = Integer.parseInt(st.nextToken());
            eventPos[i][1] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dfs(2, 0, 1)).append("\n");

        int aCar = 0;
        int bCar = 1;
        for (int i = 2; i < W + 2; i++) {

            if (dp[aCar][bCar] == dp[i][bCar] + getDistacne(aCar, i)) {
                aCar = i;
                sb.append(1).append("\n");
            } else {
                bCar = i;
                sb.append(2).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int dfs(int e, int aCar, int bCar) {
        if (e > W + 1) {
            return 0;
        }

        if (dp[aCar][bCar] != 0) {
            return dp[aCar][bCar];
        }

        int oneDispatch = dfs(e + 1, e, bCar) + getDistacne(aCar, e);
        int twoDispatch = dfs(e + 1, aCar, e) + getDistacne(bCar, e);

        //System.out.println(e + " " + aCar + " " + bCar + " " + oneDispatch + " " + twoDispatch + " " + getDistacne(aCar,e) + " " + getDistacne(bCar,e));

        return dp[aCar][bCar] = Math.min(oneDispatch, twoDispatch);
    }

    private static int getDistacne(int pos1, int pos2) {
        return Math.abs(eventPos[pos1][0] - eventPos[pos2][0]) + Math.abs(eventPos[pos1][1] - eventPos[pos2][1]);
    }
}
