package com.yun.code.package0000.package90;

import com.yun.algorithm.tree.TreeNode;

import java.util.ArrayList;
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
        if(n<1){
            return 0;
        }
        return generateTrees(1,n).size();
    }
    public List<TreeNode> generateTrees(int start, int end) {
        ArrayList<TreeNode> list = new ArrayList<>();
        if(start>end){
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> treeNodeListLeft = generateTrees(start, i - 1);
            List<TreeNode> treeNodeListRight = generateTrees(i+1, end);
            for (TreeNode treeNodeLeft:treeNodeListLeft){
                for (TreeNode treeNodeRight:treeNodeListRight) {
                    TreeNode treeNodeRoot =new TreeNode(i);
                    treeNodeRoot.left=treeNodeLeft;
                    treeNodeRoot.right=treeNodeRight;
                    list.add(treeNodeRoot);
                }
            }
        }
        return list;
    }

}
