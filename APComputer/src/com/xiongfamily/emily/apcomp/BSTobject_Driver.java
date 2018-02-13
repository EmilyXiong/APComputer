package com.xiongfamily.emily.apcomp;

 //name:Emily Xiong    date:  2/26/17
import java.util.*;
import java.io.*;

public class BSTobject_Driver
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberWidgets = 10;
   public static void main( String[] args ) 
   {
   //day one 
      tree = new BSTobject<String>();
      tree = build(tree, "T");
      System.out.print(tree);
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty());
      
   		
   	//day two
   	//	Your assignment the second day is to finish the BSTobject class.  
   	//	Specifically, prompt the user for a string, put the characters into a BST, 
   	//	call toString on this tree, and print the size of the tree.
   		Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();
      tree = build( tree, s );
      System.out.print(tree.toString());
      
      //day two, Widgets			
   	//	Next, fill your BST with 57 Widget objects from widgets.txt.  Display the tree. 
   	//	Then prompt the user to enter pounds and ounces.  If the tree contains that 
   	//	Widget, delete it, of course restoring the BST order. Display the new tree 
   	// and its size. If the tree does not contain that Widget, print "Not found".
      
      treeOfWidgets = new BSTobject<Widget>();
      treeOfWidgets = build(treeOfWidgets, new File("widget.txt"));
      System.out.println(treeOfWidgets);
      System.out.println("Tree Hight is: " + treeOfWidgets.height());
      System.out.print("Input pounds: ");
      String day2 = sc.nextLine();
      int x = Integer.parseInt(day2);
      System.out.print("Input ounces: ");
      String m = sc.nextLine();
      int y = Integer.parseInt(m);
      Widget targ = new Widget(x, y);
      if(treeOfWidgets.contains(targ))
      {
         treeOfWidgets.delete(targ);
         System.out.println("");
         System.out.println(treeOfWidgets);
         System.out.println("Size: " + treeOfWidgets.size());

      }
      else
      {
         System.out.println("Not Found.");
      }
      
      //day three -- AVL tree  -----------------------
      System.out.println("Day three test .....");
      tree = new BSTobject<String>();
      System.out.print("Input string: ");
      String day3 = sc.nextLine();
      tree = build( tree, day3 );      

   }
  // build the tree for Strings, Day 1
   public static BSTobject<String> build(BSTobject<String> tr, String str)
   {
     /* your code goes here */
     for(int k = 0; k < str.length(); k++)
     {
         tr.add("" + str.charAt(k));
         System.out.println("======================================");
         System.out.println(tr.toString());
     }
      return tr;
   
   }
   //build the tree for Widgets, Day 2
   public static BSTobject<Widget> build(BSTobject<Widget> tree, File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
         System.exit(0);
      }
      for(int i = 0; i < 10; i++)   
      {
         int a = Integer.parseInt(infile.next());
         int b = Integer.parseInt(infile.next());
         Widget w = new Widget(a, b);
         tree.add(w);
      }
      return tree;
   }
}

//////////////////////////////////
interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );     //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );  //returns the object, not a Node<E>
   public int size();
   public String toString();
}
//////////////////////////////////

class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
    // 2 fields 
    private Node<E> root;
    private int size;   
    // 1 default constructor
    public BSTobject()
    {
       root = null;
       size = 0;
    }    
   //instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }
    //recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
      if(t == null)
         return new Node<E>(obj);
      if(obj.compareTo(t.getValue()) <= 0)
      {
         t.setLeft(add(t.getLeft(), obj));
         if(height(t.getLeft()) - height(t.getRight()) == 2)
         {
        	 if(obj.compareTo(t.getLeft().getValue()) < 0)
        	 {
        		 t = rotateWithLeftChild(t);
        	 }
        	 else{
        		 t = doubleWithLeftChild(t);
        	 }
         }   
      }
      if(obj.compareTo(t.getValue()) > 0)
      {
         t.setRight(add(t.getRight(), obj));
         if(height(t.getRight()) - height(t.getLeft()) == 2)
         {
        	 if(obj.compareTo(t.getRight().getValue()) > 0)
        	 {
        		 t = rotateWithRightChild(t);
        	 }
        	 else{
        		 t = doubleWithRightChild(t);
        	 }
         }   
      }
      return t; 
   }
   public boolean contains(E v)
   {
      return contains(root, v);
   }
   public boolean contains(Node<E> t, E x)
   {
         if(t == null)
            return false;
         if(x.compareTo(t.getValue()) == 0)
            return true;
         if(x.compareTo(t.getValue()) < 0)
            return contains(t.getLeft(), x);
         if(x.compareTo(t.getValue()) > 0)
            return contains(t.getRight(), x);
         return true;
   }
   public boolean isEmpty()
   {
      if(size == 0)
         return true;
      return false;
   }
   public int size()
   {
      return size;
   }
   public E delete( E obj)
   {
      delete(root, obj);
      size--;
      return obj;
   }
   public Node<E> delete(Node<E> current, E target)
   {
      Node<E> rt = current;
      Node<E> parent = null;
      while(current !=null)
      {
        int compare = target.compareTo(current.getValue());
        // ------->  your code goes here
        if(compare < 0)
        {
           if(current.getLeft() != null)
           {
               current.setLeft(delete(current.getLeft(), target));
               return current;
           }
        }
        else if(compare > 0)
        {
           if(current.getRight() != null)
           {
               current.setRight(delete(current.getRight(), target));
               return current;
           }         
        }
        else if(compare == 0)
        {
           if(current.getLeft() == null && current.getRight() == null)
           {
              return null;
           } 
           if(current.getLeft() == null && current.getRight() != null)
           {
              return current.getRight();
           }
           if(current.getLeft() != null && current.getRight() == null)
           {
              return current.getLeft();
           } 
           if(current.getLeft() != null && current.getRight() != null)
           {
                  Node<E> temp = current;
                  Node<E> max = max(temp.getLeft());
                  current.setValue(max.getValue());
                  current.setLeft(delete(current.getLeft(), max.getValue()));
                  return current;                              
           } 
        }
       
      }
      return rt;  //never reached
   }
   public Node<E> max(Node<E> t)
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
      {
         return t;
      }
      return max(t.getRight());
   }
   public String toString()
   {
      return display(root, 0);
   }
   public String display(Node<E> t, int level)
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
   
   public int height()
   {
	   return height(root);
   }
   public int height(Node<E> t)
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
   
   public Node<E> rotateWithRightChild(Node<E> t)
   {
	   //this is a left rotation
	   if (t == null || t.getRight() == null)
		   return t;
	   
	   Node<E> right = t.getRight();
	   
	   t.setRight(right.getLeft());
	   right.setLeft(t);
	   
	   return right;
	   
   }
   
   public Node<E> rotateWithLeftChild(Node<E> t)
   {
	   //this is a right rotation
	   if (t == null || t.getLeft() == null)
		   return t;
	   
	   Node<E> left = t.getLeft();
	   
	   t.setLeft(left.getRight());
	   left.setRight(t);
	   return left;
   }
   
   public Node<E> doubleWithRightChild(Node<E> t)
   {
	   if(t == null || t.getRight() == null || t.getRight().getLeft() == null)
		   return t;
	   
	   Node<E> right = t.getRight();
	   
	   t.setRight(rotateWithLeftChild(right));
	   
	   return rotateWithRightChild(t);
   }
   
   public Node<E> doubleWithLeftChild(Node<E> t)
   {
	   if(t == null || t.getLeft() == null || t.getLeft().getRight() == null)
		   return t;
	   
	   Node<E> left = t.getLeft();
	   t.setLeft(rotateWithRightChild(left));
	   return rotateWithLeftChild(t);
   }
   
   private void rebalance(){
	   
	   
	   
   }
   
   private void rebalance(Node<E> t){
	   
	   
   }
}
   /* implement the interface here.  Use TreeNode as an example,
    but root is a field. You need add, contains, isEmpty, 
    delete, size, and toString.  */



    
    /***************************private inner class **************/  
   class Node<E>
   {
      // 3 fields 
      private E value;
      private Node<E> left;
      private Node<E> right;   
      
      // 2 constructors, one-arg and three-arg
      public Node(E v)
      {
         this(null, v, null);
      }   
      public Node(Node<E> lt, E v, Node<E> rt)
      {   
         left = lt;
         value = v;
         right = rt;  
      }       
      //methods--Use TreeNode as an example. See the cheat sheet.
      public E getValue()
      {
         return value;
      }
      public Node<E> getLeft()
      {
         return left;
      }
      public Node<E> getRight()
      {
         return right;
      }
      public void setValue(E v)
      {
         value = v;
      }
      public void setLeft(Node<E> lt)
      {
         left = lt;
      }
      public void setRight(Node<E> rt)
      {
         right = rt;
      }
      public String toString()
      {
         return value.toString();
      }
   
   }
