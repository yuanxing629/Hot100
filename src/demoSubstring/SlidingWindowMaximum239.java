package demoSubstring;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowMaximum239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 超时 O(n*k*logk)
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        int j = 0;
        while (j <= n - k) {
            Object[] arr = queue.toArray();
            Arrays.sort(arr);
            res[j] = (int) arr[k-1];
            j++;
            queue.remove();
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) return new int[0];

        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>(); // 存储索引

        for (int i = 0; i < n; i++) {
            // 1. 移除队列中不在窗口内的元素（从队首移除）
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 2. 移除队列中小于当前元素的值（从队尾移除，保持单调递减）
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. 当前元素入队
            deque.offerLast(i);

            // 4. 当窗口形成后，记录最大值（队首元素）
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return res;
    }
}
