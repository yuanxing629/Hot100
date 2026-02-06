package demoBinSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */
public class FindFirstAndLastPositionOfElementInSortedArray34 {
    public int[] searchRange(int[] nums, int target) {
        // 先通过二分查找得到目标值的一个索引，再左右滑动指针
        // 从而找到最左和最右索引
        int index = binarySearch(nums, target);

        // 如果nums中不存在target，直接返回{-1,-1}
        if (index == -1) {
            return new int[]{-1, -1};
        }

        int left = index, right = index;
        while (left - 1 >= 0 && nums[left - 1] == target) {
            left--;
        }
        while (right + 1 < nums.length && nums[right + 1] == target) {
            right++;
        }

        return new int[]{left, right};
    }

    /**
     * 二分查找
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1; // 不存在返回-1
    }
}
