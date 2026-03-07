package demoStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */
public class DailyTemperatures739 {

    /**
     * 栈中记录还没算出下一个更大元素的那些数的下标。
     * 相当于栈是一个 todolist ，在循环的过程中，现在还不知道答案是多少，在后面的循环中会算出答案。
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> st = new ArrayDeque<>(); // todolist
        for (int i = 0; i < n; i++) {
            int t = temperatures[i];
            // 栈非空 且 当前温度 大于栈顶索引对应的温度
            while (!st.isEmpty() && t > temperatures[st.peek()]) {
                // 出栈 并 记录当前索引-栈顶索引
                int j = st.pop();
                ans[j] = i - j;
            }
            // 当前索引入栈
            st.push(i);
        }
        return ans;
    }
}
