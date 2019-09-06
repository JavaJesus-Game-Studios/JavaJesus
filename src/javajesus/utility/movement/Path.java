package javajesus.utility.movement;

import java.util.ArrayList;

/**
 * A path is a list of movement vectors and converts it into a usable form for
 * entities to utilize
 */
public class Path {

	// list of movements for the mob to use
	private ArrayList<MovementVector> vectors = new ArrayList<MovementVector>();

	protected Path() {
	}

	public void add(MovementVector vector) {
		vectors.add(vector);
	}
	
	public void clear() {
		vectors.clear();
	}

	/**
	 * @return whether or not the path exists
	 */
	public boolean isNotEmpty() {
		return vectors.size() > 0;
	}

	/**
	 * Checks if the mob moves to the next node
	 */
	public void update() {
		if (vectors.get(0).isCompleted()) {
			vectors.remove(0);
		}
	}

	/**
	 * @return the next node, or script, in the path
	 */
	public MovementVector next() {
		return vectors.get(0);
	}

}
