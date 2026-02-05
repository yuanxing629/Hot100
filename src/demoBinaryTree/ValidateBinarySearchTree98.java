package demoBinaryTree;

import java.util.*;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 严格小于 当前节点的数。
 * 节点的右子树只包含 严格大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class ValidateBinarySearchTree98 {
    // wrong。通过了77/86个测试用例
//    public boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        if (root.left != null && root.left.val >= root.val) {
//            return false;
//        }
//        if (root.right != null && root.right.val <= root.val) {
//            return false;
//        }
//        return isValidBST(root.left) && isValidBST(root.right);
//    }

    // 法一：递归
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    // 法二：中序遍历
    public boolean isValidBST2(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();
        inOrder(root, inOrderList);
        for (int i = 1; i < inOrderList.size(); i++) {
            if (inOrderList.get(i - 1) >= inOrderList.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    // 中序遍历：题解。非递归版
    public boolean isValidBST3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


}
