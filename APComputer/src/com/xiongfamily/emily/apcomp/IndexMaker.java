package com.xiongfamily.emily.apcomp;

//  Name:      date:
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}


class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
   public DocumentIndex()
   {
	   super();
   }
   
   public DocumentIndex(int size)
   {
	   super(size);
   }
   
   /** calls foundOrInserted, which returns an index.  At that position,  
   updates that IndexEntry's list of line numbers with num. */
   public void addWord(String word, int num)
   {
	   IndexEntry thisWord = this.get(foundOrInserted(word));
	   thisWord.add(num);
	   
   }
      
    /** extracts all the words from str, skipping punctuation and whitespace 
    and for each word calls addWord(word, num).  A good way to skip punctuation 
    and whitespace is to use String's split method, e.g., split("[., \"!?]") */
   public void addAllWords(String str, int num) 
   {
	   if (str.trim().length() != 0){
		   String[] words = str.trim().split("[., \"!?]");
		   
		   for (String word: words)
		   {
			   if(word.trim().length() != 0)
			   {
				   addWord(word, num);
			   }
		   }
	   }
   }
      
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
	   int pos = 0;

	   for (IndexEntry inxEntry: this)
	   {
		   int compare = word.compareToIgnoreCase(inxEntry.getWord());
		   
		   if (compare == 0)
		   {
			   //found the word
			   return pos;
		   }
		   else if (compare < 0)
		   {
			   //found the position and this is a new word
			   this.add(pos, new IndexEntry(word));
			   return pos;
		   }
		   pos++;
	   }
	   //this word is a new word and should be at the end
	   this.add(new IndexEntry(word));
	   return pos;
   }
}
   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
     private String word;
     private ArrayList<Integer> numsList;
   
     //constructors
   public IndexEntry(String w)
   {
      word = w.toUpperCase();
      numsList = new ArrayList<Integer>();
   }
     /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
      for(int a =0; a < numsList.size(); a++)
      {
         if(num == numsList.get(a))
         {
            return;
         } 
      }
      numsList.add(num);
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      return word;
   }
      
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
      String strpiece = word;
      for(int i =0; i < numsList.size(); i++)
      {
         strpiece += " " + numsList.get(i) + ",";
      }
      String last = numsList.get(numsList.size() - 1) + "";
      strpiece = strpiece.replace( last + ",", last);
      return strpiece;
   }
   
	@Override
	public int compareTo(IndexEntry o) {
		return this.getWord().compareToIgnoreCase(o.getWord());
	}
}

