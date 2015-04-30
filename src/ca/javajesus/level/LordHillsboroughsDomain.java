package ca.javajesus.level;

import java.awt.Point;
import java.util.ArrayList;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.entities.npcs.NPC;
import ca.javajesus.game.entities.npcs.Peasant;
import ca.javajesus.game.entities.npcs.NPC.Gender;
import ca.javajesus.game.entities.npcs.aggressive.Knight;
import ca.javajesus.game.entities.structures.Castle;
import ca.javajesus.game.entities.structures.CastleTower;
import ca.javajesus.game.entities.structures.CatholicChapel;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.structures.GenericHospital;
import ca.javajesus.game.entities.structures.GunStore;
import ca.javajesus.game.entities.structures.Hotel;
import ca.javajesus.game.entities.structures.Hut;
import ca.javajesus.game.entities.structures.NiceHouse;
import ca.javajesus.game.entities.structures.NiceHouse2;
import ca.javajesus.game.entities.structures.furniture.Chest;
import ca.javajesus.game.entities.structures.transporters.MapTransporter;
import ca.javajesus.game.entities.structures.trees.Forest;
import ca.javajesus.game.entities.vehicles.CenturyLeSabre;
import ca.javajesus.game.entities.vehicles.Horse;
import ca.javajesus.game.entities.vehicles.SportsCar;
import ca.javajesus.game.entities.vehicles.Truck;
import ca.javajesus.items.Item;

public class LordHillsboroughsDomain extends Level {

	private static final long serialVersionUID = 636992239040959822L;

	public LordHillsboroughsDomain() {
		super("/Levels/Cities/Domain of Lord Hillsborough.png", true,
				"Lord Hillsborough's Domain");
		this.spawnPoint = new Point(1366, 1450);
		startingSpawnPoint = new Point(1366, 1450);
	}

	protected void initNPCPlacement() {
		this.addEntity(new Horse(this, 181 * 8, 182 * 8, 0));
		this.addEntity(new CenturyLeSabre(this, 354 * 8, 182 * 8));
		this.addEntity(new Truck(this, 360*8, 182*8));
		this.addEntity(new SportsCar(this, 355*8, 200*8));

		// Peasants
		this.addEntity(new Peasant(this, 80 * 8, 44 * 8, Gender.MALE, "circle",
				10));
		this.addEntity(new Peasant(this, 198 * 8, 44 * 8, Gender.MALE,
				"circle", 12));
		this.addEntity(new Peasant(this, 280 * 8, 45 * 8, Gender.MALE, "cross",
				10));
		this.addEntity(new Peasant(this, 72 * 8, 108 * 8, Gender.MALE,
				"triangle", 10));
		this.addEntity(new Peasant(this, 123 * 8, 174 * 8, Gender.MALE,
				"triangle", 20));
		this.addEntity(new Peasant(this, 142 * 8, 173 * 8, Gender.MALE,
				"square", 10));
		this.addEntity(new Peasant(this, 136 * 8, 176 * 8, Gender.MALE,
				"square", 5));
		this.addEntity(new Peasant(this, 120 * 8, 191 * 8, Gender.MALE,
				"linear", 2));
		this.addEntity(new Peasant(this, 139 * 8, 197 * 8, Gender.MALE,
				"linear", 8));

		this.addEntity(new Peasant(this, 96 * 8, 42 * 8, Gender.FEMALE,
				"cross", 13));
		this.addEntity(new Peasant(this, 212 * 8, 42 * 8, Gender.FEMALE,
				"circle", 12));
		this.addEntity(new Peasant(this, 296 * 8, 44 * 8, Gender.FEMALE,
				"circle", 12));
		this.addEntity(new Peasant(this, 88 * 8, 100 * 8, Gender.FEMALE,
				"circle", 10));
		this.addEntity(new Peasant(this, 139 * 8, 176 * 8, Gender.FEMALE,
				"square", 10));
		this.addEntity(new Peasant(this, 148 * 8, 174 * 8, Gender.FEMALE,
				"square", 10));
		this.addEntity(new Peasant(this, 154 * 8, 186 * 8, Gender.FEMALE,
				"square", 10));
		this.addEntity(new Peasant(this, 138 * 8, 196 * 8, Gender.FEMALE,
				"linear", 2));
		this.addEntity(new Peasant(this, 154 * 8, 200 * 8, Gender.FEMALE,
				"linear", 8));

		this.addEntity(new Peasant(this, 88 * 8, 56 * 8, Gender.BOY, "circle",
				12));
		this.addEntity(new Peasant(this, 96 * 8, 56 * 8, Gender.GIRL, "circle",
				12));
		this.addEntity(new Peasant(this, 98 * 8, 56 * 8, Gender.BOY, "circle",
				4));
		this.addEntity(new Peasant(this, 214 * 8, 56 * 8, Gender.GIRL,
				"circle", 10));
		this.addEntity(new Peasant(this, 280 * 8, 60 * 8, Gender.BOY, "circle",
				2));


		// Citizens
		this.addEntity(new NPC(this, "Citizen-Female", 232 * 8, 199 * 8, 1, 16,
				16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0,
				8, "linear", 30));
		this.addEntity(new NPC(this, "Citizen-Male", 281 * 8, 197 * 8, 1, 16,
				16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0,
				0, "linear", 80));
		this.addEntity(new NPC(this, "Citizen-Female", 232 * 8, 219 * 8, 1, 16,
				16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0,
				8, "linear", 30));
		this.addEntity(new NPC(this, "Citizen-Male", 281 * 8, 217 * 8, 1, 16,
				16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0,
				0, "linear", 80));

		// Knights
		this.addEntity(new Knight(this, 153 * 8, 183 * 8, "linear", 40));
		this.addEntity(new Knight(this, 221 * 8, 152 * 8, "cross", 20));
		this.addEntity(new Knight(this, 227 * 8, 151 * 8, "cross", 20));

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {
		ArrayList<Item> chest1 = new ArrayList<Item>();
		chest1.add(Item.shotgun);
		chest1.add(Item.bazooka);
		chest1.add(Item.arrowAmmo);
		chest1.add(Item.assaultRifleAmmo);
		chest1.add(Item.laserAmmo);
		chest1.add(Item.revolverAmmo);
		chest1.add(Item.shotgunAmmo);
		chest1.add(Item.horned);
		chest1.add(Item.knight);
		chest1.add(Item.vest);
		chest1.add(Item.owl);
		this.addEntity(new Chest(this, 1366, 1466, chest1));
	}

	protected void otherEntityPlacement() {

		// The Huts
		this.addEntity(new Hut(this, 632, 288));
		this.addEntity(new Hut(this, 1560, 288));
		this.addEntity(new Hut(this, 632, 784));
		this.addEntity(new Hut(this, 2290, 264));
		this.addEntity(new Hut(this, 960, 1328));
		this.addEntity(new Hut(this, 1112, 1328));
		this.addEntity(new Hut(this, 1064, 1416));
		this.addEntity(new Hut(this, 936, 1480));
		this.addEntity(new Hut(this, 1088, 1528));

		// Knight's Tower
		this.addEntity(new CastleTower(this, 1721, 1025));

		// Lord Hillsborough's Castle
		this.addEntity(new Castle(this, 1208, 1240));

		// White Oaks
		this.addEntity(new GenericHospital(this, 1734, 1656));
		this.addEntity(new GunStore(this, 2160, 1694));
		this.addEntity(new Hotel(this, 2208, 1840));
		this.addEntity(new CatholicChapel(this, 1872, 1673));

		this.addEntity(new NiceHouse2(this, 1928, 1508));
		this.addEntity(new NiceHouse2(this, 2024, 1508));
		this.addEntity(new NiceHouse2(this, 2120, 1508));
		this.addEntity(new NiceHouse2(this, 1976, 1676));
		this.addEntity(new NiceHouse2(this, 2072, 1676));
		this.addEntity(new NiceHouse2(this, 2264, 1676));

		this.addEntity(new NiceHouse(this, 1824, 1511));
		this.addEntity(new NiceHouse(this, 2216, 1511));
		this.addEntity(new NiceHouse(this, 1736, 1863));
		this.addEntity(new NiceHouse(this, 1832, 1863));
		this.addEntity(new NiceHouse(this, 1928, 1863));
		this.addEntity(new NiceHouse(this, 2024, 1863));
		this.addEntity(new NiceHouse(this, 2120, 1863));

		// Sequoia Shores
		this.addEntity(new NiceHouse2(this, 3063, 308));
		this.addEntity(new NiceHouse2(this, 2976, 448));
		this.addEntity(new NiceHouse2(this, 3264, 448));

		this.addEntity(new NiceHouse(this, 3160, 431));
		this.addEntity(new NiceHouse(this, 2876, 431));

		// Caves
		this.addEntity(new CaveEntrance(this, 1024, 40));
		this.addEntity(new CaveEntrance(this, 744, 2208));
		this.addEntity(new CaveEntrance(this, 3216, 1624));

		this.addEntity(new Forest(this, 1360, 2040, 500, 500));
		this.addEntity(new Forest(this, 2968, 1000, 256, 624));

	}

	protected void initMapTransporters() {

		this.addEntity(new MapTransporter(this, 0, 0, Game.levels.sanCisco,
				Direction.NORTH, this.width * 8, 8));
		this.addEntity(new MapTransporter(this, 0, 0,
				Game.levels.edgeOfTheWoodsMain, Direction.WEST, 8, this.height * 8));
		this.addEntity(new MapTransporter(this, 0, (this.height * 8) - 8,
				Game.levels.bautistasDomain, Direction.SOUTH,
				(this.width * 8), 8));

	}
}
