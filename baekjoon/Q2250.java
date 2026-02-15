package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2250 {
    static int[][] node;
    static int[] depthFirst;
    static int[] res = new int[2];
    static int N, width = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        node = new int[N + 1][2];
        depthFirst = new int[N + 1];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            node[v][0] = Integer.parseInt(st.nextToken());
            node[v][1] = Integer.parseInt(st.nextToken());
        }
        int root = getRoot();
        res[0] = Integer.MAX_VALUE;
        dfs(1, root);
        System.out.println(res[0] + " " + res[1]);
    }

    private static void dfs(int depth, int v) {
        if (node[v][0] != -1) {
            dfs(depth + 1, node[v][0]);
        }

        if (depthFirst[depth] == 0) {
            depthFirst[depth] = width;
        }
        int w = width - depthFirst[depth] + 1;
        if (res[1] < w) {
            res[1] = w;
            res[0] = depth;
        }
        if (res[1] == w) {
            res[0] = Math.min(res[0], depth);
        }
        width++;

        if (node[v][1] != -1) {
            dfs(depth + 1, node[v][1]);
        }
    }

    private static int getRoot() {
        boolean[] isVisited = new boolean[N + 1];
        Stack<Integer> s = new Stack<>();
        for (int i = 1; i <= N; i++) {
            Arrays.fill(isVisited, false);
            s.push(i);
            while (!s.isEmpty()) {
                int pop = s.pop();
                isVisited[pop] = true;
                if (node[pop][1] != -1) {
                    s.push(node[pop][1]);
                }
                if (node[pop][0] != -1) {
                    s.push(node[pop][0]);
                }
            }

            boolean flag = true;
            for (int j = 1; j <= N; j++) {
                if (!isVisited[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }

        }
        return -1;
    }
}
