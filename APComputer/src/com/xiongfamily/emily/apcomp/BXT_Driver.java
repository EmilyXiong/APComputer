package com.xiongfamily.emily.apcomp;

//name:     date:
import java.util.*;

/*******************
Driver for a binary expression tree class.
Input: a postfix string, each token separated by a space.
**********************/
public class BXT_Driver
{
   public static void main(String[] args)
   {
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("14 -5 / ");
      postExp.add("20 3 -4 + * ");
      postExp.add("2 3 + 5 / 4 5 - *");
   
      for( String postfix : postExp )
      {
         System.out.println("Postfix Exp: "  + postfix);
         BXT tree = new BXT();
         tree.buildTree( postfix );
         System.out.println("BXT: "); 
         System.out.println( tree.display() );
         System.out.print("Infix order:  ");
         System.out.println( tree.inorderTraverse() );
         System.out.print("Prefix order:  ");
         System.out.println( tree.preorderTraverse() );
         System.out.print("Evaluates to " + tree.evaluateTree());
         System.out.println( "\n------------------------");
      }
   }
}


/*******************
Represents a binary expression tree.
The BXT can build itself from a postorder expression.  It can
evaluate and print itself. It also prints an inorder string and a preorder string.  
**********************/
class BXT
 {

	private int count;
	private TreeNode root;

	public BXT() {
		count = 0;
		root = null;
	}

	/* enter your instance methods here. */

	// buildTree
	public void buildTree(String postfix) {

		String[] nodes = postfix.split(" ");
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode t, t1, t2;

		for (String node : nodes) {
			node = node.trim();
			count++;
			if (node.length() == 1 && isOperator(node.charAt(0))) {
				t = new TreeNode(node, null, null);
				t1 = stack.pop();
				t2 = stack.pop();
				t.setRight(t1);
				t.setLeft(t2);
				stack.push(t);
			} else {
				t = new TreeNode(node, null, null);
				stack.push(t);
			}
		}

		root = stack.pop();
	}

	public String display() {
		return display(root, 0);
	}

	/* copy the code that is in TreeLab */
	public String display(TreeNode t, int level) {
		String toRet = "";
		if (t == null)
			return "";
		toRet += display(t.getRight(), level + 1); // recurse right
		for (int k = 0; k < level; k++)
			toRet += "\t";
		toRet += t.getValue() + "\n";
		toRet += display(t.getLeft(), level + 1); // recurse left
		return toRet;
	}

	public String inorderTraverse() {
		return inorderTraverse(root);
	}

	/* copy the code that is in TreeLab */
	public String inorderTraverse(TreeNode t) {

		String toReturn = "";
		if (t == null) {
			return "";
		}
		toReturn += inorderTraverse(t.getLeft()); // recurse left
		toReturn += t.getValue() + " "; // inorder visit
		toReturn += inorderTraverse(t.getRight()); // recurse right
		return toReturn;
	}

	public String preorderTraverse() {
		return preorderTraverse(root);
	}

	/* copy the code that is in TreeLab */
	public String preorderTraverse(TreeNode t) {
		String toReturn = "";
		if (t == null)
			return "";
		toReturn += t.getValue() + " "; // preorder visit
		toReturn += preorderTraverse(t.getLeft()); // recurse left
		toReturn += preorderTraverse(t.getRight()); // recurse right
		return toReturn;
	}

	public double evaluateTree(){
		return evaluateTree(root);
	}

	public double evaluateTree(TreeNode t){
		double result, operand1, operand2;
		String temp = null;
		
		if(t == null){
			return 0.0;
		}
		else{
			temp = (String) t.getValue();
			if(temp.length() == 1 && isOperator(temp.charAt(0))){
				operand1 = evaluateTree(t.getLeft());
				operand2 = evaluateTree(t.getRight());
				result = eval(operand1, operand2, temp.charAt(0));
			}
			else{
				result = Double.parseDouble((String)t.getValue());
			}
		}
		
		return result;
	}
	
	public boolean isOperator(char ch) {
		String operators = "+-/*";
		for (int i = 0; i < operators.length(); i++) {
			if (ch == operators.charAt(i)) {
				return true;
			}
		}
		return false;
	}

	public double eval(double a, double b, char ch) {
		double result = 0.0;

		switch (ch) {
		case '+':
			result = a + b;
			break;
		case '-':
			result = a - b;
			break;
		case '*':
			result = a * b;
			break;
		case '/':
			result = a / b;
			break;
		default:
			System.out.println("Invalid operation!");
		}

		return result;
	}

}


/***************************************
 Postfix Exp: 14 -5 / 
 BXT: 
 	-5
 /
 	14
 Infix order:  14 / -5 
 Prefix order:  / 14 -5 
 Evaluates to -2.8
 ------------------------
 Postfix Exp: 20 3 -4 + * 
 BXT: 
 		-4
 	+
 		3
 *
 	20
 Infix order:  20 * 3 + -4 
 Prefix order:  * 20 + 3 -4 
 Evaluates to -20.0
 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 BXT: 
 		5
 	-
 		4
 *
 		5
 	/
 			3
 		+
 			2
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5 
 Evaluates to -1.0
 ------------------------
 */



