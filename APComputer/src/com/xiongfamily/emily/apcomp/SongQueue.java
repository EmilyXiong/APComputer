package com.xiongfamily.emily.apcomp;

///name:Emily XIong    date:1/19/17
//first program on queues.
import java.io.*;
import java.util.*;
public class SongQueue
{
   private static Scanner infile;
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      fillPlayList();
      printSongList();
      infile = new Scanner(System.in);
      String prompt = "\tAdd song (A), Play song (P), Delete song (D), Quit (Q):  ";
      System.out.print(prompt);
      String str = infile.nextLine().toUpperCase();
      while(!str.equals("Q"))
      { 
         processRequest( str );
         System.out.print(prompt);
         str = infile.nextLine().toUpperCase();;
      } 
      System.out.println();
      System.out.println("No more music for you today.  Goodbye!");
      infile.close();
   }
   public static void fillPlayList()throws IOException
   {
      songQueue = new LinkedList<String>();
      infile = new Scanner(new File("songs.txt"));
      while(infile.hasNext())
      {
         String nx = infile.nextLine();
         songQueue.add(nx.substring(0, nx.indexOf("-") - 1));
      }
      System.out.println("\n");
      infile.close();
   }
   public static void processRequest(String str)
   {
      if(str.equals("A"))
      {
         add();
      }
      else if(str.equals("P"))
      {
         play();
      }
      else if(str.equals("D"))
      {
         delete();
      }
      System.out.print(songQueue);
      System.out.println("\n");
   }
   public static void add()
   {
      //Scanner in = new Scanner(System.in);
      String ask = "Song to add?  ";
      System.out.println(ask);
      songQueue.add(infile.nextLine());
   }
   public static void play()
   {
      System.out.println("Now playing: " + songQueue.poll());
   }
   public static void delete()
   {
      //Scanner in = new Scanner(System.in);
      String ask = "Enter song to delete (exact match):  ";
      System.out.print(ask);
      String toRem = infile.nextLine();
      Queue<String> removed = new LinkedList<String>();
      while(!songQueue.isEmpty())
      {
    	  if (toRem.compareToIgnoreCase(songQueue.peek()) != 0){
    		  removed.add(songQueue.poll());
    	  }
    	  else{
    		  songQueue.poll();
    	  }
      }
      songQueue = removed;
   }
   public static void printSongList()
   {
      System.out.print(songQueue);
   }
}