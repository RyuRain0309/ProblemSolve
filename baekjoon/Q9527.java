package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q9527 {

    static long[] D = new long[55];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        D[0] = 0;
        for (int i = 1; i <= 54; i++) {
            D[i] = (D[i - 1] << 1) + (1L << (i - 1));
        }

        System.out.println(f(B) - f(A - 1));

    }

    static long f(long x) {
        if (x <= 0) return 0;

        // x보다 작거나 같은 가장 큰 2의 거듭제곱 비트(가장 높은 자리의 1) 찾기
        int highBit = 0;
        long temp = x;
        while (temp > 1) {
            temp >>= 1;
            highBit++;
        }

        // 위에서 설명한 분할 정복 식을 그대로 리턴
        // D[highBit] + 맨 앞자리 1의 개수 + 나머지 꼬리 부분에 대한 재귀 호출
        return D[highBit] + (x - (1L << highBit) + 1) + f(x - (1L << highBit));
    }
}
