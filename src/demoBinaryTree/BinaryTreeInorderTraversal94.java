package demoBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 */
public class BinaryTreeInorderTraversal94 {
    // 法一：递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    // 法二：非递归
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            // 只要还没遍历完，就把节点入栈。然后继续向左遍历
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else { // 否则出栈，并将栈顶元素给res。然后向右遍历。
                p = stack.pop();
                res.add(p.val);
                p = p.right;
            }
        }
        return res;
    }
}
