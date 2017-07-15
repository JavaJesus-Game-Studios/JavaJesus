package javajesus.level.story;

import java.awt.Point;
import java.util.ArrayList;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.Knight;
import javajesus.entities.npcs.NPC;
import javajesus.entities.npcs.Peasant;
import javajesus.entities.solid.buildings.Castle;
import javajesus.entities.solid.buildings.CastleTower;
import javajesus.entities.solid.buildings.CatholicChapel;
import javajesus.entities.solid.buildings.CaveEntrance;
import javajesus.entities.solid.buildings.GenericHospital;
import javajesus.entities.solid.buildings.GunStore;
import javajesus.entities.solid.buildings.Hotel;
import javajesus.entities.solid.buildings.Hut;
import javajesus.entities.solid.buildings.NiceHouse;
import javajesus.entities.solid.buildings.NiceHouse2;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.solid.trees.Forest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.entities.vehicles.CenturyLeSabre;
import javajesus.entities.vehicles.Horse;
import javajesus.entities.vehicles.SportsCar;
import javajesus.entities.vehicles.Truck;
import javajesus.items.Item;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class LordHillsboroughsDomain extends Level {

	private static final long serialVersionUID = 636992239040959822L;

	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates Lord Hillsborough's Domain
	 */
	public LordHillsboroughsDomain() {
		super("/Levels/Cities/Domain of Lord Hillsborough.png", true, Level.HILLSBOROUGH, new Point(1366, 1450));

		System.err.println("Creating Lord Hillsborough's Domain");
	}

	protected NPC[] getNPCPlacement() {

		return new NPC[] { new Horse(this, 181 * 8, 182 * 8, 200, Horse.WHITE),

				// Peasants
				new Peasant(this, 80 * 8, 44 * 8, Peasant.MALE, "circle", 10),
				new Peasant(this, 198 * 8, 44 * 8, Peasant.MALE, "circle", 12),
				new Peasant(this, 280 * 8, 45 * 8, Peasant.MALE, "cross", 10),
				new Peasant(this, 72 * 8, 108 * 8, Peasant.MALE, "triangle", 10),
				new Peasant(this, 123 * 8, 174 * 8, Peasant.MALE, "triangle", 20),
				new Peasant(this, 142 * 8, 173 * 8, Peasant.MALE, "square", 10),
				new Peasant(this, 136 * 8, 176 * 8, Peasant.MALE, "square", 5),
				new Peasant(this, 120 * 8, 191 * 8, Peasant.MALE, "linear", 2),
				new Peasant(this, 139 * 8, 197 * 8, Peasant.MALE, "linear", 8),

				new Peasant(this, 96 * 8, 42 * 8, Peasant.FEMALE, "cross", 13),
				new Peasant(this, 212 * 8, 42 * 8, Peasant.FEMALE, "circle", 12),
				new Peasant(this, 296 * 8, 44 * 8, Peasant.FEMALE, "circle", 12),
				new Peasant(this, 88 * 8, 100 * 8, Peasant.FEMALE, "circle", 10),
				new Peasant(this, 139 * 8, 176 * 8, Peasant.FEMALE, "square", 10),
				new Peasant(this, 148 * 8, 174 * 8, Peasant.FEMALE, "square", 10),
				new Peasant(this, 154 * 8, 186 * 8, Peasant.FEMALE, "square", 10),
				new Peasant(this, 138 * 8, 196 * 8, Peasant.FEMALE, "linear", 2),
				new Peasant(this, 154 * 8, 200 * 8, Peasant.FEMALE, "linear", 8),

				new Peasant(this, 88 * 8, 56 * 8, Peasant.BOY, "circle", 12),
				new Peasant(this, 96 * 8, 56 * 8, Peasant.GIRL, "circle", 12),
				new Peasant(this, 98 * 8, 56 * 8, Peasant.BOY, "circle", 4),
				new Peasant(this, 214 * 8, 56 * 8, Peasant.GIRL, "circle", 10),
				new Peasant(this, 280 * 8, 60 * 8, Peasant.BOY, "circle", 2),

				new Peasant(this, 138 * 8, 196 * 8, Peasant.FEMALE, "linear", 10),

				// Citizens
				new NPC(this, "Citizen-Female", 232 * 8, 199 * 8, 1, 16, 16, 100,
						new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0, 8, "linear", 30),
				new NPC(this, "Citizen-Male", 281 * 8, 197 * 8, 1, 16, 16, 100,
						new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0, 0, "linear", 80),
				new NPC(this, "Citizen-Female", 232 * 8, 219 * 8, 1, 16, 16, 100,
						new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0, 8, "linear", 30),
				new NPC(this, "Citizen-Male", 281 * 8, 217 * 8, 1, 16, 16, 100,
						new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0, 0, "linear", 80),

				// Knights
				new Knight(this, 153 * 8, 183 * 8, 200, "linear", 40),
				new Knight(this, 221 * 8, 152 * 8, 200, "cross", 20),
				new Knight(this, 227 * 8, 151 * 8, 200, "cross", 20) };

	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {

		ArrayList<Item> contents = new ArrayList<Item>();
		contents.add(Item.revolver);
		contents.add(Item.shotgun);
		contents.add(Item.bazooka);
		contents.add(Item.laserRevolver);
		contents.add(Item.assaultRifle);
		contents.add(Item.crossBow);
		contents.add(Item.longSword);
		contents.add(Item.claymore);
		contents.add(Item.sabre);
		contents.add(Item.heavenlySword);
		contents.add(Item.heavenlyShortSword);
		for (int i = 0; i < 10; i++) {
			contents.add(Item.arrowAmmo);
			contents.add(Item.assaultRifleAmmo);
			contents.add(Item.laserAmmo);
			contents.add(Item.revolverAmmo);
			contents.add(Item.shotgunAmmo);
		}
		contents.add(Item.horned);
		contents.add(Item.knight);
		contents.add(Item.vest);
		contents.add(Item.owl);

		return new Chest[] { new Chest(this, 1366, 1466, contents) };
	}

	protected Entity[] getOtherPlacement() {

		Forest.generateForest(this, 1360, 2040, 500, 500);
		Forest.generateForest(this, 2968, 1000, 256, 624);

		return new Entity[] {

				// cars
				new CenturyLeSabre(this, 354 * 8, 182 * 8), new Truck(this, 360 * 8, 182 * 8),
				new SportsCar(this, 355 * 8, 200 * 8),

				// The Huts
				new Hut(this, 632, 288), new Hut(this, 1560, 288), new Hut(this, 632, 784), new Hut(this, 2290, 264),
				new Hut(this, 960, 1328), new Hut(this, 1112, 1328), new Hut(this, 1064, 1416),
				new Hut(this, 936, 1480), new Hut(this, 1088, 1528),

				// Knight's Tower
				new CastleTower(this, 1721, 1025),

				// Lord Hillsborough's Castle
				new Castle(this, 1208, 1240),

				// White Oaks
				new GenericHospital(this, 1734, 1656), new GunStore(this, 2160, 1694), new Hotel(this, 2208, 1840),
				new CatholicChapel(this, 1872, 1673),

				new NiceHouse2(this, 1928, 1508), new NiceHouse2(this, 2024, 1508), new NiceHouse2(this, 2120, 1508),
				new NiceHouse2(this, 1976, 1676), new NiceHouse2(this, 2072, 1676), new NiceHouse2(this, 2264, 1676),

				new NiceHouse(this, 1824, 1511), new NiceHouse(this, 2216, 1511), new NiceHouse(this, 1736, 1863),
				new NiceHouse(this, 1832, 1863), new NiceHouse(this, 1928, 1863), new NiceHouse(this, 2024, 1863),
				new NiceHouse(this, 2120, 1863),

				// Sequoia Shores
				new NiceHouse2(this, 3063, 308), new NiceHouse2(this, 2976, 448), new NiceHouse2(this, 3264, 448),

				new NiceHouse(this, 3160, 431), new NiceHouse(this, 2876, 431),

				// Caves
				new CaveEntrance(this, 1024, 40), new CaveEntrance(this, 744, 2208),
				new CaveEntrance(this, 3216, 1624) };

	}

	/**
	 * Edges of the map
	 */
	protected MapTransporter[] getMapTransporterPlacement() {

		return new MapTransporter[] {

				new MapTransporter(this, 0, 0, Level.CISCO, Direction.NORTH, this.getWidth() * 8, 8),
				new MapTransporter(this, 0, 0, Level.EDGE_MAIN, Direction.WEST, 8, this.getHeight() * 8),
				new MapTransporter(this, 0, (this.getHeight() * 8) - 8, Level.BAUTISTA, Direction.SOUTH,
						(this.getWidth() * 8), 8) };

	}
}
