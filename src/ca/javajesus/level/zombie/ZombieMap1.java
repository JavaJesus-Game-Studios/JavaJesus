package ca.javajesus.level.zombie;

import java.awt.Point;

import ca.javajesus.game.entities.structures.CastleTower;
import ca.javajesus.game.entities.structures.CatholicChurch;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.vehicles.Boat;
import ca.javajesus.game.entities.vehicles.CenturyLeSabre;
import ca.javajesus.level.Level;

public class ZombieMap1 extends Level{

	public ZombieMap1() {
		super("/Levels/zombies_test_map.png");
	}
	
	public Point spawnPoint() {
		return new Point(1360, 70);
	}

	protected void initNPCPlacement() {
		
	}

	protected void initSpawnerPlacement() {
		
	}

	protected void initChestPlacement() {
		
	}

	protected void otherEntityPlacement() {
		this.addEntity(new CenturyLeSabre(this, null, 1400, 70, 3, 100));
		this.addEntity(new Boat(this, null, 270, 858, 2, 100));
		this.addEntity(new CastleTower(this, 175, 1180));
		this.addEntity(new CatholicChurch(this, 1330, 1480));
		this.addEntity(new CaveEntrance(this, 700, 810));



		
	
		
	}

}
