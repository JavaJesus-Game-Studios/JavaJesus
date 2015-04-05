package ca.javajesus.level.zombie;

import java.awt.Point;
import java.util.ArrayList;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.npcs.NPC;
import ca.javajesus.game.entities.structures.CastleTower;
import ca.javajesus.game.entities.structures.CatholicChurch;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.structures.Hut;
import ca.javajesus.game.entities.structures.furniture.Chest;
import ca.javajesus.game.entities.vehicles.Boat;
import ca.javajesus.game.entities.vehicles.CenturyLeSabre;
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
		this.addEntity(new Spawner(this, 130, 34, "Demon"));
		this.addEntity(new Spawner(this, 88, 644, "Demon"));
		this.addEntity(new Spawner(this, 96, 1477, "Demon"));
		this.addEntity(new Spawner(this, 840, 1155, "Demon"));
		this.addEntity(new Spawner(this, 1475, 1433, "Demon"));
		this.addEntity(new Spawner(this, 1097, 890, "Demon"));
		this.addEntity(new Spawner(this, 1350, 100, "Health"));
		ArrayList<Item> chest2 = new ArrayList<Item>();
		chest2.add(Item.assaultRifle);
		ArrayList<Item> chest3 = new ArrayList<Item>();
		chest3.add(Item.shortSword);
		this.addEntity(new Hut(this, 259, 300,
				new Chest(this, 272, 232, chest3), new Spawner(this, 252, 252,
						"Demon")));
		this.addEntity(new Hut(this, 473, 300, NPC.Jesus));
		this.addEntity(new Hut(this, 687, 300));
		this.addEntity(new Hut(this, 900, 300, NPC.citizenMale));
		ArrayList<Item> chest1 = new ArrayList<Item>();
		chest1.add(Item.revolver);
		this.addEntity(new Chest(this, 1460, 70, chest1));
	}

}
