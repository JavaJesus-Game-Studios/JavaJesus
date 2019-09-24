package javajesus.entities.collision;

import java.util.ArrayList;

public class CollisionCanvas {
	
	private int width, height;
	
	private Coordinate[][] plane;
	
	public CollisionCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		plane = new Coordinate[width][height];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				plane[x][y] = new Coordinate(x, y);
			}
		}
	}
	
	/**
	 * @param box
	 * @param dx
	 * @param dy
	 */
	public void move(CollisionBox box, int dx, int dy) {
		int sign = dx >= 0 ? 1 : -1;
		ArrayList<Coordinate> toAdd = new ArrayList<>();
		ArrayList<Coordinate> toRemove = new ArrayList<>();
		for (int i = 0; i < Math.abs(dx); i++) {
			for (int j = 0; j < box.getHeight(); j++) {
				toAdd.add(plane[box.getX() + (sign > 0 ? box.getWidth() : 0) + sign * i][box.getY() + j]);
				toRemove.add(plane[box.getX() + (sign > 0 ? 0 : box.getWidth()) + -1 * sign * i][box.getY() + j]);
			}
		}
		
		sign = dy >= 0 ? 1 : -1;
		for (int i = 0; i < box.getWidth(); i++) {
			for (int j = 0; j < Math.abs(dy); j++) {
				Coordinate c = plane[box.getX() + i][box.getY() + (sign > 0 ? box.getHeight() : 0) + sign * j];
				if (!toAdd.contains(c)) {
					toAdd.add(c);
				}
				Coordinate r = plane[box.getX() + i][box.getY() + (sign > 0 ? 0 : box.getHeight()) + -1 * sign * j];
				if (toAdd.contains(r)) {
					toAdd.remove(r);
				}
				if (!toRemove.contains(r)) {
					toRemove.add(r);
				}
			}
		}
		
		for (Coordinate c: toAdd) {
			c.addObserver(box);
		}
		for (Coordinate c: toRemove) {
			c.removeObserver(box);
		}
	}
	
	public void moveTo(CollisionBox box, int x, int y) {
		ArrayList<Coordinate> previous = new ArrayList<>();
		ArrayList<Coordinate> next = new ArrayList<>();
		for (int i = 0; i < box.getWidth(); i++) {
			for (int j = 0; j < box.getHeight(); j++) {
				previous.add(plane[box.getX() + i][box.getY() + j]);
			}
		}
		
		for (int i = 0; i < box.getWidth(); i++) {
			for (int j = 0; j < box.getHeight(); j++) {
				next.add(plane[x + i][y + j]);
			}
		}
		
		for (Coordinate c: previous) {
			if (!next.contains(c)) {
				c.removeObserver(box);
			}
		}
		for (Coordinate c: next) {
			if (!previous.contains(c)) {
				c.addObserver(box);
			}
		}
	}
	
	public ArrayList<Coordinate> getNewCoordinates(CollisionBox box, int dx, int dy) {
		int sign = dx >= 0 ? 1 : -1;
		ArrayList<Coordinate> toAdd = new ArrayList<>();
		for (int i = 0; i < Math.abs(dx); i++) {
			for (int j = 0; j < box.getHeight(); j++) {
				toAdd.add(plane[box.getX() + (sign > 0 ? box.getWidth() : 0) + sign * i][box.getY() + j]);
			}
		}
		
		sign = dy >= 0 ? 1 : -1;
		for (int i = 0; i < box.getWidth(); i++) {
			for (int j = 0; j < Math.abs(dy); j++) {
				Coordinate c = plane[box.getX() + i][box.getY() + (sign > 0 ? box.getHeight() : 0) + sign * j];
				if (!toAdd.contains(c)) {
					toAdd.add(c);
				}
				Coordinate r = plane[box.getX() + i][box.getY() + (sign > 0 ? 0 : box.getHeight()) + -1 * sign * j];
				if (toAdd.contains(r)) {
					toAdd.remove(r);
				}
			}
		}
		
		return toAdd;
		
	}
	
	public void add(CollisionBox box) {
		for (int x = 0; x < box.getWidth(); x++) {
			for (int y = 0; y < box.getHeight(); y++) {
				Coordinate coord = plane[x + box.getX()][y + box.getY()];
				coord.addObserver(box);
			}
		}
	}
	
	public void remove(CollisionBox box) {
		for (int x = 0; x < box.getWidth(); x++) {
			for (int y = 0; y < box.getHeight(); y++) {
				Coordinate coord = plane[x + box.getX()][y + box.getY()];
				coord.removeObserver(box);
			}
		}
	}
	
}
