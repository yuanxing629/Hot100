package demoRegularArrays;

public class RotateArray189 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if (n == 1 || k == 0) {
            return;
        }
        swap(nums, 0, n - k - 1);
        swap(nums, n - k, n - 1);
        swap(nums, 0, n - 1);
    }

    // 翻转数组[left,right]中的元素
    void swap(int[] arr, int left, int right) {
        int i = left, j = right;
        while (i < j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[(i + k) % n] = nums[i];
        }
        System.arraycopy(arr, 0, nums, 0, n);
    }
}
