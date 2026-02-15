package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20055 {

    static int[] belt;
    static boolean[] robot;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2 * N];
        robot = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        int res = 1;
        while (true) {
            spin();
            moveRobot();
            if (belt[0] != 0) {
                belt[0] -= 1;
                robot[0] = true;
            }
            if (durabilityCheck()) {
                break;
            }
            res++;
        }
        System.out.print(res);
    }

    private static boolean durabilityCheck() {
        int cnt = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (belt[i] == 0) {
                cnt++;
            }
        }
        return cnt >= K;
    }

    private static void moveRobot() {
        robot[N - 1] = false;
        for (int i = N - 2; i >= 0; i--) {
            if (!robot[i]) {
                continue;
            }
            if (belt[i + 1] == 0 || robot[i + 1]) {
                continue;
            }
            belt[i + 1] -= 1;
            robot[i + 1] = true;
            robot[i] = false;
        }
    }

    private static void spin() {
        int temp = belt[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }
        belt[0] = temp;

        for (int i = N - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false;
    }
}
