package demoLinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class PalindromeLinkedList234 {

    // 栈
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode curr = head;
        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            if (stack.peek() != curr.val) {
                return false;
            }
            stack.pop();
            curr = curr.next;
        }
        return true;
    }

    // 法二：额外数组
    public boolean isPalindrome2(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 法三：快慢指针找中间节点
    public boolean isPalindrome3(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 找到中间节点，并翻转后半部分节点
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false; // 这里还不能直接返回false，因为还没有还原原链表
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表
        firstHalfEnd.next = reverseList(secondHalfStart);

        return result;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        // 如果是偶数个，slow会走到第n/2个
        // 如果是奇数个，slow刚好走到中间
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
