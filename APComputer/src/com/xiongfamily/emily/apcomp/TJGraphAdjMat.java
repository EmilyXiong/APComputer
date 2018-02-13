package com.xiongfamily.emily.apcomp;

//name:   date:   
// resource classes and interfaces
// for use with Graphs0: Intro
//              Graphs1: Wardhall
//              Graphs2: Floyd
import java.util.*;
import java.io.*;

interface AdjacencyMatrix
{
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public void displayGrid();
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   
}

interface Warshall
{
   //User-friendly functionality
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   //Actual Warshall Algorithm
   public void allPairsReachability();
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class TJGraphAdjMat implements AdjacencyMatrix ,Warshall ,Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   
   private Map<String, List<String>> reachableCities;
   String[] verticesNames;
     
   /*  enter your code here  */  
   public TJGraphAdjMat(int size)
   {
      grid = new int[size][size];
      vertices = new LinkedHashMap<String, Integer>();
      reachableCities = new HashMap<String, List<String>>();
      verticesNames = new String[size];
   }
   public void addEdge(int source, int target)
   {
      if(source < grid.length && target < grid.length)
      {
         grid[source][target] = 1;
      }
      else
      {
         System.out.println("That is not within range.");
      }
   }
   public void removeEdge(int source, int target)
   {
      if(isEdge(source, target))
      {
         grid[source][target] = 0;
      }
      else
      {
         System.out.println("That is not an edge.");   
      }
   }
   public boolean isEdge(int from, int to)
   {
      if(grid[from][to] > 0 &&  grid[from][to] < 9999)
      {
         return true;
      }
      return false;
   }
   public void displayGrid()
   {
      for(int r = 0; r < grid.length ; r++)
      {
         for(int c = 0; c < grid[0].length; c++)
         {
            System.out.print(grid[r][c] + " ");
         }
         System.out.print("\n");
      }
   }
   public int edgeCount()
   {
      int count = 0;
      for(int r = 0; r < grid.length ; r++)
      {
         for(int c = 0; c < grid[0].length; c++)
         {
            if(grid[r][c] > 0 &&  grid[r][c] < 9999 )
            {
               count++;
            }
         }
      }
      return count;
   }
   public List<Integer> getNeighbors(int source)
   {
      List<Integer> neighbors = new ArrayList<Integer>();
      for(int c = 0; c < grid.length ; c++)
      {
         if(grid[source][c] == 1)
         {
            neighbors.add(c);
         }
      }
      return neighbors;
   }

	public boolean isEdge(String from, String to) {

		if(vertices.get(from) != null && vertices.get(to) !=null){
			return isEdge( vertices.get(from.trim()), vertices.get(to.trim()));	
		}
		return false;	
	}

	public Map<String, Integer> getVertices() {
		return vertices;
	}

	public void readNames(String fileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName)); 
		int size = Integer.parseInt(sc.nextLine()); 
		for (int i = 0; i< size; i++){
			verticesNames[i] =  sc.nextLine().trim();
			vertices.put(verticesNames[i], i);
		}
		sc.close();
		
	}

	public void readGrid(String fileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName)); 
		int size = Integer.parseInt(sc.nextLine()); 
		String line = null;
		for (int i = 0; i< size; i++){
			line = sc.nextLine();
			line = line.replaceAll("\\s+", " ").trim();
			String[] items = line.split(" ");
			int index = 0;
			for (int j = 0; j < size; j++){
				grid[i][index] = Integer.parseInt(items[j]);
				index++;
			}
		}
		sc.close();
	}
	
	public void displayVertices() {	
		for (String city : vertices.keySet()){
			System.out.println(vertices.get(city)  + "-" + city);
		}	
		System.out.println("");
	}

	public void allPairsReachability() {
		
		for (int v=0; v < vertices.size(); v++){
			for (int i = 0; i < vertices.size(); i++){
				for (int j=0; j< vertices.size(); j++){
					if(grid[i][v] ==1 && grid[v][j] ==1){
						grid[i][j] =1;
					}
				}
			}
		}	
		
		//now populate the 
		int index = 0;
		for (String city : vertices.keySet()){
			List<String> cities = new ArrayList<String>();
			for (int i = 0; i < vertices.size(); i++){
				if(grid[index][i] == 1){
					cities.add(verticesNames[i]);
				}
			}
			reachableCities.put(city, cities);
			index++;
		}	
		
	}
	
	public List<String> getReachables(String from){
		return reachableCities.get(from);
	}

	public int getCost(int from, int to) {
		
		return grid[from][to];
	}

	public int getCost(String from, String to) {
		
		return getCost(vertices.get(from), vertices.get(to));
	}

	public void allPairsWeighted() {
		for (int v=0; v < vertices.size(); v++){
			for (int i = 0; i < vertices.size(); i++){
				for (int j=0; j< vertices.size(); j++){
					if(grid[i][j] > grid[i][v] + grid[v][j]){
						grid[i][j] =grid[i][v] + grid[v][j];
					}
				}
			}
		}	
		
	}

}
