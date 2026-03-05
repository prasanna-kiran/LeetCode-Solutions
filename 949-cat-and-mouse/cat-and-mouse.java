import java.util.*;

class Solution {
    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        int[][][] color = new int[n][n][2];
        int[][][] degree = new int[n][n][2];

        for (int m = 0; m < n; m++) {
            for (int c = 0; c < n; c++) {
                degree[m][c][0] = graph[m].length;
                degree[m][c][1] = graph[c].length;
                for (int x : graph[c]) if (x == 0) degree[m][c][1]--;
            }
        }

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int t = 0; t < 2; t++) {
                if (i == 0) continue;

                color[0][i][t] = 1;
                q.offer(new int[]{0, i, t, 1});

                color[i][i][t] = 2;
                q.offer(new int[]{i, i, t, 2});
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int m = cur[0], c = cur[1], turn = cur[2], result = cur[3];

            for (int[] prev : parents(graph, m, c, turn)) {
                int pm = prev[0], pc = prev[1], pt = prev[2];

                if (color[pm][pc][pt] != 0) continue;

                if ((pt == 0 && result == 1) || (pt == 1 && result == 2)) {
                    color[pm][pc][pt] = result;
                    q.offer(new int[]{pm, pc, pt, result});
                } else {
                    degree[pm][pc][pt]--;
                    if (degree[pm][pc][pt] == 0) {
                        int lose = pt == 0 ? 2 : 1;
                        color[pm][pc][pt] = lose;
                        q.offer(new int[]{pm, pc, pt, lose});
                    }
                }
            }
        }

        return color[1][2][0];
    }

    private List<int[]> parents(int[][] graph, int m, int c, int turn) {
        List<int[]> res = new ArrayList<>();

        if (turn == 0) {
            for (int pc : graph[c]) {
                if (pc == 0) continue;
                res.add(new int[]{m, pc, 1});
            }
        } else {
            for (int pm : graph[m]) {
                res.add(new int[]{pm, c, 0});
            }
        }

        return res;
    }
}