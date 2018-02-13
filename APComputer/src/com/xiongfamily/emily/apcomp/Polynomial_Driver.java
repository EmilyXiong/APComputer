package com.xiongfamily.emily.apcomp;

 //Name:Emily Xiong   Date:3/16/17
 //modeling a polynomial using a treeMap
import java.util.*;
public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println(poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
   	   	
      Polynomial poly2 = new Polynomial();
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1); 
      System.out.println(poly2.toString());
   	
      System.out.println(poly.add(poly2));
      System.out.println(poly.multiply(poly2));
   }
}
 interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   private Map<Integer, Integer> myPoly = null;
   
   public Polynomial(){
	   myPoly = new TreeMap<Integer, Integer>();
   }
   public Polynomial(Polynomial copy){
	   myPoly = new TreeMap<Integer, Integer>();
	   for(Integer exp: copy.myPoly.keySet()){
		   myPoly.put(exp, copy.myPoly.get(exp));
	   }
   }
   
   public void makeTerm(Integer exp, Integer coef)
   {
      myPoly.put(exp, coef);
   }
   
   public double evaluateAt(double x)
   {
      double answer = 0.0;
      for(Integer exp: myPoly.keySet())
      {
         answer += myPoly.get(exp) * Math.pow(x, exp);
      }
      return answer;
   }
   
   
   public Polynomial add(Polynomial other)
   {
      Polynomial sumPolynomial = new Polynomial(other);
   
      for(Integer exp: myPoly.keySet())
      {
    	  addItem(exp, myPoly.get(exp), sumPolynomial);
      }
      return sumPolynomial;
   }
   
   public Polynomial multiply(Polynomial other)
   {
      Polynomial sumPolynomial = new Polynomial();
   
      for(Integer exp1: myPoly.keySet())
      {
    	  for(Integer exp2 : other.myPoly.keySet()){
    		  
    		  Integer finalExp = exp1 + exp2;
    		  Integer finalCoef = myPoly.get(exp1) * other.myPoly.get(exp2);
    		  
    		  addItem(finalExp, finalCoef, sumPolynomial);  
    	  }
      }
      return sumPolynomial;
   }
   
   public String toString()
   {
	   String toRet = "";

      for(Integer exp: myPoly.keySet())
      {
         int coef = myPoly.get(exp);
         if(exp == 0)
         {
            toRet = "" + coef;
         }
         else if(exp == 1)
         {
            if(coef == 1)
               toRet = "x + " + toRet;
            else if(coef == -1)
            	toRet = "-x + " + toRet;
            else
            	toRet = "" + coef + "x + " + toRet;
         }
         else
         {
        	 if(coef == 1)
                 toRet = "x^" + exp + " + " + toRet;
              else if(coef == -1)
              	toRet = "-x^" + exp + " + " + toRet;
              else
              	toRet = "" + coef + "x^" + exp + " + " + toRet;
         }
      }
      
      return toRet;
   }
   
   
  public void addItem(Integer exp, Integer coef, Polynomial poly){
	  
	  if(poly.myPoly.containsKey(exp)){
		  Integer ll = poly.myPoly.get(exp);
		  Integer sum = ll + coef;
     	 if(sum == 0){
     		poly.myPoly.remove(exp);
     	 }
     	 else{
     		poly.myPoly.put(exp, sum);
     	 }
      }
      else{
    	  poly.myPoly.put(exp, coef);
      }
  }
}
/*  
expected output
   2x^3 + -4x + 2
   10.0
   2x^4 + x^2 + -5x + -3
   2x^4 + 2x^3 + x^2 + -9x + -1
   4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 */