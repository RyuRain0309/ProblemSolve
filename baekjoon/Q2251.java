package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2251 {

    static int[] capacity = new int[3];
    static boolean[][][] isVisited;
    static ArrayList<Integer> res = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        capacity[0] = Integer.parseInt(st.nextToken());
        capacity[1] = Integer.parseInt(st.nextToken());
        capacity[2] = Integer.parseInt(st.nextToken());
        isVisited = new boolean[capacity[0] + 1][capacity[1] + 1][capacity[2] + 1];

        Queue<int[]> q = new LinkedList<>();
        isVisited[capacity[0]][capacity[1]][capacity[2]] = true;
        q.add(new int[]{0, 0, capacity[2]});
        res.add(capacity[2]);

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int i = 0; i < 3; i++) {
                if (pos[i] == 0) {
                    continue;
                }
                for (int j = 0; j < 3; j++) {
                    int[] temp = Arrays.copyOf(pos, 3);
                    if (i == j) {
                        continue;
                    }
                    temp[j] += temp[i];
                    temp[i] = 0;
                    if (temp[j] > capacity[j]) {
                        temp[i] = temp[j] - capacity[j];
                        temp[j] = capacity[j];
                    }

                    if (isVisited[temp[0]][temp[1]][temp[2]]) {
                        continue;
                    }
                    isVisited[temp[0]][temp[1]][temp[2]] = true;
                    q.add(new int[]{temp[0], temp[1], temp[2]});
                    if (temp[0] == 0 && !res.contains(temp[2])) {
                        res.add(temp[2]);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(res);
        for (Integer i : res) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
