package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q17140 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        int[][] map = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = 0;
        while (res <= 100) {
            if (r < map.length && c < map[0].length && map[r][c] == k) {
                System.out.println(res);
                return;
            }
            ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
            int len = 0;
            if (map.length >= map[0].length) {
                for (int i = 0; i < map.length; i++) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int j = 0; j < map[0].length; j++) {
                        if (map[i][j] == 0) {
                            continue;
                        }
                        temp.add(map[i][j]);
                    }
                    arr.add(sort(temp));
                    len = Math.max(len, arr.get(arr.size() - 1).size());
                }
                len = Math.min(len, 100);
                map = new int[map.length][len];
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < len; j++) {
                        if (j < arr.get(i).size()) {
                            map[i][j] = arr.get(i).get(j);
                        } else {
                            map[i][j] = 0;
                        }
                    }
                }
            } else {
                for (int i = 0; i < map[0].length; i++) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int j = 0; j < map.length; j++) {
                        if (map[j][i] == 0) {
                            continue;
                        }
                        temp.add(map[j][i]);
                    }
                    arr.add(sort(temp));
                    len = Math.max(len, arr.get(arr.size() - 1).size());
                }
                len = Math.min(len, 100);
                map = new int[len][map[0].length];
                for (int i = 0; i < map[0].length; i++) {
                    for (int j = 0; j < len; j++) {
                        if (j < arr.get(i).size()) {
                            map[j][i] = arr.get(i).get(j);
                        } else {
                            map[j][i] = 0;
                        }
                    }
                }
            }
            res++;
        }
        System.out.println(-1);
    }

    private static ArrayList<Integer> sort(ArrayList<Integer> arr) {
        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        ArrayList<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> {
            if (Objects.equals(map.get(o1), map.get(o2))) {
                return o1 - o2;
            }
            return map.get(o1) - map.get(o2);
        });
        for (Integer i : keySet) {
            res.add(i);
            res.add(map.get(i));
        }
        return res;
    }
}
