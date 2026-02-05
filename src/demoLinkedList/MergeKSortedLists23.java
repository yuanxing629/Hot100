package demoLinkedList;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class MergeKSortedLists23 {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    // 归并操作
    public ListNode merge(ListNode[] lists, int low, int high) {
        if (low == high) {
            return lists[low];
        }
        if (low > high) {
            return null;
        }

        int mid = (low + high) >> 1;

        return merge2Lists(merge(lists, low, mid), merge(lists, mid + 1, high));
    }

    // 合并两个有序链表
    public ListNode merge2Lists(ListNode head1, ListNode head2) {
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
}
