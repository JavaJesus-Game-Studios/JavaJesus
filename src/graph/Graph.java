package graph;

import java.util.List;

public class Graph {
	private final List<Vertex> listVer;
	private final List<Edge> listEdge;
	
	/*
	 * Constructor for the graph class
	 * @param listVer: The list of all the vertices in the graph
	 * @param listEdge: The list of all the edges in the graph.
	 */
	public Graph(List<Vertex> listVer, List<Edge> listEdge) {
		this.listVer = listVer;
		this.listEdge = listEdge;
	}
	
	/*
	 * Returns the list of edges in the graph
	 * @return: List<Edge> listEdge
	 */
	public List<Edge> getEdges() {
		return listEdge;
	}
	
	/*
	 * Returns the list of vertices in the graph
	 * @return: List<Vertex> listVer
	 */
	public List<Vertex> getVertex() {
		return listVer;
	}
}
