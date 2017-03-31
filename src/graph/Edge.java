package graph;

public class Edge {
	private final Vertex source;
	private final Vertex destination;
	private final String id;
	private final int weight;
	
	
	/*
	 * Constructor for the Edge class
	 * @param source: The source Vertex
	 * @param destination: The destination Vertex
	 * @param id: The id of the edge
	 * @param weight: The weight of the edge
	 */
	public Edge(Vertex source, Vertex destination, String id, int weight) {
		this.source = source;
		this.destination = destination;
		this.id = id;
		this.weight = weight;
	}
	
	/*
	 * Returns the source of the edge
	 * @return: Vertex source
	 */
	public Vertex getSource() {
		return source;
	}
	
	/*
	 * Returns the destination of the edge
	 * @return: Vertex destination
	 */
	public Vertex getDestination() {
		return destination;
	}
	
	/*
	 * Returns the id of the edge
	 * @return: String id
	 */
	public String getID() {
		return id;
	}
	
	/*
	 * Returns the weight of the edge
	 * @return: int weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/*
	 * Returns the toString of the edge class
	 * @return: the source and destination separated by a dash
	 */
	public String toString() {
		return source + " - " + destination;
	}
}
