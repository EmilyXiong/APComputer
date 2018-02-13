package com.xiongfamily.emily.apcomp;

// Name:    Date:
import java.util.*;
import java.io.*;

public class SetsOfLetters {
	public static void main(String[] args) throws FileNotFoundException {
		// Scanner sc = new Scanner(System.in);
		// System.out.print("Enter the file name: ");
		// String fileName = sc.next();
		String fileName = "declarationLast.txt";
		fillTheSets(fileName);
	}

	public static void fillTheSets(String fn) throws FileNotFoundException {
		List<TreeSet<String>> lowerSets = new ArrayList<TreeSet<String>>();
		List<TreeSet<String>> upperSets = new ArrayList<TreeSet<String>>();
		List<TreeSet<String>> otherSets = new ArrayList<TreeSet<String>>();

		String oneLine = null;
		Scanner inputFile = new Scanner(new File(fn));
		while (inputFile.hasNextLine()) {
			TreeSet<String> lset = new TreeSet<>();
			TreeSet<String> uset = new TreeSet<>();
			TreeSet<String> oset = new TreeSet<>();
			oneLine = inputFile.nextLine();
			System.out.println(oneLine);
			for(char c: oneLine.toCharArray()){
				if(Character.isAlphabetic(c)){
					if(Character.isUpperCase(c)){
						uset.add("" + c);
					}
					else{
						lset.add("" + c);
					}
				}
				else{
					oset.add("" + c);
				}
			}
			lowerSets.add(lset);
			upperSets.add(uset);
			otherSets.add(oset);
			System.out.println("Lower Case: " + lset);
			System.out.println("Upper Case: " + uset);
			System.out.println("Other: " + oset);
		}
		
		//print all the common characters
		TreeSet<String> commonLoSet = lowerSets.get(0);
		for (int i = 1; i < lowerSets.size(); i++){
			commonLoSet.retainAll(lowerSets.get(i));
		}
		
		TreeSet<String> commonUpSet = upperSets.get(0);
		for (int i = 1; i < lowerSets.size(); i++){
			commonUpSet.retainAll(upperSets.get(i));
		}
		
		TreeSet<String> commonOtherSet = otherSets.get(0);
		for (int i = 1; i < lowerSets.size(); i++){
			commonOtherSet.retainAll(otherSets.get(i));
		}
		
		
		System.out.println("Common Lower Case: " + commonLoSet);
		System.out.println("Common Upper Case: " + commonUpSet);
		System.out.println("Common Other: " + commonOtherSet);
		
		inputFile.close();
		
	}
	
	
	
}