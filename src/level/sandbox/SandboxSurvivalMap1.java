package level.sandbox;

import game.entities.Spawner;
import game.entities.structures.CastleTower;
import game.entities.structures.CatholicChurch;
import game.entities.structures.CaveEntrance;
import game.entities.structures.Hut;
import game.entities.structures.furniture.Chest;
import game.entities.vehicles.Boat;
import game.entities.vehicles.CenturyLeSabre;
import items.Item;
import level.Level;

public class SandboxSurvivalMap1 extends Level {

	private static final long serialVersionUID = 259027073546330929L;

	public SandboxSurvivalMap1() {
		super("/Levels/Test_Levels/zombies_test_map.png", true, "Zombie Map");
		setSpawnPoint(1360, 70);
	}

	protected void initNPCPlacement() {
		// add(Player.companion);

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new CenturyLeSabre(this, 1400, 70));
		add(new Boat(this, null, 270, 858, 2, 100));
		add(new CastleTower(this, 175, 1180));
		add(new CatholicChurch(this, 1330, 1480));
		add(new CaveEntrance(this, 700, 810));
		add(new Spawner(this, 1360, 300, Spawner.DEMON));
		add(new Spawner(this, 1180, 160, Spawner.DEMON));
		add(new Spawner(this, 130, 34, Spawner.DEMON));
		add(new Spawner(this, 88, 644, Spawner.DEMON));
		add(new Spawner(this, 96, 1477, Spawner.DEMON));
		add(new Spawner(this, 840, 1155, Spawner.DEMON));
		add(new Spawner(this, 1475, 1433, Spawner.DEMON));
		add(new Spawner(this, 1097, 890, Spawner.DEMON));
		add(new Spawner(this, 1350, 100, Spawner.DEMON));
		add(new Hut(this, 687, 300));
		add(new Chest(this, 1460, 70, Item.revolver));
	}

	@Override
	protected void initMapTransporters() {
		// TODO Auto-generated method stub

	}

}
