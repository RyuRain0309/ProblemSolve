package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q10815 {
    static int[] arrN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arrN = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        while (N-- > 0) {
            arrN[N] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        int[] arrM = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            arrM[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrN);
        StringBuilder sb = new StringBuilder();
        for (Integer i : arrM) {
            if (binarySearch(i, 0, arrN.length - 1)) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }
        System.out.println(sb);
        br.close();
    }

    public static boolean binarySearch(int key, int low, int high) {
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (key == arrN[mid]) {
                return true;
            } else if (key < arrN[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }
}