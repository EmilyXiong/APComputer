package com.xiongfamily.emily.apcomp;

import java.util.ArrayList;

public class Vertex {
	
	private final String name;
    private ArrayList<Vertex> adjacencies;

	public Vertex(String name){
    	this.name = name;
    	adjacencies = new ArrayList<Vertex>();
    }

	public String getName() {
		return name;
	}

	public ArrayList<Vertex> getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(ArrayList<Vertex> adjacencies) {
		this.adjacencies = adjacencies;
	}

	@Override
	public String toString() {
		return name;
	}
    
    
}
