package javajesus.utility;

import java.awt.Rectangle;
import java.util.ArrayList;

import javajesus.entities.Entity;


/**
 * 
 * @author shtevay
 * 9/20/2019
 * QuadTree Data Structure to be used in Collision Detection
 */
public class QuadTree {
	// Max Entities per node
	private int MAX_ENTITIES = 10;
	// Maximum children per level
	private int MAX_CHILDREN = 4;
	private int MAX_HEIGHT = 6;

	
	private int height;
	private ArrayList<Entity> entities;
	private Rectangle bounds;
	private QuadTree[] children;
	
	/**
	 * Constructor
	 * @param height the height of the current node in the QuadTree
	 * @param bounds the 2D space that the node occupies
	 */
	public QuadTree(int height, Rectangle bounds) {
		this.height = height;
		this.entities = new ArrayList<Entity>();
		this.bounds = bounds;
		children = new QuadTree[MAX_CHILDREN];
	}
	
	/**
	 * Method to clear the QuadTree
	 */
	public void clear() {
		entities.clear();
		// Recursively clear the entities from the QuadTree
		for( int i = 0; i < children.length; i++ ) {
			if(children[i] != null) {
				children[i].clear();
				children[i] =  null;
			}
		}
	}
	
	/**
	 * Method to split the QuadTree into 4 new subchildren
	 */
	public void split() {
		int newWidth = (int)(bounds.getWidth() / 2);
		int newHeight = (int)(bounds.getHeight() / 2);
		int x = (int)bounds.getX();
		int y = (int)bounds.getY();
		
		for( int i = 0; i < MAX_CHILDREN; i++ ) {
			children[i] = new QuadTree(height + 1, new Rectangle(x + ((i == 0 || i == 3) ? newWidth : 0),
					y + ((i == 2 || i == 3) ? newHeight : 0),newWidth,newHeight));
		}
	}
	/**
	 * Method handles inserting new collsion boxes into the tree, splits if need be
	 * @param newRect the rectangle that is being added to the Tree
	 */
	public void insert( Entity newEntity ) {
		Rectangle newRect = newEntity.getBounds();
		// If node has children, add it to them
		if( children[0] != null ) {
			int index = getIndex( newRect );
			// Add the node to the child
			if( index != -1 ) {
				children[index].insert(newEntity);
				return;
			}
			// Else add the game to the Parent
		}
		entities.add(newEntity);
		
		// Do we need to split into more children
		if( entities.size() > MAX_ENTITIES && height < MAX_HEIGHT) {
			// If there are no children split
			if( children[0] == null ) {
				split();
			}
			// Fill the children up as much as possible
			int i = 0;
			while( i < entities.size() ) {
				int index = getIndex(entities.get(i).getBounds());
				if(index != -1)
					children[index].insert(entities.remove(i));
				else
					i++;
			}
		}
	}
	/**
	 * Method that returns all other collisionBoxes the Query Box could collide with
	 * @param boxes entities that the query box could collide with
	 * @param queryRect the box that we are checking collisions for
	 * @return
	 */
	public ArrayList<Entity> retrieve(ArrayList <Entity> boxes, Rectangle queryRect) {
		// Base Case return smallest quadrant
		if( children[0] == null )
			boxes.addAll(entities);
		int index = getIndex(queryRect);
		// If the box fits in a quadrant and the tree has children
		if (index != -1 && children[0] != null) {
			children[index].retrieve(boxes, queryRect);
		}
		return boxes;
	}
	
	/**
	 * Method that determines which node the entity is in. Return -1 if it is not in any node.
	 * @param queryRect The rectangle we want to find the index of
	 * @return the index of the queried rectangle
	 */
	public int getIndex(Rectangle queryRect) {
		int index = -1;
		double xMid = bounds.getX() + (bounds.getWidth() / 2);
		double yMid = bounds.getY() + (bounds.getHeight() / 2);
		
		// If the Rectangle is on the left Half of the Vertical Midpoint
		if( queryRect.getX() + queryRect.getWidth() < xMid) {
			// Top Left
			if( queryRect.getY() + queryRect.getHeight() < yMid )
				index = 1;
			// Bottom Left
			if( queryRect.getY() > yMid )
				index = 2;
		// Right Half of the Vertical Midpoint
		} else if( queryRect.getX() > xMid ) {
			// Top Right
			if( queryRect.getX() + queryRect.getHeight() < yMid )
				index = 0;
			// Bottom Right
			if( queryRect.getX() > yMid )
				index = 3;
		}
		return index;
	}

}
