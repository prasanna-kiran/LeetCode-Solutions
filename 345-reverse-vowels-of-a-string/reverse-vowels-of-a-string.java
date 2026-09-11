class Solution {
    public String reverseVowels(String s) {
         String vowels="aeiouAEIOU";
        char[]a=s.toCharArray();
       
        int left=0;
        int right=a.length-1;
        while(left<right){
            if(left<right && vowels.indexOf(a[left])==-1){
                left++;

            }else if(left<right && vowels.indexOf(a[right])==-1){
                right--;

            }else{
                char temp=a[left];
                a[left]=a[right];
                a[right]=temp;
                left++;
                right--;
            }
        }
        return new String(a);

        
    }
}