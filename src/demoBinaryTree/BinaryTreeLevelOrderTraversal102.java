package demoBinaryTree;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 */
public class BinaryTreeLevelOrderTraversal102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // 入队
        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = q.size();
            for (int i = 1; i <= currentLevelSize; i++) {
                TreeNode node = q.poll(); // 当前level中的节点挨个出队
                // assert node != null;
                level.add(node.val); // 出队节点的value给到level列表
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }

            }
            // for循环结束，level列表加入res
            res.add(level);
        }
        return res;
    }
}
