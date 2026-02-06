package demoBinSearch;

public class FindMinimumInRotatedSortedArray153 {
    public int findMin(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n - 1) {
            if (nums[i] > nums[i + 1]) {
                break;
            }
            i++;
        }

        return i == n - 1 ? nums[0] : nums[i + 1];
    }

    public int findMin2(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }
}
