package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2666 {
    static int[] arr;
    static int T;
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] door = new int[2];
        door[0] = Integer.parseInt(st.nextToken());
        door[1] = Integer.parseInt(st.nextToken());

        T = Integer.parseInt(br.readLine());
        arr = new int[T];
        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dfs(0, door[0], door[1], 0);
        System.out.println(res);
    }

    private static void dfs(int depth, int d1, int d2, int cnt) {
        if (depth >= T) {
            res = Math.min(res, cnt);
            return;
        }
        dfs(depth + 1, arr[depth], d2, cnt + Math.abs(arr[depth] - d1));
        dfs(depth + 1, d1, arr[depth], cnt + Math.abs(arr[depth] - d2));
    }
}
