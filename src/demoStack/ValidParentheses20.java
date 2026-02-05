package demoStack;

import java.util.Stack;

public class ValidParentheses20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        int i = 0;
        while (i < s.length()) {
            char ch = arr[i];
            switch (ch) {
                // 左括号入栈
                case '(', '[', '{':
                    stack.push(ch);
                    break;
                // 右括号，先判断栈是否为空，再检测栈顶元素
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                default:
                    break;
            }
            i++;
        }
        return stack.isEmpty(); // 栈为空说明括号匹配，否则不匹配
    }
}
