package level.story;

import java.util.ArrayList;

import game.entities.npcs.Knight;
import game.entities.npcs.NPC;
import game.entities.npcs.Peasant;
import game.entities.structures.Castle;
import game.entities.structures.CastleTower;
import game.entities.structures.CatholicChapel;
import game.entities.structures.CaveEntrance;
import game.entities.structures.GenericHospital;
import game.entities.structures.GunStore;
import game.entities.structures.Hotel;
import game.entities.structures.Hut;
import game.entities.structures.NiceHouse;
import game.entities.structures.NiceHouse2;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.MapTransporter;
import game.entities.structures.trees.Forest;
import game.entities.vehicles.CenturyLeSabre;
import game.entities.vehicles.Horse;
import game.entities.vehicles.SportsCar;
import game.entities.vehicles.Truck;
import items.Item;
import level.Level;
import utility.Direction;

public class LordHillsboroughsDomain extends Level {

	private static final long serialVersionUID = 636992239040959822L;
	
	public static final Level level = new LordHillsboroughsDomain();

	public LordHillsboroughsDomain() {
		super("/Levels/Cities/Domain of Lord Hillsborough.png", true,
				"Lord Hillsborough's Domain");
		setSpawnPoint(1366, 1450);
	}

	protected void initNPCPlacement() {
		add(new Horse(this, 181 * 8, 182 * 8, 200, Horse.WHITE));
		add(new CenturyLeSabre(this, 354 * 8, 182 * 8));
		add(new Truck(this, 360 * 8, 182 * 8));
		add(new SportsCar(this, 355 * 8, 200 * 8));

		// Peasants
		add(new Peasant(this, 80 * 8, 44 * 8, Peasant.MALE, "circle",
				10));
		add(new Peasant(this, 198 * 8, 44 * 8, Peasant.MALE,
				"circle", 12));
		add(new Peasant(this, 280 * 8, 45 * 8, Peasant.MALE, "cross",
				10));
		add(new Peasant(this, 72 * 8, 108 * 8, Peasant.MALE,
				"triangle", 10));
		add(new Peasant(this, 123 * 8, 174 * 8, Peasant.MALE,
				"triangle", 20));
		add(new Peasant(this, 142 * 8, 173 * 8, Peasant.MALE,
				"square", 10));
		add(new Peasant(this, 136 * 8, 176 * 8, Peasant.MALE,
				"square", 5));
		add(new Peasant(this, 120 * 8, 191 * 8, Peasant.MALE,
				"linear", 2));
		add(new Peasant(this, 139 * 8, 197 * 8, Peasant.MALE,
				"linear", 8));

		add(new Peasant(this, 96 * 8, 42 * 8, Peasant.FEMALE,
				"cross", 13));
		add(new Peasant(this, 212 * 8, 42 * 8, Peasant.FEMALE,
				"circle", 12));
		add(new Peasant(this, 296 * 8, 44 * 8, Peasant.FEMALE,
				"circle", 12));
		add(new Peasant(this, 88 * 8, 100 * 8, Peasant.FEMALE,
				"circle", 10));
		add(new Peasant(this, 139 * 8, 176 * 8, Peasant.FEMALE,
				"square", 10));
		add(new Peasant(this, 148 * 8, 174 * 8, Peasant.FEMALE,
				"square", 10));
		add(new Peasant(this, 154 * 8, 186 * 8, Peasant.FEMALE,
				"square", 10));
		add(new Peasant(this, 138 * 8, 196 * 8, Peasant.FEMALE,
				"linear", 2));
		add(new Peasant(this, 154 * 8, 200 * 8, Peasant.FEMALE,
				"linear", 8));

		add(new Peasant(this, 88 * 8, 56 * 8, Peasant.BOY, "circle",
				12));
		add(new Peasant(this, 96 * 8, 56 * 8, Peasant.GIRL, "circle",
				12));
		add(new Peasant(this, 98 * 8, 56 * 8, Peasant.BOY, "circle",
				4));
		add(new Peasant(this, 214 * 8, 56 * 8, Peasant.GIRL,
				"circle", 10));
		add(new Peasant(this, 280 * 8, 60 * 8, Peasant.BOY, "circle",
				2));

		// Citizens
		add(new NPC(this, "Citizen-Female", 232 * 8, 199 * 8, 1, 16,
				16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0,
				8, "linear", 30));
		add(new NPC(this, "Citizen-Male", 281 * 8, 197 * 8, 1, 16,
				16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0,
				0, "linear", 80));
		add(new NPC(this, "Citizen-Female", 232 * 8, 219 * 8, 1, 16,
				16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0,
				8, "linear", 30));
		add(new NPC(this, "Citizen-Male", 281 * 8, 217 * 8, 1, 16,
				16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0,
				0, "linear", 80));

		// Knights
		add(new Knight(this, 153 * 8, 183 * 8, 200, "linear", 40));
		add(new Knight(this, 221 * 8, 152 * 8, 200, "cross", 20));
		add(new Knight(this, 227 * 8, 151 * 8, 200, "cross", 20));

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {
		ArrayList<Item> contents = new ArrayList<Item>();
		contents.add(Item.shotgun);
		contents.add(Item.bazooka);
		for (int i = 0; i < 50; i++) {
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
		add(new Chest(this, 1366, 1466, contents));
	}

	protected void otherEntityPlacement() {

		// The Huts
		add(new Hut(this, 632, 288));
		add(new Hut(this, 1560, 288));
		add(new Hut(this, 632, 784));
		add(new Hut(this, 2290, 264));
		add(new Hut(this, 960, 1328));
		add(new Hut(this, 1112, 1328));
		add(new Hut(this, 1064, 1416));
		add(new Hut(this, 936, 1480));
		add(new Hut(this, 1088, 1528));

		// Knight's Tower
		add(new CastleTower(this, 1721, 1025));

		// Lord Hillsborough's Castle
		add(new Castle(this, 1208, 1240));

		// White Oaks
		add(new GenericHospital(this, 1734, 1656));
		add(new GunStore(this, 2160, 1694));
		add(new Hotel(this, 2208, 1840));
		add(new CatholicChapel(this, 1872, 1673));

		add(new NiceHouse2(this, 1928, 1508));
		add(new NiceHouse2(this, 2024, 1508));
		add(new NiceHouse2(this, 2120, 1508));
		add(new NiceHouse2(this, 1976, 1676));
		add(new NiceHouse2(this, 2072, 1676));
		add(new NiceHouse2(this, 2264, 1676));

		add(new NiceHouse(this, 1824, 1511));
		add(new NiceHouse(this, 2216, 1511));
		add(new NiceHouse(this, 1736, 1863));
		add(new NiceHouse(this, 1832, 1863));
		add(new NiceHouse(this, 1928, 1863));
		add(new NiceHouse(this, 2024, 1863));
		add(new NiceHouse(this, 2120, 1863));

		// Sequoia Shores
		add(new NiceHouse2(this, 3063, 308));
		add(new NiceHouse2(this, 2976, 448));
		add(new NiceHouse2(this, 3264, 448));

		add(new NiceHouse(this, 3160, 431));
		add(new NiceHouse(this, 2876, 431));

		// Caves
		add(new CaveEntrance(this, 1024, 40));
		add(new CaveEntrance(this, 744, 2208));
		add(new CaveEntrance(this, 3216, 1624));

		Forest.generateForest(this, 1360, 2040, 500, 500);
		Forest.generateForest(this, 2968, 1000, 256, 624);

	}

	protected void initMapTransporters() {

		add(new MapTransporter(this, 0, 0, SanCisco.level,
				Direction.NORTH, this.getWidth() * 8, 8));
		add(new MapTransporter(this, 0, 0,
				EdgeOfTheWoods.level, Direction.WEST, 8,
				this.getHeight() * 8));
		add(new MapTransporter(this, 0, (this.getHeight() * 8) - 8,
				BautistasDomain.level, Direction.SOUTH, (this.getWidth() * 8),
				8));

	}
}
