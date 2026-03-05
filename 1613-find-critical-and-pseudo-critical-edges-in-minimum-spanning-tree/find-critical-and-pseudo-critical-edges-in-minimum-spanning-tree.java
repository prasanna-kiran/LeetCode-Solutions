class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] e = new int[m][4];
        for (int i = 0; i < m; i++) {
            e[i][0] = edges[i][0];
            e[i][1] = edges[i][1];
            e[i][2] = edges[i][2];
            e[i][3] = i;
        }
        Arrays.sort(e, (a, b) -> a[2] - b[2]);
        int base = kruskal(n, e, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            if (kruskal(n, e, i, -1) > base) critical.add(e[i][3]);
            else if (kruskal(n, e, -1, i) == base) pseudo.add(e[i][3]);
        }
        return Arrays.asList(critical, pseudo);
    }

    private int kruskal(int n, int[][] e, int skip, int force) {
        UnionFind uf = new UnionFind(n);
        int weight = 0;
        if (force != -1) {
            uf.union(e[force][0], e[force][1]);
            weight += e[force][2];
        }
        for (int i = 0; i < e.length; i++) {
            if (i == skip) continue;
            if (uf.union(e[i][0], e[i][1])) weight += e[i][2];
        }
        return uf.count == 1 ? weight : Integer.MAX_VALUE;
    }

    static class UnionFind {
        int[] parent, rank;
        int count;
        UnionFind(int n) {
            parent = new int[n]; rank = new int[n]; count = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;
            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pb] < rank[pa]) parent[pb] = pa;
            else { parent[pb] = pa; rank[pa]++; }
            count--; return true;
        }
        int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
    }
}