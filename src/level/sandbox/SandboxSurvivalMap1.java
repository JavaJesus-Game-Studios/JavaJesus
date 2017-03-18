package level.sandbox;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.CastleTower;
import game.entities.structures.CatholicChurch;
import game.entities.structures.CaveEntrance;
import game.entities.structures.Hut;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.MapTransporter;
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

	protected NPC[] getNPCPlacement() {
		return null;
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return null;
	}

	protected Entity[] getOtherPlacement() {
		
		return new Entity[] { new CenturyLeSabre(this, 1400, 70), new Boat(this, null, 270, 858, 2, 100),
				new CastleTower(this, 175, 1180), new CatholicChurch(this, 1330, 1480),
				new CaveEntrance(this, 700, 810), new Spawner(this, 1360, 300, Spawner.DEMON),
				new Spawner(this, 1180, 160, Spawner.DEMON), new Spawner(this, 130, 34, Spawner.DEMON),
				new Spawner(this, 88, 644, Spawner.DEMON), new Spawner(this, 96, 1477, Spawner.DEMON),
				new Spawner(this, 840, 1155, Spawner.DEMON), new Spawner(this, 1475, 1433, Spawner.DEMON),
				new Spawner(this, 1097, 890, Spawner.DEMON), new Spawner(this, 1350, 100, Spawner.DEMON),
				new Spawner(this, 638, 110, Spawner.DEMON), new Spawner(this, 253, 496, Spawner.DEMON),
				new Spawner(this, 806, 666, Spawner.DEMON),new Spawner(this, 861, 1491, Spawner.DEMON),
				new Spawner(this, 223, 1041, Spawner.DEMON), new Spawner(this, 1205, 1197, Spawner.DEMON),
				new Spawner(this, 1508, 968, Spawner.DEMON),new Spawner(this, 1435, 653, Spawner.DEMON),
				new Hut(this, 687, 300), new Chest(this, 1460, 70, Item.revolver, Item.shortSword)

		};
	}

	protected MapTransporter[] getMapTransporterPlacement() {
		return null;
	}

}
