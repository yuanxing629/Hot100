package demoBinaryTree;

import java.util.*;

/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class BinaryTreeRightSideView199 {

    // 法一：广度优先搜索
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 根节点入队
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll(); // 队不为空则出队一个节点
                size--; // size减1
                if (size == 0) { // size 为 0 说明已经是当前层的最后一个元素了
                    res.add(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left); // 左儿子入队
                }
                if (cur.right != null) {
                    queue.offer(cur.right); // 右儿子入队
                }
            }
        }
        return res;
    }

    // 法二：深度优先搜索
    public List<Integer> rightSideView2(TreeNode root) {
        // map 的 key 为深度，value 为该深度的最右元素的值
        Map<Integer, Integer> mostRightValueAtDepth = new HashMap<>();
        int max_depth = -1;

        // 节点栈和深度栈是一一对应的: 深度栈中的元素为该节点对应的深度
        Deque<TreeNode> nodeStack = new LinkedList<>();
        Deque<Integer> depthStack = new LinkedList<>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                // 维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 如果不存在对应深度的节点，才插入
                if (!mostRightValueAtDepth.containsKey(depth)) {
                    mostRightValueAtDepth.put(depth, node.val);
                }

                // 由于每次都是先访问右子树。所以要让左孩子先入栈
                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }

        List<Integer> rightView = new ArrayList<>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(mostRightValueAtDepth.get(depth));
        }
        return rightView;
    }
}
