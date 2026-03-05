class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;
        
        // If target is outside possible range or parity mismatch
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) return 0;
        
        int s = (sum + target) / 2;
        return subsetCount(nums, s);
    }

    private int subsetCount(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = s; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[s];
    }
}