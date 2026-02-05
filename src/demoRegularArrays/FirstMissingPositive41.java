package demoRegularArrays;

import java.util.*;

public class FirstMissingPositive41 {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int num : nums) { // 所有正数加入set集合
            if (num > 0) {
                set.add(num);
            }
        }
        int i = 1;
        while (i <= set.size()) { // i从1开始，如果set中不含i，直接返回
            if (!set.contains(i)) {
                return i;
            }
            i++;
        }
        // while结束时
        //      set可能为空，说明nums中没有正数：直接返回1
        //      set不为空，说明缺少的第一个正整数刚好比nums中最大数大1
        return set.isEmpty() ? 1 : nums[nums.length - 1] + 1;
    }

    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            // int num = nums[i]; // 必须用上面这条，因为下面的if可能会把这个数边负
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public int firstMissingPositive3(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
