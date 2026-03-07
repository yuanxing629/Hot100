package demoStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangleInHistogram84 {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1); // 在栈中只有一个数的时候，栈顶的 【下面那个数】 是 -1
        int ans = 0;
        for (int right = 0; right <= n; right++) {
            // h 为当前高度。如果越界，h 为 -1
            int h = right < n ? heights[right] : -1;
            // 当栈中至少有 2 个数 且 栈顶索引的高度 >= h
            while (st.size() > 1 && heights[st.peek()] >= h) {
                int i = st.pop(); // 矩形的高（的下标）
                int left = st.peek(); // 栈顶下面那个数就是 left
                ans = Math.max(ans, heights[i] * (right - left - 1));
            }
            st.push(right);
        }
        return ans;
    }
}
