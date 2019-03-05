package com.wangmeng.tree.binaryTree;

import com.wangmeng.dataStructure.TreeNode;

import java.util.LinkedList;

public class SerializeBinaryTree {

    public String serialize(TreeNode root) {
        if (root == null) return null;
        return BFS(root);
    }

    //BFS traverse
    public String BFS(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.pop();
                if (temp == null) {
                    sb.append("#,");
                } else {
                    sb.append(temp.val+",");
                }
                if (temp != null) {
                    queue.offer(temp.left);
                    queue.offer(temp.right);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.left.right = new TreeNode(6);

        String result = new SerializeBinaryTree().serialize(treeNode);
        System.out.println(result);
    }
}
