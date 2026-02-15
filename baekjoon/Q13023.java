package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q13023 {
    static ArrayList<ArrayList<Integer>> friend;
    static boolean[] isVisited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isVisited = new boolean[N];
        friend = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            friend.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            friend.get(to).add(from);
            friend.get(from).add(to);
        }
        for (int i = 0; i < N; i++) {
            isVisited[i] = true;
            dfs(0, i);
            isVisited[i] = false;
        }
        System.out.println(0);
    }

    private static void dfs(int depth, int index) {
        if (depth == 4) {
            System.out.println(1);
            System.exit(0);
        }

        for (int i : friend.get(index)) {
            if (isVisited[i]) continue;
            isVisited[i] = true;
            dfs(depth + 1, i);
            isVisited[i] = false;
        }
    }
}
