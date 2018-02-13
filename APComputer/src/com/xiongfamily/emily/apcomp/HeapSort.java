package com.xiongfamily.emily.apcomp;

//Name:     Date:

public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
   //Part 1: Given a heap, sort it. Do this part first. 
      SIZE = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      display(heap);
      sort(heap);
      display(heap);
      
   //Part 2:  Generate 100 random numbers, make a heap, sort it.
      // SIZE = 100;
      // double[] heap = new double[SIZE + 1];
      // heap = createRandom(heap);
      // display(heap);
      // makeHeap(heap, SIZE);
      // display(heap); 
      // sort(heap);
      // display(heap);
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   public static void sort(double[] array)
   {
      /* enter your code here */
      for(int i = 0; i < array.length-1; i++)
      {
    	  swap(array, 1, array.length-1-i);
    	  makeHeap(array, array.length-2-i);
      }
      
   
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
   }
   public static void swap(double[] array, int a, int b)
   {
	   double temp = array[a];
      array[a] = array[b];
      array[b] = temp; 
   }
   public static void heapDown(double[] array, int k, int size)
   {
      int maxChild = 2*k ;
      if(2*k > size)
      {
         return;
      }
      else if((2*k + 1) <= size)
      {
         if(array[2*k] > array[2*k + 1])
         {
            maxChild = 2*k;
         }         
         else
         {
            maxChild = 2*k+1;
         }
      }
      if(array[k] < array[maxChild])
      {
         swap(array, k, maxChild);
      }
   }
   
   // ****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
//   public static double[] createRandom(double[] array)
//   {  
//      array[0] = -1;   //because it will become a heap
//      
//   }
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
	   for(int i=size/2; i>0; i--){
		   heapDown(array, i, size);
	   }
   }
}

