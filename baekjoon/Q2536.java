package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2536 {
    static BusLine[] busLines;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        busLines = new BusLine[K + 1];
        boolean[] isVisited = new boolean[K + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int b = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            busLines[b] = new BusLine(new Point(x1, y1), new Point(x2, y2), 0);
        }
        st = new StringTokenizer(br.readLine(), " ");
        Point startPoint = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point endPoint = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Queue<BusLine> q = new LinkedList<>();
        for (int i = 1; i <= K; i++) {
            if (three(startPoint, busLines[i].startPoint, busLines[i].endPoint)) {
                q.add(busLines[i]);
            }
        }

        while (!q.isEmpty()) {
            BusLine line = q.poll();
            if (three(endPoint, line.startPoint, line.endPoint)) {
                System.out.println(line.cnt + 1);
                break;
            }
            for (int i = 1; i <= K; i++) {
                if (isVisited[i]) continue;

                if (four(line.startPoint, line.endPoint, busLines[i].startPoint, busLines[i].endPoint)) {
                    isVisited[i] = true;
                    q.add(new BusLine(busLines[i].startPoint, busLines[i].endPoint, line.cnt + 1));
                }
            }
        }
    }

    private static boolean four(Point a, Point b, Point c, Point d) {
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
            return a.compareTo(d) <= 0 && c.compareTo(b) <= 0;
        } else return ab <= 0 && cd <= 0;
    }

    private static boolean three(Point a, Point b, Point c) {
        if (a.x == b.x && a.x == c.x) {
            if (a.y <= b.y && a.y >= c.y) {
                return true;
            }
            if (a.y >= b.y && a.y <= c.y) {
                return true;
            }
        }
        if (a.y == b.y && a.y == c.y) {
            if (a.x <= b.x && a.x >= c.x) {
                return true;
            }
            if (a.x >= b.x && a.x <= c.x) {
                return true;
            }
        }
        return false;
    }

    private static int CCW(Point a, Point b, Point c) {
        int res = (a.x * b.y + b.x * c.y + c.x * a.y) - (b.x * a.y + c.x * b.y + a.x * c.y);
        return Integer.compare(res, 0);
    }

    static class BusLine {
        Point startPoint, endPoint;
        int cnt;

        public BusLine(Point startPoint, Point endPoint, int cnt) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.cnt = cnt;
        }
    }

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (y == o.y) {
                return x - o.x;
            }
            return y - o.y;
        }
    }
}
