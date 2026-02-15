package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q14263 {

    static char[][] grid;
    static ArrayList<ArrayList<Integer>> node = new ArrayList<>();
    static ArrayList<Character> use = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        grid = new char[H][W];
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                grid[i][j] = s.charAt(j);
                if (s.charAt(j) == '.') {
                    continue;
                }
                if (!use.contains(s.charAt(j))) {
                    use.add(s.charAt(j));
                }
            }
        }

        Collections.sort(use);
        for (int i = 0; i < use.size(); i++) {
            node.add(new ArrayList<>());
        }
        int[] inDegree = new int[use.size()];

        for (int i = 0; i < use.size(); i++) {
            char c = use.get(i);
            int maxY = 0, maxX = 0;
            int minY = H - 1, minX = W - 1;
            for (int j = 0; j < H; j++) {
                for (int k = 0; k < W; k++) {
                    if (grid[j][k] == c) {
                        maxY = Math.max(maxY, j);
                        maxX = Math.max(maxX, k);
                        minY = Math.min(minY, j);
                        minX = Math.min(minX, k);
                    }
                }
            }
            for (int j = minY; j <= maxY; j++) {
                for (int k = minX; k <= maxX; k++) {
                    if (grid[j][k] == '.') {
                        System.out.println(-1);
                        return;
                    }
                    if (grid[j][k] != c) {
                        if (node.get(i).contains(use.indexOf(grid[j][k]))) {
                            continue;
                        }
                        node.get(i).add(use.indexOf(grid[j][k]));
                        inDegree[use.indexOf(grid[j][k])]++;
                    }
                }
            }
        }
        ArrayList<Character> res = new ArrayList<>();
        Queue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < use.size(); i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }
        for (int i = 0; i < use.size(); i++) {
            if (q.isEmpty()) {
                System.out.println(-1);
                return;
            }
            int poll = q.poll();
            res.add(use.get(poll));
            for (int j : node.get(poll)) {
                inDegree[j]--;
                if (inDegree[j] == 0) {
                    q.add(j);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : res) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
