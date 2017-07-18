package javajesus.level.sandbox;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.Knight;
import javajesus.entities.npcs.NPC;
import javajesus.entities.npcs.Peasant;
import javajesus.entities.npcs.aggressive.Companion;
import javajesus.entities.solid.buildings.CastleTower;
import javajesus.entities.solid.buildings.CatholicChurch;
import javajesus.entities.solid.buildings.GunStore;
import javajesus.entities.solid.buildings.Hut;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.entities.vehicles.Boat;
import javajesus.entities.vehicles.CenturyLeSabre;
import javajesus.entities.vehicles.SportsCar;
import javajesus.entities.vehicles.Truck;
import javajesus.items.Item;
import javajesus.level.Level;

/*
 * Fixed level for sandbox mode
 */
public class SandboxOriginalLevel extends Level {

	// serialization
	private static final long serialVersionUID = 259027073546330929L;
	
	/**
	 * SandboxOriginalLevel ctor()
	 * 
	 * Creates a fixed sandbox map
	 */
	public SandboxOriginalLevel() {
		super("/Levels/Sandbox/original.png", true, "Original Map", new Point(1360, 70));
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
		
		return new Entity[] {  new Boat(this, null, 270, 858, 2, 100),
				new CastleTower(this, 175, 1180), new CatholicChurch(this, 1330, 1480),
				new GunStore(this, 700, 810), 
				
				// cars
				new CenturyLeSabre(this, 1400, 70),
				new CenturyLeSabre(this, 1567, 455), new CenturyLeSabre(this, 1567, 411),
				new Truck(this, 0, 455), new Truck(this, 0, 411), new SportsCar(this, 565, 518),
				
				//companion
				new Companion(this, 1274, 76, 500, new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 4),
				
				// Upper right of map
				new Spawner(this, 1369, 202, Spawner.DEMON), new Peasant(this, 1379, 264, Peasant.FEMALE),
				new Knight(this, 1470, 230, 50, "Linear", 50), new Spawner(this, 1076, 86, Spawner.DEMON),
				new Spawner(this, 1090, 248, Spawner.DEMON), new Spawner(this, 1316, 379, Spawner.DEMON),
				new Spawner(this, 1089, 571, Spawner.DEMON),new Spawner(this, 921, 395, Spawner.DEMON),
				
				// farmlands
				new Peasant(this, 703, 427, Peasant.MALE),new Peasant(this, 627, 363, Peasant.FEMALE),
				new Spawner(this, 900, 105, Spawner.MONKEY),new Spawner(this, 800, 105, Spawner.MONKEY),
				new Spawner(this, 700, 105, Spawner.MONKEY),new Spawner(this, 600, 105, Spawner.MONKEY),
				new Spawner(this, 500, 105, Spawner.MONKEY),new Spawner(this, 400, 105, Spawner.MONKEY),
				new Spawner(this, 300, 105, Spawner.MONKEY),new Spawner(this, 200, 105, Spawner.MONKEY),
				new Spawner(this, 150, 300, Spawner.MONKEY),new Knight(this, 614, 384, 50, "Circle", 50),
				
				// above river
				new Spawner(this, 1428, 723, Spawner.GANG_MEMBER),new Spawner(this, 200, 750, Spawner.GANG_MEMBER),
				new Spawner(this, 540, 680, Spawner.GANG_MEMBER),new Spawner(this, 1000, 575, Spawner.GANG_MEMBER),
				
				// below river
				new Spawner(this, 1440, 1060, Spawner.GANG_MEMBER),new Spawner(this, 1375, 1330, Spawner.GANG_MEMBER),
				
				// bottom of map
				new Spawner(this, 1040, 1410, Spawner.GANG_MEMBER),
				
				// near castle
				new Spawner(this, 646, 1416, Spawner.CENTAUR),new Spawner(this, 483, 1515, Spawner.CENTAUR),
				new Spawner(this, 314, 1044, Spawner.CENTAUR),new Spawner(this, 103, 1112, Spawner.CENTAUR),
				new Knight(this, 328, 1393, 50, "", 50), new Knight(this, 278, 1393, 50, "", 50),
				new Knight(this, 228, 1393, 50, "", 50),new Knight(this, 178, 1393, 50, "", 50),
				
				// near church
				new Peasant(this, 1365, 1570, Peasant.GIRL),
				
				//cyclops spawner
				new Spawner(this, 1092, 888, Spawner.CYCLOPS), new Spawner(this, 1088, 1271, Spawner.CYCLOPS),
				new Spawner(this, 745, 1115, Spawner.CYCLOPS),
				
				new Hut(this, 687, 300), new Chest(this, 1460, 70, Item.revolver, Item.shortSword)

		};
	}

	protected MapTransporter[] getMapTransporterPlacement() {
		return null;
	}

}
