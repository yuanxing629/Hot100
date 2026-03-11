package demoDynamicProgramming;

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class PartitionEqualSubsetSum416 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;

        // 不足2，不能分割
        if (n < 2) {
            return false;
        }

        int sum = 0, maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
            sum += num;
        }

        // 总和为奇数
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;
        // 最大数超过了总和的一半
        if (maxNum > target) {
            return false;
        }

        // dp[i][j]: 从nums[0...i]选若干个正整数(可以是0个)，它们的总和是否可以恰等于 j
        boolean[][] dp = new boolean[n][target + 1];
        // j==0时，dp一定为true：从xxx中选取0个数，总和一定可以为0
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        // i==0时，只有dp[0][nums[0]]为true：从nums[0]中选数，它的总和只会是0或者nums[0]
        dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) { // j 不小于当前元素：可选取也可不选取，只要有一种为 true 即可
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                } else { // j 比当前元素小：说明选取当前元素的话，数字和一定会超过 j，所以不能选取
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // 表示从整个数组中，选取若干个正整数，是否可以使得总和为 target
        return dp[n - 1][target];
    }
}
