package demoGreedy;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
 * 0 <= j <= nums[i] 且
 * i + j < n
 * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
 */
public class JumpGameII45 {
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int curDistance = 0; // 当前覆盖最远距离下标
        int ans = 0;
        int nextDistance = 0; // 下一步覆盖最远距离下标
        for (int i = 0; i < nums.length; i++) {
            nextDistance = Math.max(nextDistance, nums[i] + i); // 更新下一步覆盖最远距离下标
            if (i == curDistance) { // 遇到当前覆盖最远距离下标
                ans++; // 需要走下一步
                curDistance = nextDistance; // 更新当前覆盖最远距离下标（加油）
                if (nextDistance >= nums.length - 1) { // 当前覆盖最远距离可达终点，提前结束
                    break;
                }
            }

        }
        return ans;
    }
}
