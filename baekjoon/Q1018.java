package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Boolean[][] map = new Boolean[N][M];
        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = c[j] == 'W';
            }
        }
        int paintCnt = Integer.MAX_VALUE;
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                for (int wb = 0; wb < 2; wb++) {
                    Boolean[][] tempMap = new Boolean[8][8];
                    for (int k = 0; k < 8; k++) {
                        System.arraycopy(map[k + i], j, tempMap[k], 0, 8);
                    }
                    int tempPaintCnt = 0;
                    if (wb == 0) {
                        tempMap[0][0] = !tempMap[0][0];
                        tempPaintCnt++;

                    }
                    for (int y = 0; y < 8; y++) {
                        for (int x = 0; x < 8; x++) {
                            if (x == 0 && y == 0) {
                                continue;
                            } else if (x == 0) {
                                if (tempMap[y][x] == tempMap[y - 1][x]) {
                                    tempMap[y][x] = !tempMap[y][x];
                                    tempPaintCnt++;
                                }
                            } else if (y == 0) {
                                if (tempMap[y][x] == tempMap[y][x - 1]) {
                                    tempMap[y][x] = !tempMap[y][x];
                                    tempPaintCnt++;
                                }
                            } else {
                                if (tempMap[y][x] == tempMap[y][x - 1] || tempMap[y][x] == tempMap[y - 1][x]) {
                                    tempMap[y][x] = !tempMap[y][x];
                                    tempPaintCnt++;
                                }
                            }
                        }
                    }
                    if (tempPaintCnt < paintCnt) {
                        paintCnt = tempPaintCnt;
                    }
                }
            }
        }
        System.out.println(paintCnt);
        br.close();
    }
}
