package demoBinaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return myBuild(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuild(int[] preorder, int[] inorder, int l1, int r1, int l2, int r2) {
        TreeNode root = new TreeNode();
        root.val = preorder[l1];
        int i = l2;
        while (inorder[i] != root.val) { // 定位中序遍历的根节点
            i++;
        }
        if (i > l2) { // 构造root的左子树
            root.left = myBuild(preorder, inorder, l1 + 1, i - l2 + l1, l2, i - 1);
        } else {
            root.left = null;
        }
        if (i < r2) { // 构造root的右子树
            root.right = myBuild(preorder, inorder, i - l2 + l1 + 1, r1, i + 1, r2);
        } else {
            root.right = null;
        }
        return root;
    }

    /**
     * 迭代
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        // 根节点先入栈
        stack.push(root);
        // 指针先指向中序遍历的第一个节点
        int inorderIndex = 0;
        // 依次遍历先序遍历中除第一个节点外的所有节点
        for (int i = 1; i < preorder.length; i++) {
            TreeNode curr = new TreeNode(preorder[i]); // 当前节点
            TreeNode node = stack.peek(); // 栈顶节点
            // 如果index和栈顶节点不同，我们将当前节点作为栈顶节点的左儿子
            if (node.val != inorder[inorderIndex]) {
                node.left = curr;
            } else {
                // 如果index恰好指向栈顶节点，那么不断地弹出栈顶节点并向右移动index
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                // 并将当前节点作为最后一个弹出的节点的右儿子
                node.right = curr;
            }
            // 无论哪种情况，最后都将当前节点入栈
            stack.push(curr);
        }
        return root;
    }
}
