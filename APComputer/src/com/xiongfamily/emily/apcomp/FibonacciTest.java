package com.xiongfamily.emily.apcomp;

import java.io.File;
import java.sql.Timestamp;
import java.util.Scanner;

public class FibonacciTest {

	public static void main(String args[]) {
		int row = 1;
		int col = 2;
		char[][] area = read("/Users/hxiong/Documents/area2.txt");
		displayArea(fileArea1(area, row,col, area[row][col]));
		
		//int n = 13;
        //superprime(2);
		
		//System.out.println("*****  " + n + " is prime number?" + isPrime(n) );
 
		// input to print Fibonacci series upto how many numbers
//		System.out.println("Enter number upto which Fibonacci series to print: ");
//		int number = new Scanner(System.in).nextInt();
// 
//		java.util.Date date= new java.util.Date();
//		System.out.println(new Timestamp(date.getTime()));
//		System.out.println("\nMethod-2: Fibonacci number at location " + number + " is ==> " + (fibonacciRecusion(number) + ""));
//		date= new java.util.Date();
//		System.out.println(new Timestamp(date.getTime()));
//
//		
//		System.out.println("\nMethod-2: Fibonacci number at location " + number + " is ==> " + (fibonacciLoop(number) + ""));
//		date= new java.util.Date();
//		System.out.println(new Timestamp(date.getTime()));
//		
		

		
		
//		int n = 35;
//		System.out.println("      **** checkSequenceLoop STEPS = " + checkSequenceLoop(n));
//		System.out.println("      **** hailStoneRecursive1 STEPS = " + hailStoneRecursive1(n));
//		System.out.println("      **** hailStoneRecursive2 STEPS = " + hailStoneRecursive2(n, 1));

//		System.out.println("is " + n + " a prime number ?" + isPrime(n));
//			
		
	}
 
	public static char[][] fileArea(char[][] area, int row, int col, char thisChar){

		area[row][col] = '*';
		
		//go left
		if (col >0 && area[row][col-1] == thisChar){
			fileArea( area, row, col-1, thisChar);
		}

		//go right
		if (col < area[row].length-1 && area[row][col+1] == thisChar){
			fileArea( area, row, col+1, thisChar);
		}
		
		//go up
		if (row > 0  && area[row-1][col] == thisChar){
			fileArea( area, row-1, col, thisChar);
		}
		
		//go down
		if (row < area.length-1 && area[row+1][col] == thisChar){
			fileArea( area, row+1, col, thisChar);
		}
		
		return area;
	}
	

	public static char[][] fileArea1(char[][] area, int row, int col, char thisChar)
	{
		
		if (col >=0 && col < area[0].length && row >= 0 && row < area.length){
			if (area[row][col]==thisChar){
				area[row][col] = '*';
				fileArea1( area, row, col-1, thisChar);
				fileArea1( area, row, col+1, thisChar);
				fileArea1( area, row-1, col, thisChar);
				fileArea1( area, row+1, col, thisChar);
			}
		}
		return area;
	}
	
	public static void displayArea(char[][] area){
			
		for (int i=0; i<area.length; i++){
			System.out.print("[");
			for (int j=0; j<area[i].length; j++){
				System.out.print(area[i][j]);
			}
			System.out.print("]\n");
		}

	}
	
	public static char[][] read(String filename){
		
		char [][] area = null;
		Scanner scanner = null;
		
		try{
			scanner = new Scanner(new File(filename));
			
			int row = scanner.nextInt();
			scanner.nextLine();
			area  = new char[row][];
			for (int i = 0; i< row; i++){
				area[i] = scanner.nextLine().toCharArray();
			}
		}
		catch(Exception e){
			System.out.println("      **** File is not right ");
		}
		scanner.close();
		
		return area;
	}
	
	
	
	// Method-1: Java program for Fibonacci number using recursion.
	public static int fibonacciRecusion(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}
 
		return fibonacciRecusion(number - 1) + fibonacciRecusion(number - 2); // tail recursion
	}
 
	// Method-2: Java program for Fibonacci number using Loop.
	public static int fibonacciLoop(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}
		int fibo1 = 1, fibo2 = 1, fibonacci = 1;
		for (int i = 3; i <= number; i++) {
			fibonacci = fibo1 + fibo2; // Fibonacci number is sum of previous two Fibonacci number
			fibo1 = fibo2;
			fibo2 = fibonacci;
 
		}
		return fibonacci; // Fibonacci number
	}
 
	public static int checkSequenceLoop(int x) {
		int counter = 1;
        int number = x;
        while(number !=1)
        {
        	System.out.print(number + "-");
            if(number % 2 == 0) //even
            {
                number = number/2;
            }
            else //odd
            {
                number = number*3 + 1;
            }

        counter++; //counts sequence
        }
        System.out.print(number);
        return counter;
   }
	
	private static int hailStoneRecursive1(int number) {
		
        // base case: number = 1
        if (number == 1) {
        	System.out.print(number);
            return 1;
        }
        else{
        	System.out.print(number + "-");
        }
        if ((number % 2 != 0)) { // number is odd:
        	number *= 3;
        	number += 1;
        } else {
        	number /= 2;
        }
        
        return hailStoneRecursive1(number) + 1;
    }

	private static int hailStoneRecursive2(int number, int count) {
		int inCount = count;
        // base case: number = 1
        if (number == 1) {
        	System.out.print(number);
            return inCount;
        }
        else{
        	System.out.print(number + "-");
        }
        if ((number % 2 != 0)) { // number is odd:
        	number = 3 * number +1;
        } else {
        	number /= 2;
        }
        
        return hailStoneRecursive2(number, inCount +1);
    }
	
	public static boolean isPrime(int n){
		
		if (n == 2){
			return true;
		}
		
		for (int i = 2; i<n/2; i++){
			if (n%i==0){
				return false;
			}
		}
		return true;
	}
	
	 public static void leftRight(String s, int n){
		 if(s.length() == n)
			 System.out.println(s);
		 else
		 {
			 leftRight( s + "L", n);   
			 leftRight( s + "R", n);       
		 }
	 }

	 
	public static void oddDigits(String s, int n)
	{
		if(s.length() == n)
			System.out.println(s);
		else
		{
			oddDigits(s + "1", n);
			oddDigits(s + "3", n);
			oddDigits(s + "5", n);
			oddDigits(s + "7", n);
			oddDigits(s + "9", n);
		}
	}
	
	public static void superprime(int n)		{
		recur(2, n); //try leading 2, 3, 5, 7, i.e. all the single-digit primes
		recur(3, n);
		recur(5, n);
		recur(7, n);
	}
	
	
	private static void recur(int k, int n)
	{
		if(k > Math.pow(10, n-1) && isPrime(k) ){
			System.out.println(k);
		}
		else if(isPrime(k))
		{
			recur(k*10+1, n);
			recur(k*10+3, n);
			recur(k*10+7, n);
			recur(k*10+9, n);
		}
		
	}
	

}
