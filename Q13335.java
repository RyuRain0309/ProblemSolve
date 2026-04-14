import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q13335 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Queue<Integer> trucks = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks.offer(Integer.parseInt(st.nextToken()));
        }

        int time = 0;
        int weight = 0;
        Queue<Truck> onBridges = new ArrayDeque<>();
        while (!trucks.isEmpty()) {
            time++;

            if (!onBridges.isEmpty() && time - onBridges.peek().time == w) {
                Truck truck = onBridges.poll();
                weight -= truck.weight;
            }

            if (weight + trucks.peek() <= l) {
                int truck = trucks.poll();
                weight += truck;
                onBridges.offer(new Truck(truck, time));
            }
        }
        System.out.println(time + w);
    }

    private static class Truck {
        int weight;
        int time;

        Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }
}
