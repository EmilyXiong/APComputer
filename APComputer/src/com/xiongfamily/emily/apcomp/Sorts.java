package com.xiongfamily.emily.apcomp;

    /* M.L. Billington, 10/02/2006.
    Uses the helper classes Selection and Insertion. 
	 Students are to write the Selection and Insertion classes.
    */
import java.util.*;
import java.io.*;
public class Sorts
{
   public static void main(String[] args) throws Exception
   {
        //Part 1, for doubles
//      int n = (int)(Math.random()*15);
//      double[] array = new double[n];
//      for(int k = 0; k < array.length; k++)
//         array[k] = Math.random();	
//      print(array);
//      System.out.println("*************  *************");
//      //array = Selection.sort(array);
//      array = Insertion.sort(array);
//      print(array);
         
      	//Part 2, for Strings
      int size = 20;
      Scanner sc = new Scanner(new File("declaration.txt"));
      
      //This is for string
      String[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
      print(arrayStr);
      System.out.println("*************  *************");
      //arrayStr = Selection.sort(arrayStr);
      arrayStr = Insertion.sort(arrayStr);
      print(arrayStr);
      
      //This is for comparable
//      Comparable<String>[] arrayStr = new String[size];
//      for(int k = 0; k < arrayStr.length; k++)
//         arrayStr[k] = sc.next();	
//      sc.close();
//      print(arrayStr);
//      System.out.println("*************  *************");
//      //arrayStr = Selection.sort(arrayStr);
//      arrayStr = Insertion.sort(arrayStr);
//      print(arrayStr);
   }
   public static void print(double[] a)
   {
      // for(int k = 0; k < a.length; k++)    //old style
      //       System.out.println(a[k]);
      for(double d : a)                      // for-each loop     
         System.out.println(d);
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object item : papaya)     //for-each
         System.out.println( item );
   }
}
   //*******************************************************************
  //Name:              Date:
  //The Selection class will have methods sort(), findMax() and swap().
  //Three versions of each method will have to be written, to work 
  //for doubles, Strings, and Comparables.

class Selection
{
   public static double[] sort(double[] array)
   {
      for(int x = 1; x < array.length; x++)
      {
    	  int end  = array.length-1-x;
    	  int maxPos = findMax(array, end);
    	  swap(array,maxPos, end);
      } 
      return array;
   }
   
   private static int findMax(double[] array, int n)
   {
      int maxPos = 0;
      
      for(int x = 1; x <= n; x++)
      {
         if(array[maxPos]< array[x])
         {
        	 maxPos = x;
         }
      }
      return maxPos;
   }
   
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   
   	/***************************************************
   	  for Strings
   	  ***********************************************/
   public static String[] sort(String[] array)
   {
	  String a ;
 	  String b ;
      for(int x = 0; x < array.length; x++)
      {
    	  int end  = array.length-1-x;
    	  int maxPos = findMax(array, end);
    	  a = array[maxPos];
    	  b = array[end];
    	  swap(array,maxPos, end); 
    	  a = array[maxPos];
    	  b = array[end];
      } 
      return array;
      
   }
   
   public static int findMax(String[] array, int upper)
   {
      int maxPos = 0;
      for(int x = 1; x <= upper; x++)
      { 
         if( array[maxPos].compareToIgnoreCase(array[x]) < 0 )
         {
        	 maxPos = x;
         }
      }
      return maxPos;
   }
   
   public static void swap(String[] array, int a, int b)
   {
      String temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   	/***************************************************
   	 for Comparables,
   	      Swap() is for Objects.
   	      make sure that print() is for Objects, too.
   	  ***********************************************/
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static Comparable[] sort(Comparable[] array)
   {
	      for(int x = 0; x < array.length; x++)
	      {
	    	  int end  = array.length-1-x;
	    	  int maxPos = findMax(array, end);
	    	  swap(array,maxPos, end); 
	      } 
	      return array;
   }
   
   @SuppressWarnings("unchecked")
   public static int findMax(Comparable[] array, int upper)
   {
	      int maxPos = 0;
	      for(int x = 1; x <= upper; x++)
	      { 
	         if( array[maxPos].compareTo(array[x]) < 0 )
	         {
	        	 maxPos = x;
	         }
	      }
	      return maxPos;
   }
   
   public static void swap(Object[] array, int a, int b)
   {
	   Object temp = array[a];
	   array[a] = array[b];
	   array[b] = temp;  
   }
}   

//**********************************************************
  //Name:              Date:
  //The Insertion class 
  //write enough methods to handle doubles and Comparables.
class Insertion
{
   public static double[] sort(double[] array)
   { 
	   for (int i = 1; i < array.length; i++) {
		   shift(array, i, array[i]);
		}
	   return array;
   }
   
   private static void shift(double[] array, int index, double value)
   {
		int j = index;
		while (j > 0 && array[j - 1] > value) {
			array[j] = array[j - 1];
			j--;
		}
		array[j] = value;	   
   }
   
   
  	//***************************************************
	// String version
	//***************************************************
   public static String[] sort(String[] array)
   { 
	   for (int i = 1; i < array.length; i++) {
		   shift(array, i, array[i]);
		}
	   return array;
   }
   
   private static void shift(String[] array, int index, String value)
   {
		int j = index;
		while (j > 0 && array[j - 1].compareToIgnoreCase(value) > 0) {
			array[j] = array[j - 1];
			j--;
		}
		array[j] = value;	   
   }
   
   @SuppressWarnings("unchecked")
    public static Comparable[] sort(Comparable[] array)
   { 
	   for (int i = 1; i < array.length; i++) {
		   shift(array, i, array[i]);
		}
	   return array;
	   
   }
   @SuppressWarnings("unchecked")
    private static void shift(Comparable[] array, int index, Comparable value)
   {
		int j = index;
		while (j > 0 && array[j - 1].compareTo(value) > 0) {
			array[j] = array[j - 1];
			j--;
		}
		array[j] = value;	
   }

}
