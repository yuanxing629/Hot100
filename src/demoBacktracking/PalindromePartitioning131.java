package demoBacktracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 */
public class PalindromePartitioning131 {

    List<List<String>> res = new ArrayList<>();
    List<String> cur = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0, new StringBuilder());
        return res;
    }

    private void backtrack(String s, int start, StringBuilder sb) {
        if (start == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        // 从前往后搜索，如果发现回文，进入 backtrack，起始位置后移移位，循环结束时移除 cur 的末位
        for (int i = start; i < s.length(); ++i) {
            sb.append(s.charAt(i));
            if (check(sb)) {
                cur.add(sb.toString());
                backtrack(s, i + 1, new StringBuilder());
                cur.removeLast();
            }
        }

    }

    private boolean check(StringBuilder sb) {
        for (int i = 0; i < sb.length() / 2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}

class ModifyVersion131 {
    List<List<String>> res = new ArrayList<>();
    List<String> cur = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0);
        return res;
    }

    private void backtrack(String s, int start) {
        // 递归终止条件：当起始位置到达字符串末尾，说明找到了一种完全分割方案
        if (start == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        // 从 start 开始，枚举每一个可能的切割点 i
        for (int i = start; i < s.length(); ++i) {
            // 如果 [start, i] 这一段是回文串，才进行下一步
            if (isPalindrome(s, start, i)) {
                // 做出选择：截取这段回文串加入当前路径
                cur.add(s.substring(start, i + 1));

                // 递归：从 i + 1 的位置继续向后寻找
                backtrack(s, i + 1);

                // 撤销选择（回溯）：移除刚刚加入的回文串，尝试下一种切割可能
                cur.remove(cur.size() - 1); // 兼容性比 removeLast() 更好
            }
        }
    }

    // 利用双指针在原字符串上直接判断 [left, right] 区间是否为回文
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
