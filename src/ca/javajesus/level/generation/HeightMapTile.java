package ca.javajesus.level.generation;

public class HeightMapTile {
	private int tile;
	private boolean checkGround;
	private double probability;
	private boolean house;
	
	public HeightMapTile(int tile) {
		this.tile = tile;
		this.checkGround = false;
		this.probability = 0;
		this.house = false;
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
	
	public boolean getHouse() {
		return house;
	}
	
	public void setTile(int tile) {
		this.tile = tile;
	}
	
	public void setGroundCheck(boolean check) {
		this.checkGround = check;
	}
	
	public void setProbability(double prob) {
		this.probability = prob;
	}
	
	public void setHouse() {
		house = true;
	}
}
