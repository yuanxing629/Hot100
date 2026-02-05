package demoLinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 */
public class CopyListWithRandomPointer138 {
    Map<Node, Node> cachedNode = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cachedNode.get(head);
    }

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }

        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = new Node(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        } // 结束后A B C变为A A' B B' C C'

        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = node.next; // 该循环内每次检查的是拷贝节点A'
            // 若A.random不为空则返回A.random的后继节点；否则返回null。返回值是A'.random
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        Node headNew = head.next; // 首个拷贝节点
        for (Node node = head; node != null; node = node.next) {
            Node nodeNew = node.next; // A'
            node.next = node.next.next; // B'
            // 若B不为空则返回B的拷贝节点B'，否则返回null。返回值是A'的后继节点
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;
    }
}
