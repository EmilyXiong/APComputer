// name:     date:  
package com.xiongfamily.emily.apcomp;

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Josephus
{
   private static String WINNER = "Josephus";
   public static void main(String[] args) throws FileNotFoundException
   {
      /* run it first with J_numbers.txt  */
      ListNode p = null;
      int n = Integer.parseInt(JOptionPane.showInputDialog("How many names (2-20)?"));
      File f = new File("J_numbers.txt");
      p = readNLinesOfFile(n, f);
      int countOff = Integer.parseInt(JOptionPane.showInputDialog("How many names to count off each time?"));
      countingOff(p, countOff, n);
      
   	/* run it next with J_names.txt  * */
      System.out.println("\n ****  Now start all over.  Enter the winning position in the JOptionPane.  *** \n");
      p = readNLinesOfFile(n, new File("J_names.txt"));
      int winPos = Integer.parseInt(JOptionPane.showInputDialog("Enter Josephus's preferred position."));
      replaceAt(p, WINNER, winPos);
      countingOff(p, countOff, n);
      System.out.println(WINNER + " wins!");   
   }
   
   
   /* reads the names, builds the linked list.
	  */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner infile = new Scanner(f);
      ListNode head = null;
      ListNode prev = null;
      ListNode current = null;
      for(int i = 0; i < n; i++)
      {
    	  if (head == null){
    		  head = new ListNode( infile.next(), null);
    		  prev = head;
    	  }
    	  else{
    		  current = new ListNode( infile.next(), head);
    		  prev.setNext(current);
    		  prev = current;
    		  
    	  }
      }
      return head;
   }
   
  /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
     Ends with one remaining name, who is the winner. 
	  */
   public static void countingOff(ListNode p, int count, int n)
   {
	   print(p);
	   while(p != p.getNext()){
		   p = remove(p, count);
		   print(p);
	   }
	   //System.out.print("\nThe winer is " + p.getValue());
           
   }
   
   /* removes the node after counting off count-1 nodes.
	*/  
   private static ListNode remove(ListNode p, int count)
   {
	   ListNode prev = p;
	   ListNode current = p;
	   //if only one node
	   if( current.getNext() == p){
		   return current;
	   }
	   //find the last node which links to the head
	   while(prev.getNext() != p){
		   prev = prev.getNext();
	   }
	   
      for(int i = 1; i < count; i++)
      {
    	  prev = current;
    	  current = current.getNext();
      }
      prev.setNext(current.getNext());
      return current.getNext();
      
   }
   
   /* prints the circular linked list.
	  */
  public static void print(ListNode p)
   {
	  ListNode temp = p;
	  while (temp.getNext() != p){
		  System.out.print(temp.getValue() + " ");
		  temp = temp.getNext();
	  }
	  System.out.print(temp.getValue() + "\n");
   }
  
  
 /* helper method to build the list.  Creates the node, then
    inserts it in the circular linked list.
	 */
  /* private static ListNode insert(ListNode p, Object obj)
   {
      ListNode temp = new ListNode(obj, null);
      
   }

	/* replaces the value (the string) at the winning node.
	   */
   private static void replaceAt(ListNode p, Object obj, int pos)
   {
	   String replacedString = (String)obj;
	   ListNode temp = p;
	   for(int i = 1; i < pos; i++){
		  temp = temp.getNext();
	   }
	   temp.setValue(replacedString);
   }
}

  //the College Board's standard ListNode class
class ListNode
{
   private Object value;
   private ListNode next;
   public ListNode(Object v, ListNode n)
   {
      value=v;
      next=n;
   }
   public Object getValue()
   {
      return value;
   }
   public ListNode getNext()
   {
      return next;
   }
   public void setValue(Object newv)
   {
      value=newv;
   }
   public void setNext(ListNode newn)
   {
      next=newn;
   }
}
