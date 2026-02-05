package demoLinkedList;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoLinkedLists160 {
    // 暴力双循环
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        while (p != null) {
            ListNode q = headB;
            while (q != null) {
                if (p == q) {
                    return q;
                }
                q = q.next;
            }
            p = p.next;
        }
        return null;
    }

    // 法二：哈希集合
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            set.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // 双指针
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}


