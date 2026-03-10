package demoGreedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 */
public class PartitionLabels763 {

    public List<Integer> partitionLabels(String s) {
        int[] lastIndex = new int[26]; // i 为字符 lastIndex[i] 为字符出现的最后位置
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            lastIndex[c - 'a'] = i; // 找到 a-z 最后出现的位置
        }
        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            right = Math.max(right, lastIndex[c - 'a']);// 找到当前片段字符出现的最远边界
            if (i == right) { // 下标 == 当前片段字符最大出现位置
                res.add(right - left + 1); // 更新 res
                left = i + 1; // 更新下一片段的左边界
            }
        }
        return res;
    }
}
