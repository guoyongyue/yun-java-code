package com.yun.code.day10;

import com.yun.algorithm.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */
public class S96不同的二叉搜索树 {
    public static void main(String[] args) {

    }

    public int numTrees(int n) {
        if(n==0){
            return  0;
        }
        List list = generateTrees(1, n);
        return list.size();
    }

    private List<TreeNode> generateTrees(int start, int end){
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if(start>end){
            allTrees.add(null);
            return allTrees;
        }
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
