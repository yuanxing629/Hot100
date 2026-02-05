package demoBinaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
public class FlattenBinaryTree114 {
    List<TreeNode> firstOrderList = new ArrayList<>();

    public void flatten(TreeNode root) {
        firstOrder(root);
        for (int i = 0; i < firstOrderList.size() - 1; i++) {
            firstOrderList.get(i).left = null;
            firstOrderList.get(i).right = firstOrderList.get(i + 1);
        }
        // 最后一个元素单独处理
        if (!firstOrderList.isEmpty()) {
            firstOrderList.getLast().left = null;
            firstOrderList.getLast().right = null;
        }
    }

    /**
     * 先序遍历构造一个先序的列表，列表元素为树节点
     *
     * @param root
     */
    public void firstOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        firstOrderList.add(root);
        firstOrder(root.left);
        firstOrder(root.right);
    }

    /**
     * 先序遍历 - 非递归版本
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    /**
     * 法二：先序遍历和展开同时进行
     *
     * @param root
     */
    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null; // prev 初始为 null
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) { // 只有 prev 不为null 时才赋值
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left, right = curr.right;
            // 注意入栈顺序，先右孩子再做孩子
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            prev = curr;
        }
    }

    /**
     * 法三：寻找前驱节点
     */
    public void flatten4(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) { // 当前节点的左子节点不空
                TreeNode next = curr.left;
                TreeNode prev = next;
                while (prev.right != null) { // 找到当前节点的左子树中的最右节点，记为前驱节点prev
                    prev = prev.right;
                }
                prev.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }
}
