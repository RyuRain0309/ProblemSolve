package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q11657 {
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<ArrayList<Pair>> node = new ArrayList<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            node.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            node.get(f).add(new Pair(t, w));
        }

        long[] res = new long[N + 1];
        Arrays.fill(res, MAX);
        res[1] = 0;
        boolean isUpdate = false;

        for (int i = 1; i < N; i++) {
            isUpdate = false;

            for (int j = 1; j <= N; j++) {
                for (Pair pair : node.get(j)) {
                    if (res[j] == MAX) {
                        break;
                    }

                    if (res[pair.vertex] > res[j] + pair.weight) {
                        res[pair.vertex] = res[j] + pair.weight;
                        isUpdate = true;
                    }
                }
            }

            if (!isUpdate) {
                break;
            }
        }

        if (isUpdate) {
            for (int j = 1; j <= N; j++) {
                for (Pair pair : node.get(j)) {
                    if (res[j] == Integer.MAX_VALUE) {
                        break;
                    }

                    if (res[pair.vertex] > res[j] + pair.weight) {
                        System.out.print(-1);
                        System.exit(0);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(res[i] == MAX ? -1 : res[i]).append("\n");
        }
        System.out.print(sb);
    }

    private static class Pair {
        int vertex;
        int weight;

        Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
