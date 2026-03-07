package demoTrick;

/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 */
public class SortColors75 {

    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int num : nums) {
            if (num == 0) {
                count[0]++;
            } else if (num == 1) {
                count[1]++;
            } else {
                count[2]++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < count[0]) {
                nums[i] = 0;
            } else if (i < count[0] + count[1]) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int p0 = 0; // p0: 下一个 0 的存放位置
        int p2 = nums.length - 1; // p2: 下一个 2 的存放位置
        for (int i = 0; i <= p2; i++) {
            // 核心：若当前是 2 ，不断与 p2 交换并左移 p2，直到当前为止不再是 2
            while (i <= p2 && nums[i] == 2) {
                swap(nums, i, p2);
                p2--;
            }
            // 此时 nums[i] 只能是 0 或 1。若是 0 ，与 p0 交换并右移 p0
            if (nums[i] == 0) {
                swap(nums, i, p0);
                p0++;
            }
            // 若是 1，for 循环自动 i++ ，无需操作。

        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
