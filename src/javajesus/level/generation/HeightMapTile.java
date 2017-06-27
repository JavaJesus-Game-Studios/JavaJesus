package javajesus.level.generation;

import java.io.Serializable;

public class HeightMapTile implements Serializable {
	
	private static final long serialVersionUID = 4994549437552769494L;
	
	private int tile;
	private boolean checkGround;
	private double probability;
	private boolean house;
	private boolean cave;
	private boolean spawner;
	
	public HeightMapTile(int tile) {
		this.tile = tile;
		this.checkGround = false;
		this.probability = 0;
		this.house = false;
		this.cave = false;
		this.spawner = false;
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
	
	public boolean getCave() {
		return cave;
	}
	
	public boolean getSpawner() {
		return spawner;
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
	
	public void setCave() {
		cave = true;
	}
	
	public void setSpawner() {
		spawner = true;
	}
}
