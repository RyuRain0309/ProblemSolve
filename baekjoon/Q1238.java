package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제
 * N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
 * 어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.
 * 각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.
 * 이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.
 * <p>
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다. 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다. 시작점과 끝점이 같은 도로는 없으며, 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
 * 모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.
 */
public class Q1238 {

    static ArrayList<Node>[] list;
    static ArrayList<Node>[] reverseList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        reverseList = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
            reverseList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
            reverseList[b].add(new Node(a, c));
        }

        int[] goX = dijkstra(N, X, list);
        int[] goHome = dijkstra(N, X, reverseList);
        int result = 0;
        for (int i = 0; i <= N; i++) {
            result = Math.max(result, goHome[i] + goX[i]);
        }

        System.out.println(result);
    }

    static int[] dijkstra(int n, int start, ArrayList<Node>[] load) {
        int[] isVisited = new int[n + 1];
        Arrays.fill(isVisited, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        isVisited[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (Node next : load[cur.dest]) {
                int cost = cur.cost + next.cost;
                if (cost >= isVisited[next.dest]) continue;
                isVisited[next.dest] = cost;
                pq.offer(new Node(next.dest, cost));
            }
        }
        return isVisited;
    }


    static class Node implements Comparable<Node> {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}

