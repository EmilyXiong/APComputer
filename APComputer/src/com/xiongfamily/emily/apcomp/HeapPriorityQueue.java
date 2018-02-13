package com.xiongfamily.emily.apcomp;

 //Name:Emily Xiong   Date:5/5/17
 
 //implement the API for java.util.PriorityQueue
 //test this class by using it in McRonaldPQ_working.java.
 //add(E) and remove()  must work in O(log n) time
 
import java.util.*;
public class HeapPriorityQueue<E extends  Comparable<E>> 
{
   private ArrayList<E> myHeap;
   private int heapSize;
   
   
   public static void main(String[] args)
   {
	   //Part 1: Given a heap, sort it. Do this part first. 
	   
	   HeapPriorityQueue<Integer> q = new HeapPriorityQueue<Integer>();
	  for ( int i = 0; i<=12; i++){
		  int temp = (int)(Math.random()*100) + 1;
		  q.add(temp);
		  System.out.println("" + temp + "*** " + q.toString());
	  }

	  System.out.println("\n\n  starting removing items");
	  
	  for ( int i = 0; i<=12; i++){
		  
		  System.out.println(q.remove().toString() + "*** " + q.toString());
	  }
	  
   }

   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
      heapSize = 0;
   }
   
   public void add(E obj)
   {
	   myHeap.add(obj);
	   heapSize++;
	   heapUp(heapSize, heapSize);
      
   }
   
	public E remove() {
		
		E removed = null;
		
		if(heapSize > 0){
			if(heapSize == 1){
				removed = myHeap.remove(heapSize);
			}
			else{
				removed = myHeap.get(1);
				myHeap.set(1,  myHeap.remove(heapSize));
			}
			heapSize--;
			reheap();
		}

		return removed;
	}
   
   
   public void heapUp(int k, int size)
   {
      int parentindex = k/2;
      if(parentindex == 0)
      {
         return;
      } 
      
      if(myHeap.get(k).compareTo(myHeap.get(parentindex)) < 0)
      {
         swap(k, parentindex);
         heapUp(parentindex, size);
      }
   }

   
   public void heapDown(int k, int size)
   {
      int maxChild = 2*k;
      if(2*k > size)
      {
         return;
      } 
      else if((2*k + 1) <= size)
      {
         if(myHeap.get(2*k).compareTo(myHeap.get(2*k + 1)) < 0)
         {
            maxChild = 2*k;
         }         
         else
         {
            maxChild = 2*k+1;
         }
      }
      if(myHeap.get(k).compareTo(myHeap.get(maxChild)) > 0)
      {
         swap(k, maxChild);
         heapDown(maxChild, size);
      }
      
   }
   
   public void reheap(){
	   for(int i=heapSize/2; i>0; i--){
		   heapDown(i, heapSize);
	   }
   }
   
   
   
   public void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp); 
   }
   

	public ArrayList<E> getMyHeap() {
		return myHeap;
	}
	
	public void setMyHeap(ArrayList<E> myHeap) {
		this.myHeap = myHeap;
	}
   
   
	public String toString() {
		String retString = "";
		for (int k = 1; k <= heapSize; k++)
			retString = retString + " " + myHeap.get(k).toString();
		
		return retString;
	} 
   
   
  public boolean isEmpty()
  {
     return heapSize == 0;
  }


   public E peek()
   {
	   if (heapSize > 0)
	   {
		   return myHeap.get(1);
	   }
       return null;
   }




  
}
