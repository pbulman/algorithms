package test;

import java.util.Stack;

public class ExpressionTreeHW7 {

	static class TreeNode {
		char label;
		TreeNode left;
		TreeNode right;
		
		TreeNode(char l) {
			label = l;
			left = null;
			right = null;
		}
	}
	
	static class ExpressionTree {
		
		public TreeNode makeTree(String input) {
			Stack<TreeNode> nodeStack = new Stack();
			TreeNode root, left, right;
			
			for(int i=0; i<input.length(); i++) {
				char currChar = input.charAt(i);
				//If character is operand
				if(currChar != '+' && currChar != '-' && currChar != '*' && currChar != '/') {
					nodeStack.push(new TreeNode(currChar));
					//System.out.println("operand push");
				}
				//If character is operator
				else {
					//System.out.println("operator push");
					//Create new root
					root = new TreeNode(currChar);
					//Get left/right nodes from stack
					right = nodeStack.pop();
					left = nodeStack.pop();
					
					//Point root to left/right nodes accordingly
					root.right = right;
					root.left = left;
					
					//Push new root onto nodeStack
					nodeStack.push(root);
				}
			}
			
			root = nodeStack.peek();
			//Return root of constructed expression tree
			return root;
		}
		
		public void preOrder(TreeNode root) {
			if (root == null) {
				return;
			}
				
			System.out.println(root.label);
			//Since binary tree, for loop not needed, entire left side can be completed followed by right
			preOrder(root.left);
			preOrder(root.right);
		}
		
		public void postOrder(TreeNode root) {
			if (root == null) {
				return;
			}
	
			//Since binary tree, for loop not needed, entire left side can be completed followed by right
			postOrder(root.left);
			postOrder(root.right);
			
			System.out.println(root.label);
		}
		
		public void inOrder(TreeNode root) {
			if(root == null) {
				return;
			}
			
			//Since binary tree, for loop not needed, entire left side can be completed followed by right
			inOrder(root.left);
			System.out.println(root.label);
			inOrder(root.right);
		}
	}
	
	public static void main(String args[]) {
		ExpressionTree tree = new ExpressionTree();
		
		
		String seq = "abc*+de*f+g*+";
		TreeNode root = tree.makeTree(seq);
		System.out.println("Post-fix expression: ");
		tree.postOrder(root);
		
		System.out.println("Pre-fix expression: ");
		tree.preOrder(root);
		
		System.out.println("In-order expression: ");
		tree.inOrder(root);
		
		
		seq = "ab/fg*+cd+e/-";
		TreeNode root2 = tree.makeTree(seq);
		System.out.println("Post-fix expression: ");
		tree.postOrder(root2);
		
		System.out.println("Pre-fix expression: ");
		tree.preOrder(root2);
		
		System.out.println("In-order expression: ");
		tree.inOrder(root2);
		
	}
}
