package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class Q4948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Vector<Integer> prime = getPrime(123456 * 2);
        while (true) {
            int testcase = Integer.parseInt(br.readLine());
            if (testcase == 0) {
                break;
            }
            int cnt = 0;
            for (Integer integer : prime) {
                if (integer > testcase && integer <= testcase * 2) {
                    cnt++;
                }
            }
            bw.write(Integer.toString(cnt));
            bw.newLine();
        }
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
