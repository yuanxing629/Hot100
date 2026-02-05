package demoBinaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
 */
public class KthSmallestElementInABST230 {

    // 中序遍历递归版
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list.get(k - 1);
    }

    public void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    // 中序遍历非递归版
    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        int count = 0;
        int ans = root.val;

        while (!stack.isEmpty() || root != null) {
            if (count == k) {
                break;
            }
            while (root != null) { // 不为空就入栈并向左走
                stack.push(root);
                root = root.left;
            }
            root = stack.pop(); // 左为空了出栈
            ++count; // 第 count 个节点出栈

            ans = root.val; // 第 count 个节点的值
            root = root.right;
        }
        return ans;
    }
}
