package com.wangmeng.tree.binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.wangmeng.dataStructure.TreeNode;

public class PreOrderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.empty()) {
            if (curr != null) {
                res.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode temp = stack.pop();
                curr = temp.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(2);
        List<Integer> list = new PreOrderTraversal().preorderTraversal(treeNode);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
