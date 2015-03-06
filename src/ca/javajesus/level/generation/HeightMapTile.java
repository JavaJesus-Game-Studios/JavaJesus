package ca.javajesus.level.generation;

public class HeightMapTile {
	private int tile;
	private boolean checkGround;
	private double probability;
	
	public HeightMapTile(int tile) {
		this.tile = tile;
		this.checkGround = false;
		this.probability = 0;
	}
	
	public int tile() {
		return tile;
	}
	
	public boolean groundCheck() {
		return checkGround;
	}
	
	public double probability() {
		return probability;
	}
	
	public void setTile(int tile) {
		this.tile = tile;
	}
	
	public void setGroundCheck(boolean check) {
		this.checkGround = check;
	}
	
	public void setProbability(double prob) {
		this.probability = 1000 - prob;
	}
}
