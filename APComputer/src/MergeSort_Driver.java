 //name: Carol Zhang    date: October 31, 2017
import java.util.*;
import java.io.*;
public class MergeSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //double[] array = {1,1,3,4,2,5,6,9};    //example array from the MergeSort handout
   
      int n = (int)(Math.random()*50+10);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();
         	
      MergeSort.sort(array);
   
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("oops!");
   }
   public static void print(double[] a)   
   {
      for(double number : a)    //doing something to each element
         System.out.print(number+" ");
      System.out.println();
   }
   public static boolean isAscending(double[] a)
   {
      for(int x = 0; x < a.length - 1; x ++)
         if(a[x] > a[x + 1])
            return false;
      return true;
   }
}
/////////////////////////////////////////////
// 15 Oct 07
// MergeSort Code Handout
// from Lambert & Osborne, p. 482 - 485

class MergeSort
{
   private static final int CUTOFF = 10; // for small lists, recursion isn't worth it
   public static void sort(double[] array)
   { 
      double[] copyBuffer = new double[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }
   /*  array			array that is being sorted
       copyBuffer		temp space needed during the merge process
       low, high		bounds of subarray
       middle			midpoint of subarray   */
   private static void mergeSortHelper(double[] array, double[] copyBuffer,
                                                      int low, int high)
   {  
      // if ( high - low  < CUTOFF )                  //switch to selection sort  when
         // SelectionLowHigh.sort(array, low, high);        //the list gets small enough 
      // else
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   */
   public static void merge(double[] array, double[] copyBuffer,
                                   int low, int middle, int high)
   
   {
      // Initialize i1 and i2 to the first items in each subarray  
      // Interleave items from the subarrays into the copyBuffer in such 
      // a way that order is maintained.
      
      int length = high - low;
                     
      int originalLow = low;
      int originalMiddle = middle;
      int copyBufferIndex = low;
      
      /*if(low == middle)
      {
         middle ++;
         originalMiddle ++;
      }*/
      
      double i1 = array[low];
      double i2 = array[middle];
      
      boolean isAscending = false;
      while(copyBufferIndex <= originalLow + length)
      {
         if(low < originalMiddle)
            i1 = array[low];
         if(middle < high)
            i2 = array[middle];
         if(low == originalMiddle)
         { 
            while(middle <= high)
            {
               copyBuffer[copyBufferIndex] = i2;
               middle ++;
            }
         }
         else if(middle == high)
         {
            while(low < originalMiddle)
            {  
               copyBuffer[copyBufferIndex] = i1;
               low ++;
            }
         }
         else if(i1 >= i2)
         {
            copyBuffer[copyBufferIndex] = i2;
            middle ++;
         }
         else if(i2 > i1)
         {
            copyBuffer[copyBufferIndex] = i1; 
            low ++;
         }
         copyBufferIndex ++;
         /*for(int x = low; x < low + length - 1; x ++)
         {
            if(copyBuffer[x] > copyBuffer[x + 1])
               isAscending = false;
            else if(x == low + length - 2)
               isAscending = true;
         }*/
         
      }
      //then copy the just-sorted values from the copyBuffer
      //back to the array.
   
      for(int x = originalLow; x < originalLow + length; x ++)
      {
         array[x] = copyBuffer[x];
      }
   }
}

/***************************************************
name: Carol Zhang   date: October 31, 2017
***********************************************/
class SelectionLowHigh
{
   public static void sort(double[] array, int low, int high)
   {  
   }
   private static int findMax(double[] array, int low, int upper)
   {
      return -1;
   }
   private static void swap(double[] array, int a, int b)
   {
   
   } 
}
