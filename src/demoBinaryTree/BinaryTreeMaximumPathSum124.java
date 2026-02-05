package demoBinaryTree;

/**
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class BinaryTreeMaximumPathSum124 {
    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0; // 没有节点，和为0
        }

        int leftMax = dfs(node.left); // 左子树最大链和
        int rightMax = dfs(node.right); // 右子树最大链和
        ans = Math.max(ans, leftMax + rightMax + node.val); // 两条链拼成路径
        return Math.max(Math.max(leftMax, rightMax) + node.val, 0); // 当前子树最大链和
    }
}
