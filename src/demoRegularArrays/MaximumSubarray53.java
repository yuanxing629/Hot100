package demoRegularArrays;

public class MaximumSubarray53 {
    public int maxSubArray(int[] nums) {
        int pre = 0, res = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num); // pre记录的是以当前元素为结尾的连续子数组的最大和
            res = Math.max(pre, res);
        }
        return res;
    }
}
