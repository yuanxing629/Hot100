package demoLinkedList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class SortList148 {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        // 快慢指针找到中点
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        return merge(list1, list2);
    }

    // 将两个非递减的链表合并为一个非递减的链表。LeetCode21
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    public ListNode sortList2(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        ListNode dummy = new ListNode(0, head);
        for (int subLen = 1; subLen < length; subLen *= 2) {
            ListNode prev = dummy, curr = dummy.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLen && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLen && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }

                prev.next = merge(head1, head2);
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummy.next;
    }

}
