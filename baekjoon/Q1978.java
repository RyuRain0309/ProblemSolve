package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

public class Q1978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Vector<Integer> prime = getPrime(1000);
        int cnt = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            for (Integer integer : prime) {
                if (temp == integer) {
                    cnt++;
                    break;
                }
            }
        }
        bw.write(Integer.toString(cnt));
        br.close();
        bw.close();
    }

    private static Vector<Integer> getPrime(int n) {
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
