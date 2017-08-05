/**
 * @author Stephen Pacwa
 */
package javajesus.entities;

import java.util.ArrayList;
import java.util.List;

import javajesus.level.Level;

/*
 * Node is used to define position in xy-coordinate space.
 */
class Node {
    // The x position of coordinate
	int x;
	// The y position of coordinate
    int y;
    
    /**
     * Constructor for the Node class
     * 
     * @param x - x position
     * @param y - y position
     */
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/*
 * Pathfind is used to check for and generate a shortest path from the source to the destination using Dijkstra's Algorithm.
 */
class Pathfind
{   
    /**
     * Tries to create a shortest path from [sX, sY] to [dX, dY] on matrix. 
     * 
     * @param matrix - A 1D array of a 2D level. '0' Represents a solid tile, '1' is walkable.
     * @param sX - The x coordinate of the source location.
     * @param sY - The y coordinate of the source location.
     * @param dX - The x coordinate of the destination location.
     * @param dY - The y coordinate of the destination location.
     * @return an ArrayList. If null, no path found. else, the first position is the destination,
     * 		   the last position is the source, and the nodes in between are the path.
     */
	public static List<Node> generatePath(char[] matrix, int sX, int sY, int dX, int dY) {
        // Maps current node to previous
        // For every Node at x in cNode, the Node at x in pNode is its predecessor
        List<Node> pNode = new ArrayList<Node>();
        List<Node> cNode = new ArrayList<Node>();
        
        // Queue is used to work through the matrix while allowing deletion of old data
        List<Node> queue = new ArrayList<Node>();
        // First node is the start location
        Node start = new Node(sX, sY);
        
        // Add first node to top of cNode and queue, this will be the first position that all trace back to
        // null is added to pNode as there are no positions before this.
        queue.add(start);
        cNode.add(start);
        pNode.add(null);
        
        // pathExists will be used to determine if path is present
        // Initialize a new node.
        boolean pathExists = false;
        Node current = new Node(0,0);
        
        while(!queue.isEmpty()) {
        	// Set node in queue to current
        	current = queue.remove(0);
        	// Check if destination has been reached
            if(current.x == dX && current.y == dY) {
            	pathExists = true;
                break;
            }
            
            // Use '0' to mark that the destination has been visited
            matrix[current.x + (current.y * Level.LEVEL_WIDTH)] = '0'; // mark as visited
            
            // Find all correct neighbors (!= '0'), then queue them for the next cycle
            List<Node> neighbors = getNeighbors(matrix, current);
            queue.addAll(neighbors);
            
            // For every neighbor, add current node as predecessor
            cNode.addAll(neighbors);
            while(cNode.size() != pNode.size()) {
            	pNode.add(current);
            }
        }
        
        List<Node> path = new ArrayList<Node>();
        path.add(current);
        // If path exists, work backwards from destination using pNode to find the previous node
        // This will eventually lead to null set to be the previous node of the source
        if(pathExists) {
        	while(current != null) {
        		int ind = cNode.indexOf(current);
        		path.add(pNode.get(ind));
        		current = pNode.get(ind);
        	}
        // If no path, remove all old nodes and set to null
        } else {
        	path.clear();
        	path = null;
        }
         // Return path
        return path;
    }
    
	/**
	 * Creates an ArrayList of neighbors of the current tile if valid.
	 * 
	 * @param matrix - A 1D array of a 2D level.
	 * @param node - The current location.
	 * @return an ArrayList of all valid surrounding tiles.
	 */
    public static List<Node> getNeighbors(char[] matrix, Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        
        // This should be self explanatory
        if(isValidPoint(matrix, node.x - 1, node.y)) {
            neighbors.add(new Node(node.x - 1, node.y));
        }
        
        if(isValidPoint(matrix, node.x + 1, node.y)) {
            neighbors.add(new Node(node.x + 1, node.y));
        }
        
        if(isValidPoint(matrix, node.x, node.y - 1)) {
            neighbors.add(new Node(node.x, node.y - 1));
        }
        
        if(isValidPoint(matrix, node.x, node.y + 1)) {
            neighbors.add(new Node(node.x, node.y + 1));
        }
        
        return neighbors;
    }
    
    /**
     * Checks if tile is valid. i.e. not a '0'
     * 
     * @param matrix - A 1D array of a 2D level.
     * @param x - The x location in 2D array
     * @param y - The y location in 2D array
     * @return true is tile is valid, false otherwise
     */
    public static boolean isValidPoint(char[] matrix, int x, int y) {
        return !(x < 0 || x >= Level.LEVEL_WIDTH || y < 0 || y >= Level.LEVEL_HEIGHT) && (matrix[x + (y * Level.LEVEL_WIDTH)] != '0');
    }
}