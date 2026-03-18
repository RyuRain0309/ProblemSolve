package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10775 {

    static int[] uf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        uf = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            uf[i] = i;
        }
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());
            int findG = find(g);
            if (findG == 0) {
                System.out.println(i);
                System.exit(0);
            }
            union(findG);
        }
        System.out.println(P);
    }

    private static void union(int a) {
        uf[a] = find(a - 1);
    }

    private static int find(int a) {
        if (uf[a] == a) {
            return a;
        }
        return uf[a] = find(uf[a]);
    }
}
