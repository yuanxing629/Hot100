package demoBinaryTree;

public class InvertBinaryTree226 {

    /**
     * 给你一棵二叉树的根节点root，翻转这棵二叉树，并返回其根节点。
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
