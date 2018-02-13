package com.xiongfamily.emily.apcomp;

public class TestQuestions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("*** test1(3453)=" + test1(3453));
		System.out.println("*** test2(6)=" + test2(6));
		System.out.println("*** test3(10)=" + test3(10));

	}

	
	public static int test1(int x)
	{
		if(x==0){
			return 0;
		}
		else{
			return ((x%10) + test1(x/10));
		}
	}
	
	public static int test2(int x){
		if(x==1)
			return 2;
		else
			return 2*test2(x-1);
	}
	
	public static int test3(int x){
		if(x==0)
			return 0;
		else
			return (x + test3(x/2) + test3(x/4));
	}
}
