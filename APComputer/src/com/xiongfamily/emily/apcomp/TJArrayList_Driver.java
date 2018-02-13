//  name:        date: 
//  implements the List interface with a backing array of Objects. 
//	 also overrides toString().

package com.xiongfamily.emily.apcomp;
   
public class TJArrayList_Driver
{  
   public static void main(String [] args)
   {
      TJArrayList myList = new TJArrayList();	
   
      myList.add("Apple");
      myList.add("Banana");
      myList.add("Fig");
      myList.add(2, "Cucumber");
      myList.add(3, "Dates");
      System.out.println(myList);
      System.out.println("Size is " + myList.size());
      try
      {
         myList.add(12, "Peach");
      }
      catch(IndexOutOfBoundsException e)
      {
         System.out.println(e);
      }
      System.out.println("Get 2: " + myList.get(2));
      System.out.print("Set at 2: ");
      myList.set(2, "Cherry");
      System.out.println(myList);
      Object obj = myList.remove(2);
      System.out.println("Removed " + obj+ " from " + myList);
      System.out.println("Size is " + myList.size());
      System.out.print("Add too many items: ");
      for(int i = 3; i <= 10; i++)
         myList.add(new Integer(i));
      System.out.println(myList);
      System.out.println("Size is " + myList.size());   	
      System.out.println("Contains \"Breadfruit\"?  " + myList.contains("Breadfruit"));
      System.out.println("Contains \"Banana\"?  " + myList.contains("Banana"));
   }
}
   
class TJArrayList
{
   private int size;							//stores the number of objects
   private Object[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
        size = 0;
        myArray = new Object[10];
   }
   public int size()
   {
        return size;  
   }
 	/* appends obj to end of list; increases size;
         must be an O(1) operation when size < array.length, 
            and O(n) when it doubles the length of the array.
	      @return true  */
   public boolean add(Object obj)
   {
        if(size < myArray.length)
        {
            myArray[size] = obj;
            size++;
        }
        else
        {
            Object[] newArray = new Object[size * 2];
            //copy existing objects to new array
            for (int i = 0; i < size; i++){
            	newArray[i] = myArray[i];
            }
            myArray = newArray;
            myArray[size] = obj;
            size++;
        }
        return true;
   }
      /* inserts obj at position index.  increments size. 
   		*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      for (int i = size -1; i >= index; i--)
      {
    	  myArray[i+1] = myArray[i];
      }
      myArray[index] = obj;
      size++;
      
   }
      /* return obj at position index.  
   		*/
   public Object get(int index) throws IndexOutOfBoundsException
   {
	   if(index >= size || index < 0)
	         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      return myArray[index];
   }
    /* replaces obj at position index.  
   		*/
   public void set(int index, Object obj) throws IndexOutOfBoundsException
   {
	   if(index >= size || index < 0)
	         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	   myArray[index] = obj;
      
   }
    /*  removes the node from position index. shifts elements 
        to the left.   Decrements size.
   	  @return the object at position index.
   	  */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
	   Object removed = null;
	   if(index > size || index < 0)
	         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	   removed = myArray[index];
	   for (int i = index; i < size -1; i ++){
		   myArray[i] = myArray[i+1];
	   }
	   size--;
	   return removed;
   }
	 /*
      this method compares objects and should use .equals(), not ==
     	*/
   public boolean contains(Object obj)
   {
      boolean found = false;
      
      for (int i = 0; i < size; i++){
    	  if (obj.equals(myArray[i])){
    		  found = true;
    		  break;
    	  }
      }
      return found;
   }
      /*returns a String of Objects in the array with square brackets and commas
        	*/
   public String toString()
   {
      String strValue = "[";
      for (int i = 0; i < size; i++){
    	  strValue = strValue + myArray[i].toString() + ", ";
      }
      strValue = strValue + "]";
      strValue = strValue.replace(", ]", "]");
      return strValue;
   }
}