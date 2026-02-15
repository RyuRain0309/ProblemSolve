package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17387 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Point a = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point b = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine(), " ");
        Point c = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point d = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        int ab = CCW(a, b, c) * CCW(a, b, d);
        int cd = CCW(c, d, a) * CCW(c, d, b);
        if (ab == 0 && cd == 0) {
            if (a.compareTo(b) > 0) {
                Point temp = b;
                b = a;
                a = temp;
            }
            if (c.compareTo(d) > 0) {
                Point temp = d;
                d = c;
                c = temp;
            }
            if (a.compareTo(d) <= 0 && c.compareTo(b) <= 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } else if (ab <= 0 && cd <= 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static int CCW(Point a, Point b, Point c) {
        long res = (a.x * b.y + b.x * c.y + c.x * a.y) - (b.x * a.y + c.x * b.y + a.x * c.y);
        if (res > 0) {
            return 1;
        }
        if (res < 0) {
            return -1;
        }
        return 0;
    }

    static class Point implements Comparable<Point> {
        long y, x;

        public Point(long y, long x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point o) {
            if (y == o.y) {
                return (int) (x - o.x);
            }
            return (int) (y - o.y);
        }
    }
}
