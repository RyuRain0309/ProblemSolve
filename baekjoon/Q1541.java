package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
        ArrayList<Integer> arr = new ArrayList<>();
        int end = st.countTokens();
        for (int i = 0; i < end; i++) {
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), "+");
            int tempEnd = st2.countTokens();
            int temp = 0;
            for (int j = 0; j < tempEnd; j++) {
                temp += Integer.parseInt(st2.nextToken());
            }
            arr.add(temp);
        }
        int res = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            res -= arr.get(i);
        }
        System.out.print(res);
    }
}
