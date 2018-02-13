package com.xiongfamily.emily.apcomp;

// name:     date:

import java.util.*;
public class ParenMatch
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter tests here */
      parenExp.add("5+7");
      parenExp.add("(5+7)");
      parenExp.add(")5+7(");
      parenExp.add("((5+7)*3)");
      parenExp.add("<{5+7}*>");
      parenExp.add("[(5+7)*]3");
      parenExp.add("(5+7)*3");
      parenExp.add("5+(7*3)");
     
      parenExp.add("((5+7)*3");
      parenExp.add("[(5+7]*3");
      parenExp.add("[(5+7)*3])");
      parenExp.add("([(5+7)*3");
      parenExp.add("([(5+7)*3])");

      parenExp.add("<4*4>([(5+7)*3])");

      
                  
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   public static boolean checkParen(String exp)
   {
      Stack<String> st = new Stack<String>();
      for(int i = 0; i < exp.length(); i++)
      {
         for(int c = 0; c < left.length(); c++)
         {
            if(exp.charAt(i) == left.charAt(c) && st.isEmpty())
            {       
               st.push("" + exp.charAt(i));
               break;
            } 
            else if((exp.charAt(i) == left.charAt(c) || exp.charAt(i) == right.charAt(c)) && !st.isEmpty())
            {
               if(right.indexOf(exp.charAt(i)) == left.indexOf(st.peek()))
               {
                  st.pop();
               } 
               else
               {
                  st.push("" + exp.charAt(i));
               }
               break;
            }
            else if(exp.charAt(i) == right.charAt(c) && st.isEmpty())
            {       
               return false;
            } 
            
         }      
      }
      if(st.isEmpty())
      {
         return true;
      }
      else
         return false;
      
   }
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
