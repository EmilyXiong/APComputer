package com.xiongfamily.emily.apcomp;

 //Name:      Date:
 // use for-each loops or iterators, not regular for-loops
   import java.io.*;
   import java.util.*;
    public class IteratorLab
   {
       public static void main(String[] args)
      {
         System.out.println("Iterator Lab\n");
         int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
         for(int n : rawNumbers )
            System.out.print(n + " ");    
         ArrayList<Integer> numbers = createNumbers(rawNumbers);
         System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
         System.out.println("Count negative numbers: " + countNeg(numbers));
         System.out.println("Average: " + average(numbers));
         System.out.println("Replace negative numbers: " + replaceNeg(numbers));
         System.out.println("Delete zeros: " + deleteZero(numbers));
         String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
         ArrayList<String> movies = createMovies(rawMovies);
         System.out.println("Movies: " + movies);
         System.out.println("Movies: " +  removeDupes(movies));
      }
      // pre: an array of just int values 
   	// post: return an ArrayList containing all the values
       public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
      {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for(int a: rawNumbers)
        {
            nums.add(a);
        }
        return nums;
      }
      // pre: an array of just Strings  
   	// post: return an ArrayList containing all the Strings
       public static ArrayList<String> createMovies(String[] rawWords) 
      {
        ArrayList<String> words = new ArrayList<String>();
        for(String a: rawWords)
        {
            words.add(a);
        }
        return words;
 
      }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the number of negative values in the ArrayList a
       public static int countNeg(ArrayList<Integer> a)
      {
        int negs = 0;
        for(Integer n: a)
        {
            if(n < 0)
            {
               negs++;
            }
        } 
        return negs;
      }
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the average of all values in the ArrayList a
       public static double average(ArrayList<Integer> a)
      {
        double sum = 0;
        double num = 0;
        for(Integer n: a)
        {
          sum += n;
          num++;
        } 
        return sum/num;
  
      }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: replaces all negative values with 0 
       public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
      {
         ListIterator<Integer> it = a.listIterator();  
         while(it.hasNext())
         {
        	 Integer num = it.next();
        	 if(num < 0)
             {
                it.set(0);
             }    
         }
           
         return a;
      }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: deletes all zeros in the ArrayList a
      public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
      {
    	  ListIterator<Integer> it = a.listIterator();  
    	  while(it.hasNext())
          {
         	 Integer num = it.next();
         	 if(num == 0)
              {
                 it.remove();
              }    
          }
    	  return a;
      }
      
      // pre: ArrayList a is not empty and contains only String objects
   	// post: return ArrayList without duplicate movie titles
		// strategy: start with an empty array and add movies as needed
       public static ArrayList<String> removeDupes(ArrayList<String> a)
      {
    	   ArrayList<String> newMovies = new ArrayList<String>();
    	   boolean needAdd = true;
    	   for (String movie: a)
    	   {
    		   for (String addedMovie: newMovies)
    		   {
    			   if (movie.compareTo(addedMovie) == 0)
    			   {
    				   needAdd = false;
    				   break;
    			   }
    		   }
    		   if (needAdd)
    		   {
    			   newMovies.add(movie);
    		   }
    		   else{
    			   needAdd = true;
    		   }
    		   
    	   }
    	   return newMovies;
      }
       
   }

