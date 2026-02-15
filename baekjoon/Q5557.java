package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N];
        for (int i = 0; i < N - 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int last = Integer.parseInt(st.nextToken());
        long[] res = new long[21];
        res[arr[0]] = 1;
        for (int i = 1; i < N - 1; i++) {
            long[] temp = new long[21];
            for (int j = 0; j < 21; j++) {
                if (res[j] != 0) {
                    if (j >= arr[i]) {
                        temp[j - arr[i]] += res[j];
                    }
                    if (j + arr[i] <= 20) {
                        temp[j + arr[i]] += res[j];
                    }
                }
            }
            res = temp.clone();

//            System.out.println(arr[i]);
//            for(long a : res){
//                System.out.print(a + " ");
//            }
//            System.out.println();
        }
        System.out.println(res[last]);
    }
}
