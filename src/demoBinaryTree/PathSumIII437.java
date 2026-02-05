package demoBinaryTree;

/**
 * 给定一个二叉树的根节点root，和一个整数targetSum，求该二叉树里节点值之和等于targetSum的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class PathSumIII437 {

    /**
     * 以节点root为起点向下前满足路径总和为targetSum的路径数目
     */
    public int rootSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        int val = root.val;

        if (val == targetSum) {
            res++;
        }

        res += rootSum(root.left, targetSum - val);
        res += rootSum(root.right, targetSum - val);
        return res;
    }

    public int pathSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }

        int res = rootSum(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }
}
