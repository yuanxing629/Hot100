package demoSubstring;

public class MinimumWindowSubstring76 {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // 使用数组代替哈希表，提高效率（假设字符在 ASCII 范围内）
        int[] need = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        // 定义滑动窗口左右指针，收缩和扩张
        int left = 0, right = 0;

        // 当前满足条件的字符串的长度，找最小
        int minLen = Integer.MAX_VALUE;

        // 结束满足条件，t 中还需要寻找的字符串数量
        int needCount = t.length();

        // 最小字串起始位置，后续结合 minLen 计算最短字符串
        int start = 0;

        // 右指针不断向右扩张
        while (right < s.length()) {
            char sCharAtRight = s.charAt(right);

            // 如果找到还需要的字符
            if (need[sCharAtRight] > 0) {
                needCount--;
            }
            // 所需字符减一，可以为负数，表示多余该字符
            need[sCharAtRight]--;
            // 窗口扩张
            right++;

            // 所有需要字符都已经找到
            while (needCount == 0) {
                int currLen = right - left; // 当前字符串长度
                // 更新最短字符串
                if (minLen > currLen) {
                    minLen = currLen;
                    start = left;
                }

                char sCharAtLeft = s.charAt(left);
                // 所需字符加一
                need[sCharAtLeft]++;
                // 说明少了一个所需字符串，数量加 1
                if (need[sCharAtLeft] > 0) {
                    needCount++;
                }
                // 窗口收缩
                left++;
            }

        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}

