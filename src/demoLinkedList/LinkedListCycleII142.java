package demoLinkedList;

import java.util.ArrayList;
import java.util.List;

public class LinkedListCycleII142 {
    public ListNode detectCycle(ListNode head) {
        // 空链表
        if (head == null) {
            return null;
        }

        List<ListNode> list = new ArrayList<>();
        list.add(head);
        ListNode curr = head.next;
        while (curr != null) {
            // 列表中如果有该节点，直接返回
            if (list.contains(curr)) {
                return curr;
            }
            list.add(curr);
            curr = curr.next;
        }
        // 遍历完了
        return null;
    }
}
