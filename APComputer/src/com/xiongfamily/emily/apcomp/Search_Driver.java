package com.xiongfamily.emily.apcomp;
	//name:    date:
   import java.io.*;      //the File 
   import java.util.*;    //the Scanner 
   import javax.swing.*;  //the JOptionPane
   public class Search_Driver  {
      public static void main(String[] args) throws Exception
      {
    	  String[] array = input("declaration.txt");
    	  //print the array
    	  for(String item : array)     //for-each
    	         System.out.println( item );
    	  System.out.println(" ***************");
    	  
    	  String target = "assume";
    	  System.out.println(" Linear Starch index = "  +
    	       Searches.linear(array, target) + " with compare count = " + Searches.linearCount);
    	  System.out.println(" Binary Starch index = "  +
       	       Searches.binary(array, target) + " with compare count = " + Searches.binaryCount());
    	  
      }
      
      public static String[] input(String filename) throws Exception
      {
    	  int size = 30;
          Scanner sc = new Scanner(new File(filename));
    	  String[] array = new String[size];
    	  //read
          for(int k = 0; k < array.length; k++)
             array[k] = sc.next();	
          
          
          // sort the array        
          for (int i = 1; i < array.length; i++){
        	  String value = array[i];
        	  for (int j = i; j > 0; j--){
        		  if (value.compareTo(array[j-1]) < 0){
        			array[j] = array[j-1]  ;
        			if (j == 1){
        				array[0] = value;
        			}
        		  }
        		  else{
        			  array[j] = value;
        			  break;
        		  }
        	  } 
          }
          
          sc.close();
          return array;
      }
   }
	/////////////////////////////////////////////////////////
   class Searches
   {
      public static int linearCount = 0;
      private static int binaryCount = 0;      
      public static int binaryCount()
      {
         return binaryCount;
      }
      
      public static int linear(Comparable[] array, Comparable target)
      { 
    	  for (int i = 0; i < array.length; i++){
    		  if (array[i].compareTo(target) == 0 ){
    			  linearCount = i +1;
    			  return i;
    		  }
    	  }
    	  return -1;    	  
      }
      
      public static int binary(Comparable[] array, Comparable target)
      {
    	  binaryCount=0;
    	  return binaryhelper(array, target, 0, array.length-1);    	  
      }
      
      private static int binaryhelper(Comparable[] array, Comparable target, int start, int end)
      {
    	  if (start > end ){
    		  return -1;
    	  }
    	  else if (start == end){
    		  if (target.compareTo(array[start]) == 0){
    			  return start;
    		  }
    		  else{
    			  return -1;
    		  }
    		  
    	  }
    	  else{
    		  int mid = (end - start) /2;
    		  int compValue = target.compareTo(array[mid]);
    		  binaryCount++;
    		  if (compValue == 0){
    			  return mid;
    		  }
    		  else if (compValue > 0){
    			  return binaryhelper(array, target, mid + 1, end);
    		  }
    		  else{
    			  return binaryhelper(array, target, start, end-1);
    		  }
    	  }
    	  
      }
   }