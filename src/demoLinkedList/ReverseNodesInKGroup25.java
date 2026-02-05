package demoLinkedList;

import java.util.ArrayList;
import java.util.List;

public class ReverseNodesInKGroup25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        List<ListNode> firstOfGroup = new ArrayList<>();

        ListNode dummy = new ListNode(0); // 虚拟头节点
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;
        int i = 0;
        while (curr != null) {
            if (i % k == 0) {
                firstOfGroup.add(curr);
            }
            curr = curr.next;
            i++;
        }
        firstOfGroup.add(null);
        curr = firstOfGroup.removeFirst();
        while (!firstOfGroup.isEmpty()) {
            ListNode nextFirst = firstOfGroup.removeFirst();
            prev.next = reverse(curr, nextFirst);
            prev = curr; // prev指向当前组的第一个节点，即翻转后的最后一个节点
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode first, ListNode nextFirst) {
        ListNode dummy = new ListNode(0);
        dummy.next = null;
        ListNode curr = first;
        ListNode nextNode;
        while (curr != null) {
            nextNode = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = nextNode;
        }

        assert first != null;
        first.next = nextFirst;

        return dummy.next;
    }
}
