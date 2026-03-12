package demoBacktracking;

import java.util.ArrayList;
import java.util.List;


/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class GenerateParentheses22 {

    List<String> res = new ArrayList<>();
    StringBuilder temp = new StringBuilder();

    public List<String> generateParenthesis(int n) {
        backtrack(0, 0, n);
        return res;
    }

    private void backtrack(int open, int close, int max) {
        if (temp.length() == max * 2) {
            res.add(temp.toString());
            return;
        }

        // 如果左括号数量不大于 n，我们可以放一个左括号
        if (open < max) {
            temp.append('(');
            backtrack(open + 1, close, max);
            temp.deleteCharAt(temp.length() - 1);
        }

        // 如果右括号数量小于左括号的数量，我们可以放一个右括号。
        if (close < open) { // 注意：这里是 close < open 而不是 max
            temp.append(')');
            backtrack(open, close + 1, max);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
