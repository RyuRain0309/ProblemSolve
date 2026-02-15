package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int preSize = 0;
        int size = 3;
        int oCount = 1;

        while (N > size) {
            preSize = size;
            size = size * 2 + (oCount + 3);
            oCount++;
        }
        oCount--;

        while (true) {
            if (N > preSize) {
                N -= preSize;
                if (N <= oCount + 3) {
                    break;
                } else {
                    N -= oCount + 3;
                }
            }

            oCount--;
            preSize = (preSize - oCount - 3) / 2;

        }

        System.out.println(N == 1 ? "m" : "o");
    }
}
