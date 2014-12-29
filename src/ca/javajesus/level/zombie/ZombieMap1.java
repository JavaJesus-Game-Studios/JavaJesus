package ca.javajesus.level.zombie;

import java.awt.Point;

import ca.javajesus.game.entities.structures.PoorHouse;
import ca.javajesus.level.Level;

public class ZombieMap1 extends Level{

	public ZombieMap1() {
		super("/Levels/zombies_test_map.png");
	}
	
	public Point spawnPoint() {
		return new Point(555, 765);
	}

	protected void initNPCPlacement() {
		
	}

	protected void initSpawnerPlacement() {
		
	}

	protected void initChestPlacement() {
		
	}

	protected void otherEntityPlacement() {
		
		this.addEntity(new PoorHouse(this, 70, 765));
		this.addEntity(new PoorHouse(this, 170, 765));
		this.addEntity(new PoorHouse(this, 270, 765));
		this.addEntity(new PoorHouse(this, 370, 765));
		this.addEntity(new PoorHouse(this, 470, 765));
		for (int i = 600; i <= 3100; i += 100) {
			this.addEntity(new PoorHouse(this, i, 765));
		}
		
	}

}
