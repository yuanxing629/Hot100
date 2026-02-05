package demoDoublePointer;

public class TrappingRainWater42 {
    // 法一：按列求
    public int trap(int[] height) {
        int sum = 0;
        // 最两端的列不用考虑，因为一定不会存水。所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            // 找出左边最高的墙
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }

            int max_right = 0;
            // 找出右边最高的墙
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }

            // 取较矮的一端
            int shorter = Math.min(max_left, max_right);

            // 只有矮墙比当前列高时，当前列才会储水，否则没水。
            if (shorter > height[i]) {
                sum += (shorter - height[i]);
            }
        }
        return sum;
    }

    // 法二：动态规划
    public int trap2(int[] height) {
        int sum = 0;
        int n = height.length;
        int[] max_left = new int[n];
        int[] max_right = new int[n];

        for (int i = 1; i < n - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }

        // 最两端的列不用考虑，因为一定不会存水。所以下标从 1 到 length - 2
        for (int i = 1; i < n - 1; i++) {
            // 取较矮的一端
            int shorter = Math.min(max_left[i], max_right[i]);

            // 只有矮墙比当前列高时，当前列才会储水，否则没水。
            if (shorter > height[i]) {
                sum += (shorter - height[i]);
            }
        }
        return sum;
    }

    // 法三：双指针
    public int trap3(int[] height) {
        int sum = 0;
        int n = height.length;
        int max_left = 0, max_right = 0;
        int left = 1, right = n - 2;

        for (int i = 1; i < n - 1; i++) {
            // 从左到右更
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int shorter = max_left;
                if (shorter > height[left]) {
                    sum += (shorter - height[left]);
                }
                left++;
            }
            // 从右到左更
            else {
                max_right = Math.max(max_right, height[right + 1]);
                int shorter = max_right;
                if (shorter > height[right]) {
                    sum += (shorter - height[right]);
                }
                right--;
            }
        }
        return sum;
    }
}
