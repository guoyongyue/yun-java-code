package com.yun.code.package0100.package00;

import com.yun.algorithm.tree.TreeNode;

/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class S100相同的树 {
    public static void main(String[] args) {

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return Boolean.FALSE;
        } else if(p==null || q==null){
            return Boolean.FALSE;
        } else if(p.index != q.index){
            return Boolean.FALSE;
        }
        return isSameTree(p.left,p.right) && isSameTree(p.right,q.right);
    }




}
