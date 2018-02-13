package com.xiongfamily.emily.apcomp;

//  Name:      date:
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.

import java.io.*;
import java.util.*;

public class IndexMakerMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String infileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(infileName));
      String outfileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outfileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }

   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex indexH = new DocumentIndex(); 	
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         indexH.addAllWords(inputFile.nextLine(), lineNum);
      }
      //print to the outputFile

    
      for (Map.Entry<String, ArrayList<Integer>> entry : indexH.entrySet()) {
    	  ArrayList<Integer> lineList = entry.getValue();
          String word = entry.getKey();
          String strpiece = word;
    	  for(int i =0; i < lineList.size(); i++)
          {
             strpiece += " " + lineList.get(i) + ",";
          }
          String last = lineList.get(lineList.size() - 1) + "";
          strpiece = strpiece.replace( last + ",", last);
          outputFile.println(strpiece);
     }
   }
}

class DocumentIndex extends TreeMap<String, ArrayList<Integer>>
{
	
   public void addWord(String word, int lineNum)
   {
	   String WORD = word.toUpperCase();
	   //first check if the treemap has this wrod
	   ArrayList<Integer> wordIndex = this.get(WORD);
	   if ( wordIndex == null){
		   //this is a new word
		   wordIndex = new ArrayList<Integer> ();
		   wordIndex.add(lineNum);
		   this.put(WORD, wordIndex);
	   }
	   else{
		   //check if the lineNum is already exist
		   if(!wordIndex.contains(lineNum)){
			   wordIndex.add(lineNum);
		   }
	   }
   
   }
   
 /** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord(word, num).  A good way to skip punctuation 
 and whitespace is to use String's split method, e.g., split("[., \"!?]") */
   public void addAllWords(String str, int lineNum) 
   {
   
	   if (str.trim().length() != 0){
		   String[] words = str.trim().split("[., \"!?]");
		   
		   for (String word: words)
		   {
			   if(word.trim().length() != 0)
			   {
				   addWord(word, lineNum);
			   }
		   }
	   }
   }
}


