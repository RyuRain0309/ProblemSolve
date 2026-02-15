package baekjoon;

import java.io.*;

public class Q11653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for (int i = 2; i <= N; i++) {
            while (N % i == 0) {
                N /= i;
                bw.write(Integer.toString(i));
                bw.newLine();
            }
        }
        br.close();
        bw.close();
    }
}
