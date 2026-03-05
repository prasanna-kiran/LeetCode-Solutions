import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToBuses.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visitedBuses = new HashSet<>();
        Set<Integer> visitedStops = new HashSet<>();
        q.add(source);
        visitedStops.add(source);

        int buses = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            buses++;
            for (int i = 0; i < size; i++) {
                int stop = q.poll();
                for (int bus : stopToBuses.getOrDefault(stop, new ArrayList<>())) {
                    if (visitedBuses.contains(bus)) continue;
                    visitedBuses.add(bus);
                    for (int nextStop : routes[bus]) {
                        if (nextStop == target) return buses;
                        if (!visitedStops.contains(nextStop)) {
                            visitedStops.add(nextStop);
                            q.add(nextStop);
                        }
                    }
                }
            }
        }
        return -1;
    }
}