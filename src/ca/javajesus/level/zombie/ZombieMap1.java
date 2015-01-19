package ca.javajesus.level.zombie;

import java.awt.Point;
import java.util.Random;

import ca.javajesus.game.entities.monsters.Demon;
import ca.javajesus.game.entities.monsters.Monster;
import ca.javajesus.game.entities.structures.CastleTower;
import ca.javajesus.game.entities.structures.CatholicChurch;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.vehicles.Boat;
import ca.javajesus.game.entities.vehicles.CenturyLeSabre;
import ca.javajesus.level.Level;

public class ZombieMap1 extends Level{
	
	private static final Random random = new Random();


	public ZombieMap1() {
		super("/Levels/zombies_test_map.png");
	}
	
	public Point spawnPoint() {
		return new Point(1360, 70);
	}

	protected void initNPCPlacement() {
		for (int i = 0; i < 3; i++) {
			this.addSpawner(random.nextInt(this.width * 8),
					random.nextInt(this.height * 8), 2);
		}
		
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
		this.addEntity(new Demon(this, null, height, height, 100));



		
	
		
	}

}
