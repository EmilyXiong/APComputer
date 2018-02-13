// name: Emily Xiong   date:11/29/16

package com.xiongfamily.emily.apcomp;

import java.util.Hashtable;

public class DLL_Driver
{
   public static void main(String args[])
   {
	   
	   Hashtable beneficiaryData = null;
	   beneficiaryData = new Hashtable();
	   
	   beneficiaryData.put("kdhsl", null);
	   
      DLL list = new DLL();	
   
      list.addLast("Apple");
      list.addLast("Banana");
      list.addLast("Cucumber");
      list.add("Durian");
      list.add("Eggplant");
      
      System.out.println("The list is " + list);
      System.out.println("Size: " + list.size());
      Object obj = list.remove(2);
      System.out.println("Remove index 2: "+ obj);
      System.out.println("The list is " + list);
      System.out.println("Size: " + list.size());
   
      list.add(2,"Carrot");
      System.out.println("Add Carrot at index 2:   " + list);
      
      try
      {
         list.add(16,"Kiwi");    //out-of-bounds
      }
      catch(IndexOutOfBoundsException e)
      {
         System.out.println(e);
      }
       
       
      System.out.println("Get values at index 0 and First: " + list.get(0)+" and " + list.getFirst());
      System.out.println("No change in list: " +list);
         
      list.removeFirst();
      System.out.println( "Remove the First:  " + list); 
       
      list.addFirst("Artichoke");
      System.out.println("Add First:  " + list);
      System.out.println("Size: " + list.size());
       
      list.set(1, "Broccoli");
      System.out.println("Set value at index 1:  " + list);
   }
}

//////////////////////////////////////////////////////////
    
class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
	   DLNode temp = head;
	   size = 0;
	   while(temp != head.getPrev()){
		   temp = temp.getNext();
		   size++;
	   }
	   return size;
   }
   
  /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 
   	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

      /* enter your code below  */
	   DLNode temp = head;
	   for(int i = 0; i < index; i++) {
		   temp = temp.getNext();
	   }
      
      DLNode newNode = new DLNode (obj, temp, temp.getNext());
      temp.getNext().setPrev(newNode);
      temp.setNext(newNode);
      size++;
   }
   
   /* return obj at position index.  
   	*/
   public Object get(int index)
   {
	   if(index > size-1 || index < 0)
	         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	   
	   DLNode temp = head.getNext();
	   for(int i = 0; i < index; i++){
		   temp = temp.getNext();
	   }
	   return temp.getValue();      	
   }
   
   /* replaces obj at position index.  
   		*/
   public void set(int index, Object obj)
   {
	   if(index > size-1 || index < 0)
	         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	   
	   DLNode temp = head.getNext();
	   for(int i = 0; i < index; i++){
		   temp = temp.getNext();
	   }
	   temp.setValue(obj);
   }
   
   /*  removes the node from position index.  decrements size.
   	  @return the object at position index.
   	 */
   public Object remove(int index)
   {
	   if(index > size-1 || index < 0)
	         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

	   DLNode temp = head;
	   for(int i = 0; i <= index; i++){
		   temp = temp.getNext();
	   }
	   temp.getPrev().setNext(temp.getNext());
	   temp.getNext().setPrev(temp.getPrev());
	   size--;
	   return temp;
   }
   
  /* inserts obj at front of list; increases size;
     */
   public void addFirst(Object obj)
   {
      DLNode front = new DLNode(obj, head, head.getNext());
      head.getNext().setPrev(front);
      head.setNext(front);
      size++;   
   }
   
   /* appends obj to end of list; increases size;
       */
   public void addLast(Object obj)
   {
      DLNode back = new DLNode(obj, head.getPrev(), head);
      head.getPrev().setNext(back);
      head.setPrev(back);
      size++;
   }
   
   public Object getFirst()
   {
	   return head.getNext().getValue();
   }
   
   public Object getLast()
   {
	   return head.getPrev().getValue();
   }
   
   public Object removeFirst()
   {
	   DLNode first = head.getNext();
	   head.setNext(first.getNext());
	   first.getNext().setPrev(head);
	   size--;
	   return first;
   }
   
   public Object removeLast()
   {
	   DLNode last = head.getPrev();
	   head.setPrev(last.getPrev());
	   last.setNext(head);
	   size--;
	   return last;
   }
   
   public String toString()
   {
	   String strValue = "[";
	   DLNode temp = head;
	   for (int i = 1; i < size; i++){
		   temp = temp.getNext();
		   strValue = strValue + (String) temp.getValue() + ", ";
	   }
	   strValue = strValue + (String) temp.getNext().getValue() + "]";
	   return strValue;
   }
}
	
//////////////////////////////////////

class DLNode 
{
   private Object value;
   private DLNode prev;
   private DLNode next;
   public DLNode(Object arg, DLNode p, DLNode n)
   {
      value=arg;
      prev=p;
      next=n;
   }
   public DLNode()
   {
      value=null;
      next=this;
      prev=this;
   }
   public void setValue(Object arg)
   {
      value=arg;
   }
   public void setNext(DLNode arg)
   {
      next=arg;
   }
   public void setPrev(DLNode arg)
   {
      prev=arg;
   }
   public DLNode getNext()
   {
      return next;
   }
   public DLNode getPrev()
   {
      return prev;
   }
   public Object getValue()
   {
      return value;
   }
}
