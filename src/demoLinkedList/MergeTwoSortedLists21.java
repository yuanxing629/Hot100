package demoLinkedList;

public class MergeTwoSortedLists21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);

        ListNode prev = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        if (list1 != null) {
            prev.next = list1;
        }
        if (list2 != null) {
            prev.next = list2;
        }
        return dummy.next;
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val <= list2.val) {
            mergeTwoLists2(list1.next, list2);
            return list1;
        } else {
            mergeTwoLists2(list1, list2.next);
            return list2;
        }
    }
}
