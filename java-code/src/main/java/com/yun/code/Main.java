package com.yun.code;


import com.yun.algorithm.tree.TreeNode;

public class Main {
    int maxSum = Integer.MIN_VALUE;
    public static void main(String[] args) {
    }
    public int maxPathSum (TreeNode root, Integer maxNum) {
        if(root == null){
            return 0;
        }
        int max = getMax(root, maxNum, 0);
        return max;
    }
    public int getMax(TreeNode root,Integer maxNum, Integer num){
        if(root == null){
            return 0;
        }
        //因为节点的值可以为负数，所以最大值取0和子树值的较大者
        int leftMax = Math.max(0,getMax(root.left,maxNum,num));
        int rightMax = Math.max(0,getMax(root.right,maxNum,num));
        //如果将当前root作为根节点，那么最大值是root.val+左子树最大值+右子树最大值
        maxSum = Math.max(maxNum,root.val+leftMax+rightMax);
        num++;
        if(maxNum.equals(num)){
            return maxSum;
        }
        //只能返回左右子树中较大值加上root.val
        return Math.max(0,root.val + Math.max(leftMax,rightMax));
    }


}
