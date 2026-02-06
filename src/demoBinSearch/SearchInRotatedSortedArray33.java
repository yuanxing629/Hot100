package demoBinSearch;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class SearchInRotatedSortedArray33 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        int i = 0;
        while (i < n - 1) {
            if (nums[i] > nums[i + 1]) {
                // i 会停留在数组中的最大元素位置
                // 如果 k 为 0，即数组没有被旋转，i 会停在 n - 1
                break;
            }
            i++;
        }

        // 记录最大元素的位置
        int largest = i;
        // 如果数组没有被旋转过，直接进行二分查找
        if (i == n - 1) {
            return binarySearch(nums, target);
        }

        // original即原来的数组
        int[] temp = new int[n];
        int k = 0;
        while (i < n - 1) {
            temp[k++] = nums[++i];
        }
        i = 0;
        while (i <= largest) {
            temp[k++] = nums[i++];
        }

        // 在原数组上进行二分查找
        int res = binarySearch(temp, target);
        return res == -1 ? res : (res + largest + 1) % n;
    }

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
