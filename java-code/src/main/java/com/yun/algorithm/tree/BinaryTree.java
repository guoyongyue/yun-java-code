package com.yun.algorithm.tree;

import java.util.Stack;

public class BinaryTree {

	public static void main(String[] args){
		BinaryTree binaryTree = new BinaryTree();
		TreeNode root = TreeNode.createBinaryTree();

		/*int height = binaryTree.getHeight(root);
		System.out.println("树的高度treeHigh:"+height);*/

		int size = binaryTree.getSize(root);
		System.out.println("结点数treeSize:"+size);

		/*System.out.print("前序preOrder data:");
		binaryTree.preOrder(root);

		System.out.print("\n中序midOrder data:");
		binaryTree.midOrder(root);

		System.out.print("\n后序postOrder data:");
		binaryTree.postOrder(root);

		System.out.print("\n前序非递归preOrder data:");
		binaryTree.nonRecOrder(root);*/
	}

	/**
	 * 求二叉树的高度
	 * @author smallkong
	 *
	 */

	/**
	 * 构建二叉树
	 *          A
	 *     B       C
	 * D      E        F
	 */
	private int getHeight(TreeNode node) {
		if(node == null){
			return 0;
		}else{
			int i = getHeight(node.left);
			int j = getHeight(node.right);
			return (i<j)?j+1:i+1;
		}
	}

	/**
	 * 获取二叉树的结点数
	 * @author smallkong
	 *
	 */

	private int getSize(TreeNode node) {
		if(node == null){
			return 0;
		}else{
			return 1+getSize(node.left)+getSize(node.right);
		}
	}


	/**
	 * 前序遍历——递归
	 * @author smallkong
	 *
	 */
	public void preOrder(TreeNode node){
		if(node == null){
			return;
		}else{
			System.out.print(node.getData());
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	/**
	 * 前序遍历——非递归
	 */
	
	public void nonRecOrder(TreeNode node){
		if(node == null){
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(node);
		while(!stack.isEmpty()){
			//出栈和进栈
			TreeNode n = stack.pop();//弹出根结点
			//压入子结点
			System.out.print(n.getData());
			if(n.right!=null){
				stack.push(n.right);
				
			}
			if(n.left!=null){
				stack.push(n.left);
			}
		}
	}
	/**
	 * 中序遍历——递归
	 * @author smallkong
	 *
	 */
	public void midOrder(TreeNode node){
		if(node == null){
			return;
		}else{
			midOrder(node.left);
			System.out.print(node.getData());
			midOrder(node.right);
		}
	}
	
	/**
	 * 后序遍历——递归
	 * @author smallkong
	 *
	 */
	public void postOrder(TreeNode node){
		if(node == null){
			return;
		}else{
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.getData());
		}
	}
	
	

}