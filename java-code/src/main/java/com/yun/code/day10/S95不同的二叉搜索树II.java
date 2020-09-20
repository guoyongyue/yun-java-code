package com.yun.code.day10;

import com.yun.algorithm.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 */
public class S95不同的二叉搜索树II {
    public static void main(String[] args) {
        S95不同的二叉搜索树II s95不同的二叉搜索树II = new S95不同的二叉搜索树II();
        s95不同的二叉搜索树II.generateTrees(1);
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i,"");
                    currTree.leftChild = left;
                    currTree.rightChild = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

}
