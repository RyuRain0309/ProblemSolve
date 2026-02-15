package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Jewelry> jewelries = new PriorityQueue<>();
        int[] bag = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            jewelries.offer(new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        long res = 0;
        Arrays.sort(bag);
        Queue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (int bagSize : bag) {
            while (!jewelries.isEmpty() && jewelries.peek().weight <= bagSize) {
                q.offer(jewelries.poll().value);
            }
            if (!q.isEmpty()) {
                res += q.poll();
            }
        }
        System.out.println(res);
    }


    static class Jewelry implements Comparable<Jewelry> {
        int weight, value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            if (this.weight == o.weight) {
                return o.value - this.value;
            }
            return this.weight - o.weight;
        }
    }
}
