package com.yun.algorithm.tree;

public class TreeNode {
	public int index;
	public Integer val;
	public String data;
    public TreeNode left;
    public TreeNode right;

    public int getIndex() {
        return index;
    }


    public void setIndex(int index) {
        this.index = index;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

    public TreeNode(int index, String data) {
        this.index = index;
        this.data = data;
        this.left = null;
        this.right = null;
    }



	/**
	 * 构建二叉树
	 *         A
	 *     B       C
	 * D      E        F
	 */
	public static TreeNode createBinaryTree(){
		TreeNode root = new TreeNode(1, "A");
		TreeNode nodeB = new TreeNode(2, "B");
		TreeNode nodeC = new TreeNode(3, "C");
		TreeNode nodeD = new TreeNode(4, "D");
		TreeNode nodeE = new TreeNode(5, "E");
		TreeNode nodeF = new TreeNode(6, "F");
		root.left = nodeB;
		root.right = nodeC;
		nodeB.left = nodeD;
		nodeB.right = nodeE;
		nodeC.right = nodeF;
		return root;
	}
}