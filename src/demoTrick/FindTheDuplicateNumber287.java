package demoTrick;

/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 */
public class FindTheDuplicateNumber287 {
    public int findDuplicate(int[] nums) {
        int[] count = new int[nums.length]; // 长度为 n+1，count[i] 记录的是 i 在 nums 中出现的次数
        for (int num : nums) {
            count[num]++;
        }
        int i = 1;
        for (; i < count.length; i++) {
            if (count[i] > 1) {
                break;
            }
        }
        return i;
    }

    // 法二：二进制
    public int findDuplicate2(int[] nums) {
        // 题目：数组中最大的数不会超过 length - 1
        int n = nums.length, ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                // 数组中二进制展开后第 i 位为 1 的数有 x 个
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                // 数字 [1, n] 这 n 个数二进制展开后第 i 位为 1 的数有 y 个
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    // 法三：快慢指针
    public int findDuplicate3(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
