package com.wangmeng.tree.binaryTree;
import com.wangmeng.dataStructure.TreeNode;
import org.junit.jupiter.api.Test;

public class PathSum {
    boolean flag = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null && sum == 0) {
            return false;
        }
        hasPathSumHelper(root, sum, 0);
        return flag;
    }
    public void hasPathSumHelper(TreeNode root, int sum, int tempSum) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            //conut
            if (sum == tempSum + root.val) {
                flag = true;
            }
        }
        hasPathSumHelper(root.left, sum, tempSum + root.val);
        hasPathSumHelper(root.right, sum, tempSum + root.val);
    }

    @Test
    public void test() {
        TreeNode treeNode = new TreeNode(-2);
        treeNode.left = new TreeNode(-3);
        boolean b = hasPathSum(treeNode, -5);
        System.out.println(b);
    }
}
