package ca.javajesus.level.generation;

import java.awt.Point;

public class VillageTile {
	private double probability;
	private boolean ground;
	private Point coordinates;
	
	public VillageTile(boolean ground, Point coordinates) {
		this.ground = ground;
		this.coordinates = coordinates;
	}
	
	/**
	 * Set's the probability of a house at this point.
	 * @param prob Must be a number between 1 and 100, 1 is 1% probability, and 100 is 100%
	 */
	public void setProbability(double prob) {
		probability = 100 - prob;
	}
	
	public double getProbability() {
		return probability;
	}
	
	public boolean getGroundCheck() {
		return ground;
	}
	
	public Point getCoordinates() {
		return coordinates;
	}
}
