package demoBinaryTree;

import java.util.*;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class LowestCommonAncestorOfABinaryTree236 {

    // 法一：非递归后序遍历
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 辅助栈和路径保存
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<TreeNode> pathP = null;
        List<TreeNode> pathQ = null;

        TreeNode curr = root;
        TreeNode lastVisited = null;

        // 开始非递归后序遍历
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peekNode = stack.peek();
                // 如果右子树存在且未被访问过，则转向右子树
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    // 此时为后序遍历的“访问”时机
                    if (peekNode == p) {
                        pathP = new ArrayList<>(stack);
                        // 注意：栈是后进先出，ArrayList 复制后底层顺序是 [root, ..., p] 的逆序
                    }
                    if (peekNode == q) {
                        pathQ = new ArrayList<>(stack);
                    }

                    // 提前剪枝：如果两条路径都找到了，直接退出循环
                    if (pathP != null && pathQ != null) break;

                    lastVisited = stack.pop();
                    curr = null; // 确保下一轮继续从栈中取节点
                }
            }
        }

        // 比较两条路径，找到最后一个公共节点
        // 因为 ArrayDeque 复制到 ArrayList 后，元素顺序通常是 [p, ..., root] (取决于实现)
        // 我们倒着遍历寻找第一个相同的节点
        TreeNode lca = null;
        int i = pathP.size() - 1;
        int j = pathQ.size() - 1;

        while (i >= 0 && j >= 0) {
            if (pathP.get(i) == pathQ.get(j)) {
                lca = pathP.get(i);
            } else {
                break;
            }
            i--;
            j--;
        }

        return lca;
    }

    // 法二：递归
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // 1. Base Case: 如果 root 为空，或者找到了 p 或 q，直接返回当前的 root
        if (root == null || root == p || root == q) {
            return root;
        }

        // 2. 递归寻找左右子树
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        // 3. 结果合并与回溯逻辑

        // 情况 A: 左右子树都返回了非空值
        // 说明 p 和 q 分布在当前节点的两侧，那么当前节点就是 LCA
        if (left != null && right != null) {
            return root;
        }

        // 情况 B: 只有一边非空
        // 说明 p 和 q 都在这一侧，或者这一侧只找到了其中一个
        // 谁不为空就返回谁，向上级“交差”
        return (left != null) ? left : right;
    }

    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    // 法三：记录父节点
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }
}
