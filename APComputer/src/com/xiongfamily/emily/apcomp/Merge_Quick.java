    /* 
    Calls methods in the classes Merge and QuickSort. 
	 Students are to write the Merge and QuickSort classes.
    */
package com.xiongfamily.emily.apcomp;

   import java.util.*;
   import java.io.*;
   public class Merge_Quick
   {
      public static void main(String[] args) throws Exception
      {
         int n = (int)(Math.random()*10);
         double[] array = new double[n];
         for(int k = 0; k < array.length; k++)
            array[k] = Math.random();	
         print(array);
         //MergeSort.sort(array);
         QuickSort.sort(array);
         print(array);
         if(check(array))
            System.out.println("In order!");
         else
            System.out.println("oops!");
      }
      public static void print(double[] array)
      {
         for(double theNumber : array )     //doing something to each
            System.out.println(theNumber);
         System.out.println();
      }
      public static boolean check(double[] a)
      {
    	  for (int i = 0; i< a.length-1; i++){
    		  if(a[i] > a[i+1]){
    			  return false;
    		  }
    	  }
    	  return true;
      }
   }
/////////////////////////////////////////////////
// from Lambert & Osborne, p. 482 - 485
   class MergeSort
   {
      private static final int CUTOFF = 10;  // for small lists, recursion isn't worth it
      public static void sort(double[] array)
      { 
         double[] copyBuffer = new double[array.length];
         mergeSortHelper(array, copyBuffer, 0, array.length - 1);
      }
   
      private static void mergeSortHelper(double[] array, double[] copyBuffer,
                                                             int low, int high)
      {  
         // if ( high - low  < CUTOFF )              //switch to selection sort  when
            // Selection.sort(array, low, high);     //each list gets small enough 
         // else  
         if (low < high)
         {
            int middle = (low + high) / 2;
            mergeSortHelper(array, copyBuffer, low, middle);
            mergeSortHelper(array, copyBuffer, middle + 1, high);
            merge(array, copyBuffer, low, middle, high);
         }
      }
   	
      public static void merge(double[] array, double[] copyBuffer,
                                      int low, int middle, int high)
      // array			array that is being sorted
      // copyBuffer		temp space needed during the merge process
      // low			beginning of first sorted subarray
      // middle			end of first sorted subarray
      // middle + 1		beginning of second sorted subarray
      // high			end of second sorted subarray
      {
        		/* write the merge method  */
    	  //copy the array to the copyBufer
          for(int x = 0; x < array.length; x++)
          {
        	  	copyBuffer[x] = array[x];
          }
          //pick the smaller one from both sides to the array 
          int indexLow = low;  			//point to the smallest one on the left side
          int indexHigh = middle + 1; 	//point to the smallest one on the right side
          int indexSorted = low; 		//point to the next spot of the sorted array
          
          while (indexLow <= middle && indexHigh <= high){
	        	  if (copyBuffer[indexLow] <= copyBuffer[indexHigh]){
	        		  array[indexSorted] = copyBuffer[indexLow];
	        		  indexLow++;
	        	  }
	        	  else{
	        		  array[indexSorted] = copyBuffer[indexHigh];
	        		  indexHigh++;
	        	  }
	        	  indexSorted++;
          }
          
          //copy the rest of left half if any
          while (indexLow <= middle){
	        	  array[indexSorted] = copyBuffer[indexLow];
	    		  indexLow++;
	    		  indexSorted++;
          }
        //copy the rest of right half if any
          while (indexHigh <= high){
	        	  array[indexSorted] = copyBuffer[indexHigh];
	        	  indexHigh++;
	    		  indexSorted++;
          }
      }		
   }

////////////////////////////////////////////////////
   class QuickSort
   {
      public static void sort(double[] array)
      {
    	  if (array != null && array.length > 0){
    		  quickSort(array, 0, array.length-1);
    	  }
      }
      
      private static void quickSort(double[] array, int first, int last)
      {
         int splitpt = 0;
         if(first < last)
         {
        	 String a = "kihdsakfkdsk asldkflkj";
        	 
        	 splitpt = split(array, first, last);
         }
         
         if(first < splitpt -1){
        	 quickSort(array, first, splitpt-1);
         }
        	 
         if (last > splitpt){
        	 quickSort(array, splitpt, last);
         }
         
      }
      
      private static int split(double[] info, int first, int last)
      {
    	  double pivot = info[first];
		  while(first <= last){
			  while(info[first] < pivot ){
				  first++;
			  }
			  while(info[last] > pivot){
				  last--;
			  }
			  if (first <= last) {
				  swap(info, first, last);
	                //move index to next position on both sides
	                first++;
	                last--;
	         }
		  }  
    	  return first;  
      }
      
      private static void swap(double[] array, int a, int b)
      {
    	  double temp = array[a];
    	  array[a] = array[b];
    	  array[b] = temp;
      }
   }