import java.util.*;
class Solution {
    public long minimumWeight(int n,int[][] edges,int src1,int src2,int dest){
        List<List<int[]>> graph=new ArrayList<>();
        List<List<int[]>> revGraph=new ArrayList<>();
        for(int i=0;i<n;i++){graph.add(new ArrayList<>());revGraph.add(new ArrayList<>());}
        for(int[] e:edges){graph.get(e[0]).add(new int[]{e[1],e[2]});revGraph.get(e[1]).add(new int[]{e[0],e[2]});}
        long[] dist1=dijkstra(n,graph,src1);
        long[] dist2=dijkstra(n,graph,src2);
        long[] distDest=dijkstra(n,revGraph,dest);
        long ans=Long.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(dist1[i]==Long.MAX_VALUE||dist2[i]==Long.MAX_VALUE||distDest[i]==Long.MAX_VALUE)continue;
            ans=Math.min(ans,dist1[i]+dist2[i]+distDest[i]);
        }
        return ans==Long.MAX_VALUE?-1:ans;
    }
    private long[] dijkstra(int n,List<List<int[]>> graph,int src){
        long[] dist=new long[n];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[src]=0;
        PriorityQueue<long[]> pq=new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        pq.offer(new long[]{src,0});
        while(!pq.isEmpty()){
            long[] cur=pq.poll();
            int u=(int)cur[0];
            long d=cur[1];
            if(d>dist[u])continue;
            for(int[] nei:graph.get(u)){
                int v=nei[0],w=nei[1];
                if(dist[v]>d+w){
                    dist[v]=d+w;
                    pq.offer(new long[]{v,dist[v]});
                }
            }
        }
        return dist;
    }
}