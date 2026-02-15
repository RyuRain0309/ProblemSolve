package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String init = br.readLine();
        StringBuilder sb = new StringBuilder(init);
        int cursor = init.length();

        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            switch (order) {
                case "L":
                    cursor = Math.max(0, cursor - 1);
                    break;
                case "D":
                    cursor = Math.min(sb.length(), cursor + 1);
                    break;
                case "B":
                    if (cursor != 0) {
                        sb.delete(cursor - 1, cursor);
                        cursor--;
                    }
                    break;
                case "P":
                    sb.insert(cursor, st.nextToken());
                    cursor++;
                    break;
            }
        }
        System.out.println(sb);
    }
}
