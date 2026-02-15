package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q3678 {

    static final int[] dy = {0, -1, -1, 0, 1, 1};
    static final int[] dx = {1, 1, 0, -1, -1, 0};

    static final int[] my = {-1, -1, 0, 1, 1, 0};
    static final int[] mx = {1, 0, -1, -1, 0, 1};
    static int[] mCnt = {0, 1, 1, 1, 1, 1};

    static int[][] map = new int[150][150];
    static int[] res = new int[10_001];

    static int[] resAmount = new int[6];
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 5; i++) {
            arr.add(i);
        }
        setMap();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(res[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

    private static void setMap() {
        int y = 75, x = 75;
        int cnt = 1;

        map[y][x] = getRes(y, x);
        res[cnt] = map[y][x];
        cnt++;

        while (true) {
            x++;
            map[y][x] = getRes(y, x);
            res[cnt] = map[y][x];
            cnt++;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < mCnt[i]; j++) {
                    y += my[i];
                    x += mx[i];
                    map[y][x] = getRes(y, x);
                    res[cnt] = map[y][x];
                    cnt++;
                    if (cnt > 10000) {
                        return;
                    }
                }
                mCnt[i]++;
            }
        }
    }

    private static int getRes(int y, int x) {
        arr.sort((o1, o2) -> {
            if (resAmount[o1] == resAmount[o2]) {
                return o1 - o2;
            }
            return resAmount[o1] - resAmount[o2];
        });

        for (Integer order : arr) {
            boolean flag = true;
            for (int i = 0; i < 6; i++) {
                int ty = dy[i] + y;
                int tx = dx[i] + x;
                if (order == map[ty][tx]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                resAmount[order]++;
                return order;
            }
        }
        return -1;
    }
}
