package demoSubstring;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK560 {

    public int subarraySum(int[] nums, int k) {
        if (nums.length == 1) return nums[0] == k ? 1 : 0;
        int count = 0;
        for (int begin = 0; begin < nums.length; ++begin) {
            int sum = 0;
            for (int end = begin; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        int count = 0, preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            preSum += num;
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
