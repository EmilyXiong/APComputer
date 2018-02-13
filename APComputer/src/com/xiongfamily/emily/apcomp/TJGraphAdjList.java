package com.xiongfamily.emily.apcomp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class TJGraphAdjList {

	private ArrayList<Vertex> vertices;
	   private Map<String, Integer> nameToIndex ;

	   public TJGraphAdjList(){
		   vertices = new ArrayList<Vertex>();
		   nameToIndex = new HashMap<String, Integer>();
	   }
	   
	   public void addVertex(String v){

		   if(nameToIndex.get(v) == null){
			   vertices.add(new Vertex(v));
			   nameToIndex.put(v, vertices.size()-1);
		   }
	   }
	   
	   public Map<String, Integer> getVertexMap(){
		   return nameToIndex;
	   }
	   public void addEdge(String f, String t){
		   
		   if(nameToIndex.get(f) == null){
			   addVertex(f);
		   }
		   if(nameToIndex.get(t) == null){
			   addVertex(t);
		   }
		   Vertex from  = vertices.get(nameToIndex.get(f));
		   Vertex to  = vertices.get(nameToIndex.get(t));
		   ArrayList<Vertex>  fromAdjacencies =  from.getAdjacencies();
		   if (!fromAdjacencies.contains(to)){
			   fromAdjacencies.add(to);
		   }
	   }
	   
	   public List<Vertex> depthFirstSearch(String name){
		   
		   if(nameToIndex.get(name) == null){
			   return null;
		   }
		   
		   Vertex source = vertices.get(nameToIndex.get(name));
		   
		   List<Vertex> list = new ArrayList<Vertex>();
		   Stack<Vertex> stack = new Stack<Vertex>();
		   
		   stack.push(source);
		   
		   while(!stack.empty()){
			   Vertex temp = stack.pop();
			   if (!list.contains(temp)){
				   list.add(temp);
				   for (Vertex v : temp.getAdjacencies()){
					   stack.push(v);
				   }
			   }
		   }
		   
		   return list;
	   }
	   
	   public List<Vertex> breadthFirstSearch(String name){
		   
		   if(nameToIndex.get(name) == null){
			   return null;
		   }
		   
		   Vertex source = vertices.get(nameToIndex.get(name));
		   		   
		   List<Vertex> list = new ArrayList<Vertex>();
		   Queue<Vertex> queue = new  LinkedList<Vertex>();
		   
		   queue.add(source);
		   
		   while(!queue.isEmpty()){
			   Vertex temp = queue.remove();
			   if (!list.contains(temp)){
				   list.add(temp);
				   for (Vertex vertex : temp.getAdjacencies()){
					   queue.add(vertex);
				   }
			   }
		   }
		   
		   return list;
	   }
	   
	   public List<Vertex> depthFirstRecur(String name){
		   
		   if(nameToIndex.get(name) == null){
			   return null;
		   }
		   
		   Vertex source = vertices.get(nameToIndex.get(name));
		   
		   List<Vertex> list = new ArrayList<Vertex>();
		   list.add(source);
		   
		   depthFirstRecurHelper(source, list);
		   
		   return list;
	   }
	   
	   public void depthFirstRecurHelper(Vertex source, List<Vertex> list){
		   
		   for (Vertex v : source.getAdjacencies()){
			   
			   if(!list.contains(v)){
				   list.add(v);
				   depthFirstRecurHelper(v, list);
			   }
		   }   
	   }
	   
	   public void graphFromEdgeListData(String fileName) throws FileNotFoundException{
		   
		   Scanner sc = new Scanner(new File(fileName)); 
		   String line = null;
			while(sc.hasNext()){
				line = sc.nextLine().trim();
				String [] vs = line.split(" ");
				addEdge(vs[0], vs[1]);
			}
			sc.close();
	   }
	   
	   public int edgeCount() {
		   int count = 0;
		   for (Vertex v : vertices) {
			   count += v.getAdjacencies().size();
		   } 

		   return count;
	   }
	   
	   public boolean isReachable(String source, String target){
		   
		   boolean ret = false;
		   
		   List<Vertex> lists = depthFirstSearch(source);
		   for(Vertex v : lists){
			   if(target.equals(v.getName())){
				   ret = true;
				   break;
			   }
		   }
		   return ret;
	   }
	   
	   public boolean isConnected(){
		   	boolean ret = true;
		   
		   	for (String fromV : nameToIndex.keySet()){
		   		for (String toV : nameToIndex.keySet()){
		   			if(!isReachable(fromV, toV)){
		   				return false;
		   			}
		   		}
		   	}
		   	
		   return ret;
		   
	   }
	   
	   public String toString(){
		   String retValue = "";
		   for (Vertex v : vertices) {
			   retValue = retValue  + v.getName() + " " + v.getAdjacencies().toString() + "\n";
		   } 
		   return retValue;
	   }
}
