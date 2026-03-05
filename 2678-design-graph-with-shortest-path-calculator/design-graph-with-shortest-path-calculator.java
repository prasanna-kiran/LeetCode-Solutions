import java.util.*;
class Graph {
    private List<List<int[]>> adj;
    public Graph(int n,int[][] edges){
        adj=new ArrayList<>();
        for(int i=0;i<n;i++)adj.add(new ArrayList<>());
        for(int[] e:edges)adj.get(e[0]).add(new int[]{e[1],e[2]});
    }
    public void addEdge(int[] edge){
        adj.get(edge[0]).add(new int[]{edge[1],edge[2]});
    }
    public int shortestPath(int node1,int node2){
        int n=adj.size();
        long[] dist=new long[n];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[node1]=0;
        PriorityQueue<long[]> pq=new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        pq.offer(new long[]{node1,0});
        while(!pq.isEmpty()){
            long[] cur=pq.poll();
            int u=(int)cur[0];
            long d=cur[1];
            if(d>dist[u])continue;
            for(int[] nei:adj.get(u)){
                int v=nei[0],w=nei[1];
                if(dist[v]>d+w){
                    dist[v]=d+w;
                    pq.offer(new long[]{v,dist[v]});
                }
            }
        }
        return dist[node2]==Long.MAX_VALUE?-1:(int)dist[node2];
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */