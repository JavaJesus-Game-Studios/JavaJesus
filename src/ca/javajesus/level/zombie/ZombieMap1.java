package ca.javajesus.level.zombie;

import java.awt.Point;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.structures.CastleTower;
import ca.javajesus.game.entities.structures.CatholicChurch;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.vehicles.Boat;
import ca.javajesus.game.entities.vehicles.CenturyLeSabre;
import ca.javajesus.level.Level;

public class ZombieMap1 extends Level {

	public ZombieMap1() {
		super("/Levels/Test_Levels/zombies_test_map.png", true);
		this.spawnPoint = new Point(1360, 70);
	}

	protected void initNPCPlacement() {
		this.addEntity(Player.companion);

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new CenturyLeSabre(this, null, 1400, 70));
		this.addEntity(new Boat(this, null, 270, 858, 2, 100));
		this.addEntity(new CastleTower(this, 175, 1180));
		this.addEntity(new CatholicChurch(this, 1330, 1480));
		this.addEntity(new CaveEntrance(this, 700, 810));
		this.addEntity(new Spawner(this, 1360, 300, "Demon"));
		this.addEntity(new Spawner(this, 1350, 100, "Health"));

	}

}
