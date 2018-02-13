package com.xiongfamily.emily.apcomp;

//name:   date: 
//for use with Graphs6: Dijkstra
//             Graphs7: Dijkstra with Cities

import java.io.*;
import java.util.*;

class Edge {
	public final wVertex target;
	public final double weight;

	public Edge(wVertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}

interface wVertexInterface {
	public String toString();

	public String getName();

	public double getMinDistance();

	public void setMinDistance(double m);

	public wVertex getPrevious(); // Graphs 7

	public void setPrevious(wVertex v); // Graphs 7

	public ArrayList<Edge> getAdjacencies();

	public int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface {
	private final String name;
	private ArrayList<Edge> adjacencies;
	private double minDistance = Double.POSITIVE_INFINITY;
	private wVertex previous;

	/* enter your code here */
	public wVertex(String name) {
		this.name = name;
		adjacencies = new ArrayList<Edge>();
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double m) {
		minDistance = m;
	}

	public ArrayList<Edge> getAdjacencies() {
		return adjacencies;
	}

	public int compareTo(wVertex other) {
		int ret = (int) (minDistance - other.minDistance);
		return ret;
	}

	public wVertex getPrevious() { // Graphs 7
		return previous;
	}

	public void setPrevious(wVertex v) { // Graphs 7
		previous = v;
	}
}

public class TJGraphAdjListWeighted implements TJGraphAdjListWeightedInterface {
	private ArrayList<wVertex> vertices = new ArrayList<wVertex>();
	private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();

	/* enter your code here */
	public List<wVertex> getVertices() {

		return vertices;
	}

	public wVertex getVertex(int i) {

		return vertices.get(i);
	}

	public wVertex getVertex(String vertexName) {

		Integer index = nameToIndex.get(vertexName);
		if (index == null) {
			return null;
		}
		return vertices.get(index);
	}

	public void addVertex(String v) {
		if (nameToIndex.get(v) == null) {
			vertices.add(new wVertex(v));
			nameToIndex.put(v, vertices.size() - 1);
		}

	}

	public void addEdge(String source, String target, double weight) {
		if (nameToIndex.get(source) == null) {
			addVertex(source);
		}
		if (nameToIndex.get(target) == null) {
			addVertex(target);
		}
		wVertex from = getVertex(source);
		wVertex to = getVertex(target);

		boolean found = false;
		for (Edge e : from.getAdjacencies()) {
			if (e.target.equals(to)) {
				found = true;
				break;
			}
		}

		if (!found) {
			from.getAdjacencies().add(new Edge(to, weight));
		}

	}

	public void minimumWeightPath(String vertexName) { // Dijkstra's

		
		getVertex(vertexName).setMinDistance(0);

		PriorityQueue<wVertex> queue = new PriorityQueue<wVertex>();
		List<wVertex> removed = new ArrayList<wVertex>();
		queue.add(getVertex(vertexName));

		while (!queue.isEmpty()) {

			wVertex startVertex = queue.remove();
			removed.add(startVertex);
			for (Edge adjE : startVertex.getAdjacencies()) {
				double distance = startVertex.getMinDistance() + adjE.weight;
				wVertex target = adjE.target;
				if (!removed.contains(target)) {
					if (adjE.target.getMinDistance() == Double.POSITIVE_INFINITY) {
						adjE.target.setMinDistance(distance);
					} else {
						if (adjE.target.getMinDistance() > distance) {
							adjE.target.setMinDistance(distance);
						}
					}
					adjE.target.setPrevious(startVertex);
					queue.add(adjE.target);
				}
			}
		}
	}

	/* Graphs 7 */

	public List<wVertex> getShortestPathTo(wVertex v) {
		List<wVertex> path = new ArrayList<wVertex>();
		wVertex startV = null;
		wVertex endV = v;
		for (wVertex tempV : vertices) {
			if (tempV.getMinDistance() == 0.0) {
				startV = tempV;
			} 
		}

		while (endV != null) {
			path.add(0, endV);
			if (endV.equals(startV)){
				break;
			}
			endV = endV.getPrevious();
			
		}
		return path;
	}

	public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData)
			throws FileNotFoundException {
		Scanner sc = new Scanner(vertexNames);

		int size = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < size; i++) {
			addVertex(sc.nextLine().trim());
		}
		sc.close();
		sc = new Scanner(edgeListData);
		String line = null;
		while (sc.hasNext()) {
			line = sc.nextLine().trim();
			String[] vs = line.split(" ");
			addEdge(vs[0], vs[1], Double.parseDouble(vs[2]));
		}
		sc.close();

		return this;
	}

}

interface TJGraphAdjListWeightedInterface {
	public List<wVertex> getVertices();

	public wVertex getVertex(int i);

	public wVertex getVertex(String vertexName);

	public void addVertex(String v);

	public void addEdge(String source, String target, double weight);

	public void minimumWeightPath(String vertexName); // Dijkstra's

	/* Graphs 7 */

	public List<wVertex> getShortestPathTo(wVertex v);

	public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData)
			throws FileNotFoundException;

}