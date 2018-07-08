package javajesus.level.tile;

public class TileAdapter {
	
	private Tile tile;
	
	private int x, y;
	
	private boolean isOccupied;
	
	public TileAdapter(Tile t, int x, int y) {
		this.tile = t;
		
		this.x = x;
		this.y = y;
	}
	
	public boolean isOccupied() {
		return isOccupied;
	}
	
	public void setOccupied(boolean val) {
		isOccupied = val;
	}
	
	public boolean isSolid() {
		return tile.isSolid();
	}
	
	public boolean isWater() {
		return Tile.isWater(tile.getId());
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Object o) {
		TileAdapter t;
		if (!(o instanceof TileAdapter)) {
			return false;
		} else {
			t = (TileAdapter) o;
		}
		
		return this.x == t.getX() && this.y == t.getY();
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
