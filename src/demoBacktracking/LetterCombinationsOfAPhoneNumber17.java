package demoBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class LetterCombinationsOfAPhoneNumber17 {

    // 全局列表存储最终结果
    List<String> list = new ArrayList<>();
    // 每次迭代取一个字符串，所以会涉及大量字符串拼接， 因此选择更高效的 StringBuilder
    StringBuilder temp = new StringBuilder();
    // 初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串
    String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return list;
        }

        // 迭代处理
        backtrack(digits, 0);
        return list;
    }

    private void backtrack(String digits, int num) {
        // 本次回溯传入的 num 已经等于 digits 的长度
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }

        // str 表示当前 num 对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            // 递归，处理下一层
            backtrack(digits, num + 1);
            // 剔除末尾的继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
