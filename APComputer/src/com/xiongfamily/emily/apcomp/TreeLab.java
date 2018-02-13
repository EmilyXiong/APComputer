package com.xiongfamily.emily.apcomp;

//name:    date:  
import java.util.*;         //for the queue interface
public class TreeLab
{
   public static TreeNode root = null;
  // public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   public static String s = "XAComputerScienceTreeHasItsRootAtTheTop"; 
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display(root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Grandparents = " + countGrands(root));
      System.out.println("Only childs = " + countOnlys(root));	
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Width = " + width(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
   
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
  
   private static String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";  //preorder visit
      toReturn += preorderTraverse(t.getLeft());         //recurse left
      toReturn += preorderTraverse(t.getRight());        //recurse right
      return toReturn;
   }
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
      {
         return "";
      }  
      toReturn += inorderTraverse(t.getLeft());         //recurse left  
      toReturn += t.getValue() + " ";                    //inorder visit
      toReturn += inorderTraverse(t.getRight());        //recurse right
      return toReturn;
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
      {
         return "";
      }  
      toReturn += postorderTraverse(t.getLeft());         //recurse left  
      toReturn += postorderTraverse(t.getRight());        //recurse right
      toReturn += t.getValue() + " ";                    //postorder visit
      return toReturn;

   }
   
   public static int countNodes(TreeNode t)
   {
      if(t == null)
         return 0;
      return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
   }
   
   public static int countLeaves(TreeNode t)
   {
      if(t == null)
         return 0;
      if(t.getRight() == null && t.getLeft() == null)
         return 1;
      return countLeaves(t.getLeft()) + countLeaves(t.getRight());

   }
   public static int countGrands(TreeNode t)
   {
      if(t == null)
         return 0;
      if(height(t) == 2)
         return 1;
      else if(height(t) < 2)
         return 0;
      return  1 + countGrands(t.getLeft()) + countGrands(t.getRight());
   }
   public static int countOnlys(TreeNode t)
   {
      int count = 0;
      if(t == null)
         return 0;
      if((t.getRight() == null && t.getLeft() != null) ||
         (t.getLeft() == null && t.getRight() != null))
         count++;
      return count + countOnlys(t.getLeft()) + countOnlys(t.getRight());
   }
   
   public static int height(TreeNode t)
   {
      if(t == null)
         return 0;
      int left = 0;
      int right = 0;
      if(t.getLeft() != null)
         left = 1 + height(t.getLeft());
      if(t.getRight() != null)
         right = 1 + height(t.getRight());

      return Math.max(left, right);
   }
      /* "width" is the longest path from leaf to leaf */
   public static int width(TreeNode t)
   {
	   if(t==null) 
		   return 0;
	   
	   int width = 0, noteCount =0;
	   Queue<TreeNode> q = new LinkedList<>();
	   q.add(t);
	   while(!q.isEmpty())
	   {
		   noteCount = q.size();
		   if(noteCount> width)
		   {
			   width = noteCount;
		   }
		   while(noteCount > 0)
		   {
				TreeNode n = (TreeNode)q.remove();
				if(n.getLeft()!=null) q.add(n.getLeft());
				if(n.getRight()!=null) q.add(n.getRight());
				noteCount--;
			}
	   }
	   return width;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning about needing to cast
   public static Object min(TreeNode t)
   {
	      if(t == null)
	         return null;
	      
	      String answer = "" + t.getValue();

	      if(t.getLeft() != null){
	    	  String leftMin = (String) min(t.getLeft());
	    	  if (answer.compareTo(leftMin) > 0)
	    		  answer = leftMin;
	      }
	    	 
	      if(t.getRight() != null){
	    	  String rightMin = (String) min(t.getRight());
	    	  if (answer.compareTo(rightMin) > 0)
	    		  answer = rightMin;
	      }
	    	  
	      return answer;
   }
   
   
   @SuppressWarnings("unchecked")//this removes the warning about needing to cast
   public static Object max(TreeNode t)
   {
	      if(t == null)
	    	  return null;
	      String answer = "" + t.getValue();
	      if(t.getLeft() != null){
	    	  String leftMin = (String) max(t.getLeft());
	    	  if (answer.compareTo(leftMin) < 0)
	    		  answer = leftMin;
	      }      
	      if(t.getRight() != null){
	    	  String rightMin = (String) max(t.getRight());
	    	  if (answer.compareTo(rightMin) < 0)
	    		  answer = rightMin;
	      }    
	      return answer;          
   }
   
      /* this method is not recursive.  Use a local queue
   	to store the children of the current node.*/
   public static String displayLevelOrder(TreeNode t)
   {
	   String toReturn = "";
	   if (t == null)
		   return "";
	   
	   Queue<TreeNode> q= new LinkedList<TreeNode>();  
	   q.add(t);  
	   
	   while(!q.isEmpty())  
	   {
		   TreeNode tempNode=q.remove();
		   toReturn += tempNode.getValue();
		   if(tempNode.getLeft()!=null)
		   {
			   q.add(tempNode.getLeft()); 
		   }
		   if(tempNode.getRight()!=null)
		   {
			   q.add(tempNode.getRight());
		   }
	   }	   
	   return toReturn;
   }   
}
/***************************************************
	  
   ----jGRASP exec: java Lab01
 
			E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Grandparents = 5
 Only childs = 3

 Height of tree = 5
 Width = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC   
*******************************************************/

