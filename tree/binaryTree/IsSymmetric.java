package com.wangmeng.tree.binaryTree;

import java.util.ArrayList;
import java.util.List;
import com.wangmeng.dataStructure.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * 判断是否为堆成树
 */
public class IsSymmetric {
    public boolean isSymmetric1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        if (root.left == null || root.right == null) return false;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        preOrederTraversal(root.left, list1);
        preOrederTraversal2(root.right, list2);
        if (list1.size() != list2.size()) {
            return false;
        } else {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) != list2.get(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    //preOrderTraversal
    public void preOrederTraversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preOrederTraversal(root.left, list);
            preOrederTraversal(root.right, list);
        } else {
            list.add(1);
        }
    }
    //preOrderTraversal2
    public void preOrederTraversal2(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preOrederTraversal2(root.right, list);
            preOrederTraversal2(root.left, list);
        } else {
            list.add(1);
        }
    }

    //以上两种相反方向遍历的方法很慢，在这题上效率不高

    /**
     * 第二种方法
     * 高效
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        return root == null || isSymmetric2Helper(root.left, root.right);
    }

    public boolean isSymmetric2Helper(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left==right;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric2Helper(left.left, right.right) && isSymmetric2Helper(left.right, right.left);
    }

    @Test
    public void test() {
        TreeNode treeNode = new TreeNode(2);
        TreeNode left1 = new TreeNode(3);
        TreeNode right1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(4);
        TreeNode left3 = new TreeNode(5);
        TreeNode right3 = new TreeNode(5);
        TreeNode left4 = new TreeNode(8);
        TreeNode right4 = new TreeNode(8);
        TreeNode left5 = new TreeNode(9);
        TreeNode right5 = new TreeNode(9);
        treeNode.left = left1;
        treeNode.right = right1;
        left1.left = left2;
        right1.right = right2;
        left1.right = left3;
        right1.left = right3;
        left3.left = left4;
        right3.right = right4;
        left3.right = left5;
        right3.left = right5;

        boolean symmetric = isSymmetric2(treeNode);
        System.out.println(symmetric);
    }
}
