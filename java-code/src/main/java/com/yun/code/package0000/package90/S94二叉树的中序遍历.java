package com.yun.code.package0000.package90;

import com.yun.algorithm.tree.BinaryTree;
import com.yun.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *        1
 *     2     3
 *    4  5  6  7
 *  8  9
 */
public class S94二叉树的中序遍历 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1,"1");
        TreeNode treeNode2 = new TreeNode(2,"2");
        TreeNode treeNode3 = new TreeNode(3,"3");
        TreeNode treeNode4 = new TreeNode(4,"4");
        TreeNode treeNode5 = new TreeNode(5,"5");
        TreeNode treeNode6 = new TreeNode(6,"6");
        TreeNode treeNode7 = new TreeNode(7,"7");
        TreeNode treeNode8 = new TreeNode(8,"8");
        TreeNode treeNode9 = new TreeNode(9,"9");
        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;

        treeNode2.left=treeNode4;
        treeNode2.right=treeNode5;

        treeNode3.left=treeNode6;
        treeNode3.right=treeNode7;


        treeNode4.left=treeNode8;
        treeNode4.right=treeNode9;
        S94二叉树的中序遍历 s94二叉树的中序遍历 = new S94二叉树的中序遍历();
        s94二叉树的中序遍历.inorderTraversal(treeNode1);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        midOrder(list,root);
        return list;
    }

    public void midOrder(List<Integer> list,TreeNode node){
        if(node==null){
            return;
        }else {
            midOrder(list,node.left);
            System.out.println(node.val);
            list.add(node.val);
            midOrder(list,node.right);

        }
    }
}
