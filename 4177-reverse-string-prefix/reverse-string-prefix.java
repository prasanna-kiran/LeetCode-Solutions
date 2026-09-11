class Solution {
    public String reversePrefix(String s, int k) {
        char[]a=s.toCharArray();
        int left=0;
        int right=k-1;
        while(left<right){
            char temp=a[left];
            a[left]=a[right];
            a[right]=temp;
            left++;
            right--;

        }
        return new String(a);
        


        
    }
}