package graph;



public class Vertex {
	final private String id;
	final private String desc;
	
	/*
	 * Creates the vertex object for Dijkstra's Method
	 * @param id: The ID of the object
	 * @param desc: the name of the object
	 */
	public Vertex(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	/*
	 * Returns the ID of the object
	 * @returns: String id
	 */
	public String getID() {
		return id;
	}
	
	/*
	 * Returns the name of the object
	 * @returns: String desc
	 */
	public String getDesc() {
		return desc;
	}
	
	/*
	 * Checks if the two objects are equal
	 * @param obj: The object you are comparing to the other
	 * @return: true is the same, false otherwise
	 */
	public boolean equals(Vertex obj) {
		if(this == obj) {
			return true;
		}
		return false;
	}
	
	/*
	 * toString for the vertex class
	 * @return: The name of the object
	 */
	public String toString() {
		return desc;
	}
}
