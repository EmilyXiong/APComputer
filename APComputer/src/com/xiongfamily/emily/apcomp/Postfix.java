package com.xiongfamily.emily.apcomp;

//name:     date:
   
import java.util.*;
public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postExp = new ArrayList<String>();
      /*  enter tests here  */
      postExp.add("345*+");
      postExp.add("34*5+");
      postExp.add("45+53*-");
      postExp.add("34+56+*");
      postExp.add("345+*2-5/");
      postExp.add("812*+93/-");
      postExp.add("345+*2-5/");
      postExp.add("23+5/45-*");

      

      for( String pf : postExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   public static int eval(String postfix)
   {
      int a;
      int b;
      Stack<String> numbers = new Stack<>();
      
      for(int i = 0; i < postfix.length(); i++)
      {
    	  
         if(!isOperator(postfix.charAt(i)))
         {
        	 numbers.push("" + postfix.charAt(i));
         }
         else{
        	 b = Integer.parseInt("" + numbers.pop());
        	 a = Integer.parseInt("" + numbers.pop());
        	 numbers.push("" + eval(a, b, postfix.charAt(i)));
         }
      }
      return Integer.parseInt("" + numbers.pop());
   }
   
   public static int eval(int a, int b, char ch)
   {
      int result = 0;
      
	   switch (ch) {
       case '+':
    	   result = a+b;
           break;
       case '-':
    	   result = a-b;
           break;
       case '*':
    	   result = a*b;
           break;
       case '/':
    	   result = a/b;
           break;
       default:
           System.out.println("Invalid operation!");
	   }
	   
	   return result;
   }
   public static boolean isOperator(char ch)
   {
      String operators = "+-/*";
      for(int i = 0; i < operators.length(); i++)
      {
         if(ch == operators.charAt(i))
         {
            return true;
         }
      }
      return false;
   }
}

/*
 Postfix  -->  Evaluate
 345*+		23
 34*5+		17
 45+53*-		-6
 34+56+*		77
 345+*2-5/		5
 812*+93/-		7  
 */