package demoGreedy;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 */
public class JumpGame55 {
    public boolean canJump(int[] nums) {
        int cover = 0; // cover 表示当前位置能覆盖的范围（最远能到哪里）
        if (nums.length == 1) { // 如果只有一个元素，那就是能到达
            return true;
        }
        for (int i = 0; i <= cover; i++) { // 注意这里是 <= cover
            cover = Math.max(cover, i + nums[i]);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
