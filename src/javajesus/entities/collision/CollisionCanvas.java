package javajesus.entities.collision;

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
	 * TODO: Will lead to strange adds/removes when diagonal, need to adjust for that
	 * @param box
	 * @param dx
	 * @param dy
	 */
	public void move(CollisionBox box, int dx, int dy) {
		int sign = dx >= 0 ? 1 : -1;
		for (int i = 0; i < Math.abs(dx); i++) {
			for (int j = 0; j < box.getHeight(); j++) {
				plane[box.getX() + (sign > 0 ? box.getWidth() : 0) + sign * i][box.getY() + j].addObserver(box);
				plane[box.getX() + (sign > 0 ? 0 : box.getWidth()) + -1 * sign * i][box.getY() + j].removeObserver(box);
			}
		}
		
		sign = dy >= 0 ? 1 : -1;
		for (int i = 0; i < box.getWidth(); i++) {
			for (int j = 0; j < Math.abs(dy); j++) {
				plane[box.getX() + i][box.getY() + (sign > 0 ? box.getHeight() : 0) + sign * j].addObserver(box);
				plane[box.getX() + i][box.getY() + (sign > 0 ? 0 : box.getHeight()) + -1 * sign * j].removeObserver(box);
			}
		}
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
