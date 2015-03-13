package ca.javajesus.level.zombie;

import java.awt.Point;
import java.util.ArrayList;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.structures.CastleTower;
import ca.javajesus.game.entities.structures.CatholicChurch;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.structures.Hut;
import ca.javajesus.game.entities.structures.Chest;
import ca.javajesus.game.entities.vehicles.Boat;
import ca.javajesus.game.entities.vehicles.CenturyLeSabre;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.items.Gun;
import ca.javajesus.items.Item;
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
		this.addEntity(new Spawner(this, 1180, 160, "Demon"));
		this.addEntity(new Spawner(this, 1350, 100, "Health"));
		this.addEntity(new Hut(this, 45, 300));
		this.addEntity(new Hut(this, 259, 300));
		this.addEntity(new Hut(this, 473, 300));
		this.addEntity(new Hut(this, 687, 300));
		this.addEntity(new Hut(this, 900, 300));
		ArrayList<Item> chest1 = new ArrayList<Item>();
		chest1.add(new Gun("Revolver", 4, 0, 0, Colors.get(-1, 500, 500,
                    Colors.fromHex("#FF0000")), "Standard Firearm", 0, 0, 6,
                    10, 20, 50));
		this.addEntity(new Chest(this, 1460, 70, chest1));
	}

}
