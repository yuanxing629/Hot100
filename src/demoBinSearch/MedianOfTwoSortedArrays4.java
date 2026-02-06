package demoBinSearch;

public class MedianOfTwoSortedArrays4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            // 交换两数组，使得下面的 i 可以从 0 开始枚举
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int[] a = new int[m + 2];
        int[] b = new int[n + 2];
        a[0] = b[0] = Integer.MIN_VALUE;
        a[m + 1] = b[n + 1] = Integer.MAX_VALUE;
        System.arraycopy(nums1, 0, a, 1, m);
        System.arraycopy(nums2, 0, b, 1, n);

        // 枚举 nums1 有 i 个数在第一组
        // 那么 nums2 有 j = (m+n+1)/2 - i 个数在第一组
        int i = 0;
        int j = (m + n + 1) / 2;
        while (true) {
            if (a[i] < b[j + 1] && a[i + 1] > b[j]) { // 写 >= 也可以
                int max1 = Math.max(a[i], b[j]); // 第一组的最大值
                int min2 = Math.min(a[i + 1], b[j + 1]); // 第二组的最小值
                return (m + n) % 2 > 0 ? max1 : (max1 + min2) / 2.0;
            }
            i++; // 继续枚举
            j--;
        }
    }

    public double findMedianSortedArrays2(int[] a, int[] b) {
        if (a.length > b.length) {
            // 交换 a 和 b
            int[] tmp = a;
            a = b;
            b = tmp;
        }

        int m = a.length;
        int n = b.length;
        // 循环不变量：a[left] <= b[j+1]
        // 循环不变量：a[right] > b[j+1]
        int left = -1;
        int right = m;
        while (left + 1 < right) { // 开区间 (left, right) 不为空
            int i = left + (right - left) / 2;
            int j = (m + n + 1) / 2 - i - 2;
            if (a[i] <= b[j + 1]) {
                left = i; // 缩小二分区间为 (i, right)
            } else {
                right = i; // 缩小二分区间为 (left, i)
            }
        }

        // 此时 left 等于 right-1
        // a[left] <= b[j+1] 且 a[right] > b[j'+1] = b[j]，所以答案是 i=left
        int i = left;
        int j = (m + n + 1) / 2 - i - 2;
        int ai = i >= 0 ? a[i] : Integer.MIN_VALUE;
        int bj = j >= 0 ? b[j] : Integer.MIN_VALUE;
        int ai1 = i + 1 < m ? a[i + 1] : Integer.MAX_VALUE;
        int bj1 = j + 1 < n ? b[j + 1] : Integer.MAX_VALUE;
        int max1 = Math.max(ai, bj);
        int min2 = Math.min(ai1, bj1);
        return (m + n) % 2 > 0 ? max1 : (max1 + min2) / 2.0;
    }
}
