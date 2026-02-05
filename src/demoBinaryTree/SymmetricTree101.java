package demoBinaryTree;

import java.util.*;

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 */
public class SymmetricTree101 {
    /*public boolean isSymmetric(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();
        inOrder(root, inOrderList);
        while (!inOrderList.isEmpty()) {
            if (!Objects.equals(inOrderList.getFirst(), inOrderList.getLast())) {
                return false;
            }
            inOrderList.removeFirst();
            if (!inOrderList.isEmpty()) {
                inOrderList.removeLast();
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
    }*/

    // 法一：递归
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    public boolean check(TreeNode p, TreeNode q) {
        // 左右都为空，直接返回true
        if (p == null && q == null) {
            return true;
        }
        // 一空一不空，直接返回false
        if (p == null || q == null) {
            return false;
        }
        // 左右都非空，再看：
        // 值相同，且每棵树的左子树都和另一颗树的右子树镜像对称
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    // 法二：迭代
    public boolean isSymmetric2(TreeNode root) {
        return check2(root, root);
    }

    public boolean check2(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(u);
        q.offer(v);

        while (!q.isEmpty()) {
            // 每次取一对节点比较
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                // 注意：这里是continue，而非直接返回true
                continue;
            }
            if (u == null || v == null || u.val != v.val) {
                return false;
            }

            // 这是一对
            q.offer(u.left);
            q.offer(v.right);

            // 这也是一对
            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }
}
