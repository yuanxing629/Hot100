package demoBinaryTree;

/*
    给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点root。
    两节点之间路径的 长度 由它们之间边数表示。
 */
public class DiameterOfBinaryTree543 {
    // 全局变量 ans 记录 d_node 的最大值
    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1; // ans-1 即为树的直径
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 空节点，以node为根的子树深度为0
        }
        int lDepth = depth(node.left); // 左儿子为根的子树深度
        int rDepth = depth(node.right); // 右儿子为根的子树深度
        ans = Math.max(ans, lDepth + rDepth + 1); // 更新ans
        return Math.max(lDepth, rDepth) + 1; // 返回该节点为根的子树的深度
    }
}
