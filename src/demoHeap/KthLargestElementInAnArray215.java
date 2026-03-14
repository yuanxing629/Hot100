package demoHeap;


import java.util.Arrays;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class KthLargestElementInAnArray215 {

    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            // 循环执行 k - 1 次，每次把当前对顶的最大值交换到末尾
            swap(nums, 0, i);
            // heapSize 减 1，相当于把刚才换到末尾的最大值从堆中“踢出”
            --heapSize;
            // 让剩下的元素重新排成大顶堆
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    // 初始化大顶堆，负责把最初乱序的数组变成大顶堆。
    private void buildMaxHeap(int[] a, int heapSize) {
        // 在完全二叉树中，索引大于 heapSize / 2 - 1的节点全是叶子节点。
        // 叶子节点没有子节点，天然就可看作是一个大小为 1 的合法堆。
        // 因此，建堆的过程是从最后一个非叶子节点开始，
        // 从下而上、从右往左地调用 maxHeapify，逐步调整为大顶堆
        for (int i = heapSize / 2 - 1; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    // 向下调整（堆化或下沉）
    private void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        // 如果最大的不是当前节点（largest != i），
        // 说明父节点压不住子节点了。此时将父节点和最大的子节点 swap 交换位置。
        // 交换后，被换下去的父节点可能在新的子树中依然“压不住”它的子节点，所以要继续向下递归调用 maxHeapify
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

class Quicksort {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickselect(nums, 0, n - 1, n - k);
    }

    private int quickselect(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[k];
        }
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do {
                i++;
            } while (nums[i] < x);
            do {
                j--;
            } while (nums[j] > x);

            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        if (k <= j) {
            return quickselect(nums, l, j, k);
        }
        return quickselect(nums, j + 1, r, k);
    }
}
