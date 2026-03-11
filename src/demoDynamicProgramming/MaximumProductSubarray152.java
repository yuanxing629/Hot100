package demoDynamicProgramming;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
 */
public class MaximumProductSubarray152 {
    public int maxProduct(int[] nums) {
        // 记录以当前元素结尾的连续子数组的最大乘积和最小乘积
        long maxF = nums[0], minF = nums[0];
        // 全局最大乘积
        int ans = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; ++i) {
            // 暂存上一步的状态，非常关键。
            // 因为计算当前maxF时会把原来的maxF覆盖掉，但接着计算minF时仍要用到上一轮的maxF
            long mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            /*防止溢出。-2^31 即 Integer.MIN_VALUE
            如果minF越界，说明这个极小的负数对后续寻找合法答案已无帮助，甚至可能导致long也溢出
            所以直接重置为nums[i]进行截断*/
            if (minF < -1 << 31) {
                minF = nums[i];
            }
            ans = Math.max((int) maxF, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        long m = -1 << 31;
        System.out.println(m==Integer.MIN_VALUE);
    }
}
