package demoSlidingWindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters3 {
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        while (j < arr.length) {
            char ch = arr[j]; // ch为窗口右边界的元素
            while (set.contains(ch)) { // 只要集合中包含ch，就移除窗口左边界元素，且下标右移
                set.remove(arr[i++]);
            }
            set.add(arr[j++]);
            maxLen = Math.max(maxLen, j - i); // 上一行已经j++了，所以这里不需要写j-i+1
        }
        return maxLen;
    }
}
