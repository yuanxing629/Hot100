package demoBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 */
public class MaximumDepthOfBinaryTree104 {

    public int maxDepth(TreeNode root) {
        int lDepth, rDepth;
        if (root == null) {
            return 0;
        } else {
            lDepth = maxDepth(root.left);
            rDepth = maxDepth(root.right);
            return (lDepth > rDepth ? lDepth + 1 : rDepth + 1);
        }
    }

    public void getDepth(TreeNode p, int[] arr) {
        if (p != null) {
            ++arr[0];
            if (arr[0] >= arr[1]) {
                arr[1] = arr[0];
            }
            getDepth(p.left, arr);
            getDepth(p.right, arr);
            --arr[0];
        }
    }

    public int maxDepth2(TreeNode root) {
        int[] arr = {0, 0};
        getDepth(root, arr);
        return arr[1];
    }

    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }
}
