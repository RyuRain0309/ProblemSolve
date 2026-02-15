package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class Q2581 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        Vector<Integer> prime = getPrime(10000);
        int least = -1, sum = 0;
        for (Integer integer : prime) {
            if (integer >= M && integer <= N) {
                if (least == -1) {
                    least = integer;
                }
                sum += integer;
            }
        }
        if (sum != 0) {
            bw.write(Integer.toString(sum));
            bw.newLine();
        }
        bw.write(Integer.toString(least));
        br.close();
        bw.close();
    }

    public static Vector<Integer> getPrime(int n) {
        Vector<Integer> v = new Vector<>();
        Boolean[] is_Prime = new Boolean[n + 1];
        Arrays.fill(is_Prime, true);
        is_Prime[1] = false;
        for (int i = 3; i < Math.sqrt(n + 1); i += 2) {
            if (is_Prime[i]) {
                for (int j = i + i; j < n + 1; j += i) {
                    is_Prime[j] = false;
                }
            }
        }
        v.add(2);
        for (int i = 3; i < n + 1; i += 2) {
            if (is_Prime[i]) {
                v.add(i);
            }
        }
        return v;
    }
}
