package com.wangmeng.tree.binaryTree;

import com.wangmeng.dataStructure.TreeNode;
import org.junit.Test;


/**
 * 二叉树的深度
 */
public class MaximumDepth {
    /**
     * Botton-up
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        int leftValue = maxDepth1(root.left);
        int rightValue = maxDepth1(root.right);
        return Math.max(leftValue, rightValue) + 1;
    }

    /**
     * Top-down
     * @return
     */
    int ans = 0;
    public int maxDepth2(TreeNode root) {
        maxDepth2Helper(root, 1);
        return ans;
    }

    /**
     *
     * @param root
     * @param depth
     * @return
     */
    public void maxDepth2Helper(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            //叶子节点 计算
            ans = Math.max(depth, ans);
        }
        maxDepth2Helper(root.left, depth + 1);
        maxDepth2Helper(root.right, depth + 1);
    }

    @Test
    public void test() {
        TreeNode treeNode = new TreeNode(3);
        TreeNode left1 = new TreeNode(9);
        TreeNode right1 = new TreeNode(20);
        TreeNode left2 = new TreeNode(15);
        TreeNode right2 = new TreeNode(7);
        treeNode.left = left1;
        treeNode.right = right1;
        right1.left = left2;
        right1.right = right2;

        int i = maxDepth2(treeNode);
        System.out.println(i);
    }
}
