package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q10431 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            sb.append(st.nextToken()).append(" ");
            int cnt = 0;
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                int height = Integer.parseInt(st.nextToken());
                int index = arr.size();
                for (int k = 0; k < arr.size(); k++) {
                    if (arr.get(k) > height) {
                        index = k;
                        break;
                    }
                }
                cnt += arr.size() - index;
                arr.add(index, height);
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}