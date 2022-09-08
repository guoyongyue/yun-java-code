package com.yun.code.package0100.package00;

import com.yun.algorithm.tree.TreeNode;

public class S101对称二叉树 {
    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return Boolean.TRUE;
        }
        return check(root.left,root.right);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if(p==null && p==null){
            return Boolean.TRUE;
        }else if(){

        }
    }
}
