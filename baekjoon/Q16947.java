package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16947 {
    static ArrayList<ArrayList<Integer>> node = new ArrayList<>();
    static Stack<Integer> s = new Stack<>();
    static boolean[] isVisited, isLoop;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        isVisited = new boolean[N + 1];
        isLoop = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            node.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            node.get(to).add(from);
            node.get(from).add(to);
        }
        isVisited[1] = true;
        s.push(1);
        dfs(1, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (isLoop[i]) {
                sb.append("0 ");
                continue;
            }
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{i, 0, 0});
            while (!q.isEmpty()) {
                int[] pos = q.poll();
                if (isLoop[pos[0]]) {
                    sb.append(pos[2]).append(" ");
                    break;
                }
                for (Integer next : node.get(pos[0])) {
                    if (next == pos[1]) {
                        continue;
                    }
                    q.add(new int[]{next, pos[0], pos[2] + 1});
                }
            }
        }
        System.out.println(sb);
    }

    private static void dfs(int now, int pre) {
        for (Integer next : node.get(now)) {
            if (next == pre) {
                continue;
            }
            if (isVisited[next]) {
                while (!s.peek().equals(next)) {
                    isLoop[s.pop()] = true;
                }
                isLoop[s.pop()] = true;
                flag = true;
                return;
            }
            s.push(next);
            isVisited[next] = true;
            dfs(next, now);
            if (flag) {
                return;
            }
            s.pop();
        }
    }
}
