package level.sandbox;

import game.entities.Player;
import game.entities.Spawner;
import game.entities.npcs.Jesus;
import game.entities.structures.CastleTower;
import game.entities.structures.CatholicChurch;
import game.entities.structures.CaveEntrance;
import game.entities.structures.Hut;
import game.entities.structures.furniture.Chest;
import game.entities.vehicles.Boat;
import game.entities.vehicles.CenturyLeSabre;
import items.Item;

import java.awt.Point;
import java.util.ArrayList;

import level.Level;

public class SandboxSurvivalMap1 extends Level {

	private static final long serialVersionUID = 259027073546330929L;

	public SandboxSurvivalMap1() {
		super("/Levels/Test_Levels/zombies_test_map.png", true, "Zombie Map");
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
		this.addEntity(new CenturyLeSabre(this, 1400, 70));
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
		this.addEntity(new Hut(this, 473, 300, new Jesus(this, 300, 400, "stand", 30)));
		this.addEntity(new Hut(this, 687, 300));
		this.addEntity(new Hut(this, 900, 300, new Jesus(this, 300, 400, "stand", 30)));
		ArrayList<Item> chest1 = new ArrayList<Item>();
		chest1.add(Item.revolver);
		this.addEntity(new Chest(this, 1460, 70, chest1));
	}

	@Override
	protected void initMapTransporters() {
		// TODO Auto-generated method stub
		
	}

}
