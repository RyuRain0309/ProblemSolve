package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] sum = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                if (j == 0) {
                    sum[i][j] = Integer.parseInt(st.nextToken());
                    continue;
                }
                sum[i][j] = sum[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int res = 0;
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;
            for (int j = startY; j <= endY; j++) {
                if (startX == 0) {
                    res += sum[j][endX];
                } else {
                    res += sum[j][endX] - sum[j][startX - 1];
                }
            }
            sb.append(res).append("\n");
        }
        System.out.print(sb);
    }
}
