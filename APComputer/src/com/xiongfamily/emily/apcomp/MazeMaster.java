package com.xiongfamily.emily.apcomp;

import java.util.*;
import java.io.*;
public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename: ");
      char[][] retArr = buildCharArr(sc.next());
      Maze m = new Maze(retArr);
      m.display();
      System.out.println("Options: ");
      System.out.println("1: Mark all paths.");
      System.out.println("2: Mark all paths, and display the count of all steps.");
      System.out.println("3: Show only the correct path.");
      System.out.println("4: Show only the correct path. If no path exists, display 'No path exists'.");
      System.out.println("5: Show only the correct path, and display the count of steps.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();  
   } 
   
   //take in a filename, and return a char[][]
   public static char[][] buildCharArr(String fileName)
   {
   		// enter your code here
		char [][] area = null;
		Scanner scanner = null;
		
		try{
			scanner = new Scanner(new File(fileName));
			
			int row = scanner.nextInt();
			scanner.nextLine();
			area  = new char[row][];
			for (int i = 0; i< row; i++){
				area[i] = scanner.nextLine().toCharArray();
			}
		}
		catch(Exception e){
			System.out.println("****  " + e);
		}
		scanner.close();
		
		return area;	
   }
}


class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char PATH = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   //fields
   private char[][] maze;
   private int startRow, startCol;
   private boolean S_Exists=false, E_Exists=false;
   //constructor initializes all the fields
   public Maze(char[][] inCharArr)    
   {
	   maze = inCharArr;
	   for(int a = 0; a<maze.length; a++)
	      {
	         for(int b = 0; b<maze[0].length; b++)
	         {
	        	 if(maze[a][b] == START){
	        		 startRow = a;
	        		 startCol = b;
	        		 S_Exists = true;
	        		 break;
	        	 }
	         }
	        if (S_Exists){
	        	break;
	        }
	      }
   }
   
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println("");
      }
      System.out.println("");
   }
   
   public void solve(int n)
   {
      if(n==1)
      {
         markAllPaths(startRow, startCol);
      }
      else if(n==2)
      {
         int count = markAllPathsAndCountStars(startRow, startCol);
         System.out.println("Number of steps = " + count);
      }
      else if(n==3)
      {
         displayTheCorrectPath(startRow, startCol);
      }
      else if(n==4)   //use maze3 here
      {
         if( !displayTheCorrectPath(startRow, startCol) )
            System.out.println("No path exists");   
      }     
      else if(n==5)
      {
         displayCorrectPathAndCountStars(startRow, startCol, 0);
      }
      else System.out.println("invalid submission");
      
   }         
   
   private void markAllPaths(int r, int c)
   {
		if (c >=0 && c < maze[0].length && r >= 0 && r< maze.length){
			if (maze[r][c] == PATH || maze[r][c] == START || maze[r][c] == EXIT){
				maze[r][c] = STEP;
				markAllPaths(r, c-1);
				markAllPaths(r, c+1);
				markAllPaths(r-1,c);
				markAllPaths(r+1, c);
			}
		}

		return;
   }
   
   private int markAllPathsAndCountStars(int r, int c)
   {
	   int count = 0;
		if (c >=0 && c < maze[0].length && r >= 0 && r< maze.length){
			if (maze[r][c] == PATH || maze[r][c] == START || maze[r][c] == EXIT){
				maze[r][c] = STEP;
				count = 1 + markAllPathsAndCountStars(r, c-1)+
						markAllPathsAndCountStars(r, c+1) +
						markAllPathsAndCountStars(r-1,c) +
						markAllPathsAndCountStars(r+1, c);
			}
		}

		return count;
   }

   private boolean displayTheCorrectPath(int r, int c)
   {
	   
		if (c >=0 && c < maze[0].length && r >= 0 && r< maze.length){
			if (maze[r][c] == PATH || maze[r][c] == START){
				maze[r][c] = 'o';
				if (displayTheCorrectPath(r, c-1) || displayTheCorrectPath(r, c+1)
						|| displayTheCorrectPath(r-1,c) || displayTheCorrectPath(r+1, c)){
					maze[r][c] = STEP;
					return true;
				}
				maze[r][c] = PATH;
			}
			else if (maze[r][c] == EXIT ){
				maze[r][c] = STEP;
				return true;
			}
		}
		
		return false;
   }
   
   private boolean displayCorrectPathAndCountStars(int r, int c, int count)
   {
	   
		if (c >=0 && c < maze[0].length && r >= 0 && r< maze.length){
			if (maze[r][c] == PATH || maze[r][c] == START){
				maze[r][c] = 'o';
				if (displayCorrectPathAndCountStars(r, c-1, count+1) || 
						displayCorrectPathAndCountStars(r, c+1, count+1) ||
						displayCorrectPathAndCountStars(r-1,c, count+1) ||
						displayCorrectPathAndCountStars(r+1, c, count+1)){
					maze[r][c] = STEP;
					return true;
				}
				maze[r][c] = PATH;
			}
			else if (maze[r][c] == EXIT ){
				maze[r][c] = STEP;
				System.out.println("Number of steps = " + (count+1));
				return true;
			}
		}
	   
      return false; 
   }
   
   //This is for testing purposes. Do not change or remove this method.
   public char[][] getMaze()
   {
      return maze;
   }
}
