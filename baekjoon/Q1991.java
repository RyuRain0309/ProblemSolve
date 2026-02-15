package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1991 {

    static int[] inorder;
    static int[] postorder;
    static int[][] node;
    static ArrayList<Integer> preoder = new ArrayList<>();
    static int N, ctrl;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inorder = new int[N];
        postorder = new int[N];
        node = new int[N + 1][2];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }
        ctrl = N - 1;
        int rootPos = 0;
        for (int i = 0; i < N; i++) {
            if (inorder[i] == postorder[ctrl]) {
                rootPos = i;
            }
        }
        ctrl--;
        makeNode(rootPos, 0, N - 1);
        preoder(inorder[rootPos]);
        StringBuilder sb = new StringBuilder();
        for (int i : preoder) {
            sb.append(i).append(" ");
        }
        System.out.print(sb);
    }

    private static void preoder(int pos) {
        preoder.add(pos);
        if (node[pos][0] != 0) {
            preoder(node[pos][0]);
        }
        if (node[pos][1] != 0) {
            preoder(node[pos][1]);
        }
    }

    private static void makeNode(int pos, int lo, int hi) {
        //System.out.println(inorder[pos] + " a " + pos );

        if (ctrl < 0) return;
        int searchPos = pos;
        for (int i = pos + 1; i <= hi; i++) {
            if (inorder[i] == postorder[ctrl]) {
                searchPos = i;
            }
        }
        if (searchPos != pos) {
            ctrl--;
            node[inorder[pos]][1] = inorder[searchPos];
            makeNode(searchPos, pos + 1, hi);
        }

        if (ctrl < 0) return;
        searchPos = pos;
        for (int i = lo; i <= pos; i++) {
            if (inorder[i] == postorder[ctrl]) {
                searchPos = i;
            }
        }
        if (searchPos != pos) {
            ctrl--;
            node[inorder[pos]][0] = inorder[searchPos];
            makeNode(searchPos, lo, pos - 1);
        }
    }
}
