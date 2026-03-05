import java.util.*;
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer,List<int[]>> graph=new HashMap<>();
        for(int[] e:times) graph.computeIfAbsent(e[0],x->new ArrayList<>()).add(new int[]{e[1],e[2]});
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{k,0});
        Map<Integer,Integer> dist=new HashMap<>();
        while(!pq.isEmpty()){
            int[] cur=pq.poll();
            int node=cur[0],time=cur[1];
            if(dist.containsKey(node)) continue;
            dist.put(node,time);
            if(!graph.containsKey(node)) continue;
            for(int[] nei:graph.get(node)){
                int next=nei[0],newTime=time+nei[1];
                if(!dist.containsKey(next)) pq.offer(new int[]{next,newTime});
            }
        }
        if(dist.size()!=n) return -1;
        int max=0;
        for(int t:dist.values()) max=Math.max(max,t);
        return max;
    }
}