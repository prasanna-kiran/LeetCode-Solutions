class Solution {
    public int findTheCity(int n,int[][] edges,int distanceThreshold){
        int[][] dist=new int[n][n];
        for(int i=0;i<n;i++)Arrays.fill(dist[i],1000000000);
        for(int i=0;i<n;i++)dist[i][i]=0;
        for(int[] e:edges){
            dist[e[0]][e[1]]=e[2];
            dist[e[1]][e[0]]=e[2];
        }
        for(int k=0;k<n;k++)
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    if(dist[i][j]>dist[i][k]+dist[k][j])
                        dist[i][j]=dist[i][k]+dist[k][j];
        int ans=-1,minReach=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int count=0;
            for(int j=0;j<n;j++)if(i!=j&&dist[i][j]<=distanceThreshold)count++;
            if(count<=minReach){minReach=count;ans=i;}
        }
        return ans;
    }
}