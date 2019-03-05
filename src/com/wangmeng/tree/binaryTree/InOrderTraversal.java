package com.wangmeng.tree.binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.wangmeng.dataStructure.TreeNode;
/**
 * 中序遍历
 * 非递归
 *
 * @author wangmeng
 */
public class InOrderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode treeNode = stack.pop();
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
            stack.push(treeNode);
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
            if (treeNode.right == null && treeNode.left == null) break;
        }
        while (!stack.empty()) {
            res.add(stack.pop().val);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.left.right = new TreeNode(6);
        List<Integer> list = new InOrderTraversal().inorderTraversal(treeNode);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
