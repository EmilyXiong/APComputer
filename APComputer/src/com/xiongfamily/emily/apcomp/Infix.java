package com.xiongfamily.emily.apcomp;

  //name:   date: 
import java.util.*;
public class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  -->  Postfix  -->  Evaluate");
      /*enter code here  */
      List<String> infixExp = new ArrayList<String>();
       
      infixExp.add("3+4*5");
      infixExp.add("3*4+5");
      infixExp.add("(4+5)-5*3");
      infixExp.add("(3+4)*(5+6)");
      infixExp.add("(3*(4+5)-2)/5");
      infixExp.add("8+1*2-9/3");
      infixExp.add("3*4+5");
      infixExp.add("(4+5)-5*3");
      infixExp.add("(3+4)*(5+6)");
      infixExp.add("(3*(4+5)-2)/5");
      infixExp.add("8+1*2-9/3");
      infixExp.add("(3*(3+(4+5*3)*5)+3*2)");
      
      for( String s : infixExp )
      {
         String pf = infixToPostfix(s);
         System.out.println(s + "       " + pf + "       " + Postfix.eval(pf));
      }
   }
   public static String infixToPostfix(String infix)
   {
	   String result = "";
	   Stack<String> st = new Stack<>();
	   
	   for (char thisChar: infix.toCharArray()){
		   if(isOperator(thisChar)){
			   if(st.isEmpty()){
				   st.push("" + thisChar);
			   }
			   else{
				   if(st.peek().compareTo("(") == 0){
					   st.push("" + thisChar);
				   }
				   else if(isLower(thisChar, st.peek().charAt(0))){
					   result = result + st.pop();
					   st.push("" + thisChar);
				   }
				   else{
					   st.push("" + thisChar);
				   }
				   
			   }
		   }
		   else if( thisChar == '(' ){
			   st.push("" + thisChar);
		   }
		   else if(thisChar == ')'){
			   while(!st.isEmpty()){
				   if (st.peek().compareTo("(") == 0){
					   st.pop();
					   break;
				   }
				   else{
					   result = result + st.pop();
				   }
			   }
		   }
		   else{
			   result = result + thisChar;
		   }
		   
	   }
	   while(!st.isEmpty()){
			   result = result + st.pop();
	   }
	   return result;
	   
   }
	//returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
	   if((c1=='+' || c1=='-') && (c2=='*' || c2=='/')){
		   return true;
	   }
	   return false;
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
 Infix  -->  Postfix  -->  Evaluate
 3+4*5       345*+       23
 3*4+5       34*5+       17
 (4+5)-5*3       45+53*-       -6
 (3+4)*(5+6)       34+56+*       77
 (3*(4+5)-2)/5       345+*2-5/       5
 8+1*2-9/3       812*+93/-       7
	*/
