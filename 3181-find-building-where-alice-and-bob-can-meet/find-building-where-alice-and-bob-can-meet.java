class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int q = queries.length;
        int[] ans = new int[q];
        Arrays.fill(ans, -1);

        List<int[]>[] bucket = new ArrayList[n];
        for (int i = 0; i < n; i++) bucket[i] = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }

            if (a == b) ans[i] = b;
            else if (heights[a] < heights[b]) ans[i] = b;
            else bucket[b].add(new int[]{heights[a], i});
        }

        List<Integer> stack = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() && heights[stack.get(stack.size() - 1)] <= heights[i])
                stack.remove(stack.size() - 1);

            stack.add(i);

            for (int[] qy : bucket[i]) {
                int h = qy[0];
                int idx = qy[1];

                int l = 0, r = stack.size() - 1, res = -1;

                while (l <= r) {
                    int m = (l + r) / 2;
                    if (heights[stack.get(m)] > h) {
                        res = stack.get(m);
                        l = m + 1;
                    } else r = m - 1;
                }

                ans[idx] = res;
            }
        }

        return ans;
    }
}