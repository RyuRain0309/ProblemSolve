package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q16235 {
    static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int N, M, K;
    static int[][] S2D2;
    static int[][] map;

    static ArrayList<Tree> liveTree = new ArrayList<>();
    static ArrayList<Tree> deadTree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S2D2 = new int[N + 1][N + 1];
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                S2D2[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int old = Integer.parseInt(st.nextToken());
            liveTree.add(new Tree(y, x, old));
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(liveTree.size());
    }

    private static void spring() {
        ArrayList<Tree> temp = new ArrayList<>();
        Collections.sort(liveTree);
        for (Tree tree : liveTree) {
            int y = tree.y;
            int x = tree.x;
            if (map[y][x] < tree.old) {
                deadTree.add(tree);
                continue;
            }
            map[y][x] -= tree.old;
            temp.add(new Tree(y, x, tree.old + 1));
        }
        liveTree = temp;
    }

    private static void summer() {
        for (Tree tree : deadTree) {
            int y = tree.y;
            int x = tree.x;
            int nutrition = tree.old / 2;
            map[y][x] += nutrition;
        }
        deadTree.clear();
    }

    private static void autumn() {
        ArrayList<Tree> temp = new ArrayList<>();
        for (Tree tree : liveTree) {
            if (tree.old % 5 != 0) {
                continue;
            }
            for (int i = 0; i < 8; i++) {
                int ty = dy[i] + tree.y;
                int tx = dx[i] + tree.x;
                if (ty <= 0 || ty > N || tx <= 0 || tx > N) {
                    continue;
                }
                temp.add(new Tree(ty, tx, 1));
            }
        }
        liveTree.addAll(temp);
    }

    private static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += S2D2[i][j];
            }
        }
    }

    private static class Tree implements Comparable<Tree> {
        int y, x, old;

        public Tree(int y, int x, int old) {
            this.y = y;
            this.x = x;
            this.old = old;
        }

        @Override
        public int compareTo(Tree t) {
            return this.old - t.old;
        }
    }
}
