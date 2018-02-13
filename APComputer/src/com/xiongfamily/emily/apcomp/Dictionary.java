package com.xiongfamily.emily.apcomp;

   //Name:     Date:
import java.io.*;
import java.util.*;
public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
         
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
	   Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();

	   while(infile.hasNextLine())
	   {
		   add(eng2spn, infile.nextLine(), infile.nextLine());
	   }
	   
	   return eng2spn;
   }
   
   private static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
	   TreeSet<String> tranSet = (TreeSet<String>) dictionary.get(word);
	   
      if(tranSet == null){
    	  // this is a new word
    	  tranSet = new TreeSet<String>();
    	  tranSet.add(translation);
    	  dictionary.put(word, tranSet);
      }
      else{
    	  tranSet.add(translation);
      }
   }
   
   public static void display(Map<String, Set<String>> m)
   {
	  for (String key:  m.keySet() ){
		  System.out.println("    " + key + " " + m.get(key));
	  }
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
	   
	   Map<String, Set<String>> spn2eng = new TreeMap<String, Set<String>>();  
	   
	   for (String engWord : dictionary.keySet()){
		   TreeSet<String> spnWords = (TreeSet<String> ) dictionary.get(engWord);
		
		   for(String spnWord: spnWords){
			   TreeSet<String> engWords = (TreeSet<String>) spn2eng.get(spnWord);
			   if (engWords == null){
				   engWords = new TreeSet<String>();
				   engWords.add(engWord);
				   spn2eng.put(spnWord, engWords);
			   }
			   else{
				   engWords.add(engWord);
			   }
		   }
		  
		   
		   
	   }
	   
	   
	   return spn2eng;
   }
}
      /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/