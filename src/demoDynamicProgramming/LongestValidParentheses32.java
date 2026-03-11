package demoDynamicProgramming;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号 子串 的长度。
 * 左右括号匹配，即每个左括号都有对应的右括号将其闭合的字符串是格式正确的，比如 "(()())"。
 */
public class LongestValidParentheses32 {
    public int longestValidParentheses(String s) {
        int ans = 0;
        // dp[i] 表示以 i 位置字符结尾的最长有效括号的长度
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); ++i) {
            // 只需要考虑 ')' 结尾的
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') { // 形如 "...()"
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') { // 形如 "...))" 且上一最长有效子串的前一个恰好是 '('
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // 法二：栈
    public int longestValidParentheses2(String s) {
        // 做法：始终保持栈底元素为当前已遍历过的元素中 “最后一个没有被匹配的右括号的下标”
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            // 遇到左括号，将其下标入栈
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else { // 遇到右括号
                // 先弹出栈顶元素
                stack.pop();
                // 如果栈空，说明当前右括号为没有被匹配的右括号
                // 将其下标放入栈中来更新 “最后一个没有被匹配的右括号的下标"
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // 栈不空，当前右括号的下标减去栈顶元素 即为 ”以该右括号为结尾的最长有效括号的长度“
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }
}
