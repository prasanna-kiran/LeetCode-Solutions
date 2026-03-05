class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long MOD = 1000000007;
        long maxKadane = kadane(arr);
        if (k == 1) return (int)(maxKadane % MOD);

        long prefix = maxPrefix(arr);
        long suffix = maxSuffix(arr);
        long totalSum = 0;
        for (int num : arr) totalSum += num;

        long result;
        if (totalSum > 0) {
            result = Math.max(maxKadane, prefix + suffix + (k - 2) * totalSum);
        } else {
            result = Math.max(maxKadane, prefix + suffix);
        }
        return (int)(result % MOD);
    }

    private long kadane(int[] arr) {
        long max = 0, cur = 0;
        for (int num : arr) {
            cur = Math.max(num, cur + num);
            max = Math.max(max, cur);
        }
        return max;
    }

    private long maxPrefix(int[] arr) {
        long sum = 0, max = 0;
        for (int num : arr) {
            sum += num;
            max = Math.max(max, sum);
        }
        return max;
    }

    private long maxSuffix(int[] arr) {
        long sum = 0, max = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            sum += arr[i];
            max = Math.max(max, sum);
        }
        return max;
    }
}