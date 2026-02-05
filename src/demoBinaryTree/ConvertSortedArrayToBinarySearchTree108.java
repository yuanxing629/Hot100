package demoBinaryTree;

/**
 * 给你一个整数数组nums，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 */
public class ConvertSortedArrayToBinarySearchTree108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;
        // 总是选择中间位置右边的数字作为根节点
        // int mid = (left + right + 1) / 2;
        // 选择任意一个中间位置数字作为根节点
        // int mid = (left + right + rand.nextInt(2)) / 2;
        // 这三种策略是一样的，只是最终生成的树结构可能不一样，但都会得到一棵平衡二叉搜索树

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }
}
