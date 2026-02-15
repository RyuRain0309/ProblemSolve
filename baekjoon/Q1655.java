package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> bigQ = new PriorityQueue<>();
        PriorityQueue<Integer> smallQ = new PriorityQueue<>(Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();

        smallQ.offer(Integer.parseInt(br.readLine()));
        sb.append(smallQ.peek()).append("\n");
        for (int i = 0; i < N - 1; i++) {
            int temp = Integer.parseInt(br.readLine());
            if (smallQ.peek() < temp) {
                bigQ.add(temp);
            } else {
                smallQ.add(temp);
            }
            while ((smallQ.size() - i % 2) != bigQ.size()) {

                if (smallQ.size() > bigQ.size()) {
                    bigQ.offer(smallQ.poll());
                } else {
                    smallQ.add(bigQ.poll());
                }
            }
            sb.append(smallQ.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
