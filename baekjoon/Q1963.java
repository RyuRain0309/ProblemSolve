package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1963 {

    static boolean[] prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        eratosthenesSieve(10_000);
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            boolean isNotFound = true;
            boolean[] isVisited = new boolean[10_000];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{start, 0});
            while (!q.isEmpty()) {
                int[] pw = q.poll();
                if (pw[0] == dest) {
                    sb.append(pw[1]).append("\n");
                    isNotFound = false;
                    break;
                }
                for (int i = 1; i <= 1000; i *= 10) {
                    int temp = pw[0];
                    for (int j = 0; j < 10; j++) {
                        temp += i;
                        if ((temp / i) % 10 == 0) {
                            temp -= i * 10;
                        }
                        if (temp < 1000 || temp >= 10000) {
                            continue;
                        }
                        if (isVisited[temp]) {
                            continue;
                        }
                        if (!prime[temp]) {
                            q.add(new int[]{temp, pw[1] + 1});
                            isVisited[temp] = true;
                        }
                    }
                }
            }
            if (isNotFound) {
                sb.append("Impossible").append("\n");
            }
        }
        System.out.println(sb);
    }

    static void eratosthenesSieve(int MAX) {
        prime = new boolean[MAX];
        for (int i = 2; i * i < MAX; i++) {
            if (prime[i]) continue;
            for (int j = i + i; j < MAX; j += i) {
                prime[j] = true;
            }
        }
    }
}
