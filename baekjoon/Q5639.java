package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q5639 {
    static ArrayList<Integer> pre = new ArrayList<>();
    static ArrayList<Integer> post = new ArrayList<>();
    static int[][] node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int MAX = Integer.MIN_VALUE;
        while (true) {
            input = br.readLine();
            if (input == null || input.equals("")) {
                break;
            }
            MAX = Math.max(MAX, Integer.parseInt(input));
            pre.add(Integer.parseInt(input));
        }
        node = new int[pre.size()][2];
        int hi = pre.size();
        for (int i = 1; i < pre.size(); i++) {
            if (pre.get(0) < pre.get(i)) {
                hi = i;
                break;
            }
        }
        if (hi > 1) {
            node[0][0] = 1;
            divide(1, hi);
        }
        if (hi != pre.size()) {
            node[0][1] = hi;
            divide(hi, pre.size());
        }
        postOrder(0);
        StringBuilder sb = new StringBuilder();
        for (int i : post) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
    }

    private static void postOrder(int order) {
        if (node[order][0] != 0) {
            postOrder(node[order][0]);
        }
        if (node[order][1] != 0) {
            postOrder(node[order][1]);
        }
        post.add(pre.get(order));
    }

    private static void divide(int lo, int hi) {
        int divPoint = hi;
        for (int i = lo; i < hi; i++) {
            if (pre.get(lo) < pre.get(i)) {
                divPoint = i;
                break;
            }
        }

        if (divPoint != lo + 1) { // 왼쪽 노드가 유무 확인
            node[lo][0] = lo + 1;
            divide(lo + 1, divPoint);
        }
        if (divPoint != hi) {  // 오른쪽 노드가 유무 확인
            node[lo][1] = divPoint;
            divide(divPoint, hi);
        }
    }
}
