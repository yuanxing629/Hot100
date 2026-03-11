package demoDynamicProgramming;

import java.util.*;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class LongestIncreasingSubsequence300 {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        int res = 1;
        Arrays.fill(dp, 1);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) { // 注意 j < i
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]); // 取最长的子序列
        }
        return res;
    }

    // 法二：贪心+二分
    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 1) {
            return 1;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 如果找不到说明[1...len]的数都比 nums[i] 大，此时要更新 d[1]，因此把 pos 设为 0
                int left = 1, right = len, pos = 0;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }

                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}
