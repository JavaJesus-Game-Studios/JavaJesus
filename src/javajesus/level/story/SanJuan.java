package javajesus.level.story;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.ApartmentHighRise;
import javajesus.entities.structures.CatholicChapel;
import javajesus.entities.structures.CatholicChurch;
import javajesus.entities.structures.GenericHospital;
import javajesus.entities.structures.Hotel;
import javajesus.entities.structures.NiceHouse;
import javajesus.entities.structures.NiceHouse2;
import javajesus.entities.structures.Police;
import javajesus.entities.structures.PoorHouse;
import javajesus.entities.structures.Projects;
import javajesus.entities.structures.RancheroHouse;
import javajesus.entities.structures.RussianOrthodoxChurch;
import javajesus.entities.structures.Warehouse;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.sanjuan.JungleHQ;
import javajesus.entities.structures.sanjuan.QuackerHQ;
import javajesus.entities.structures.sanjuan.SanJuanCityHall;
import javajesus.entities.structures.sanjuan.TheHub;
import javajesus.entities.structures.transporters.MapTransporter;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A Parody of San Jose
 */
public class SanJuan extends Level {

	private static final long serialVersionUID = -1623870972641933936L;

	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates San Juan
	 */
	public SanJuan() {
		super("/Levels/Cities/San_Juan.png", true, Level.JUAN);
		setSpawnPoint(1680, 2648);

		System.err.println("Creating San Juan");

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

		return new Entity[] {

				// housing
				new ApartmentHighRise(this, 1976, 1624), new ApartmentHighRise(this, 2216, 2080),
				new ApartmentHighRise(this, 2360, 2080), new Hotel(this, 2096, 1768), new NiceHouse(this, 1104, 2816),
				new NiceHouse(this, 480, 2296), new NiceHouse(this, 504, 2400), new NiceHouse(this, 536, 2520),
				new NiceHouse(this, 552, 2632), new NiceHouse(this, 616, 2832), new NiceHouse(this, 848, 2456),
				new NiceHouse(this, 872, 2816), new NiceHouse(this, 1056, 2496), new NiceHouse(this, 1104, 2816),
				new NiceHouse(this, 1248, 2712), new NiceHouse(this, 1272, 2456), new NiceHouse(this, 1464, 2560),
				new NiceHouse(this, 1536, 2816), new NiceHouse(this, 1584, 2552), new NiceHouse(this, 1752, 2584),
				new NiceHouse(this, 2024, 2728), new NiceHouse(this, 2096, 2816), new NiceHouse(this, 2224, 2696),
				new NiceHouse(this, 2264, 2592), new NiceHouse(this, 2304, 2464), new NiceHouse2(this, 640, 2144),
				new NiceHouse2(this, 640, 2256), new NiceHouse2(this, 640, 2368), new NiceHouse2(this, 640, 2480),
				new NiceHouse2(this, 640, 2576), new NiceHouse2(this, 640, 2688), new NiceHouse2(this, 848, 2736),
				new NiceHouse2(this, 952, 2496), new NiceHouse2(this, 1000, 2816), new NiceHouse2(this, 1168, 2496),
				new NiceHouse2(this, 1216, 2816), new NiceHouse2(this, 1448, 2816), new NiceHouse2(this, 1456, 2680),
				new NiceHouse2(this, 1472, 2456), new NiceHouse2(this, 1608, 2448), new NiceHouse2(this, 1624, 2816),
				new NiceHouse2(this, 1760, 2448), new NiceHouse2(this, 1768, 2816), new NiceHouse2(this, 1824, 2680),
				new NiceHouse2(this, 1856, 2568), new NiceHouse2(this, 1888, 2464), new NiceHouse2(this, 2008, 2816),
				new NiceHouse2(this, 2048, 2632), new NiceHouse2(this, 2080, 2536), new NiceHouse2(this, 2096, 2456),
				new NiceHouse2(this, 2192, 2816), new NiceHouse2(this, 2440, 2816), new NiceHouse2(this, 2528, 2816),
				new PoorHouse(this, 624, 1176), new PoorHouse(this, 720, 1208), new PoorHouse(this, 744, 1704),
				new PoorHouse(this, 952, 1280), new PoorHouse(this, 1144, 1336), new PoorHouse(this, 1552, 1368),
				new PoorHouse(this, 1632, 1368), new PoorHouse(this, 2256, 1792), new PoorHouse(this, 2336, 1792),
				new PoorHouse(this, 2416, 1792), new PoorHouse(this, 2496, 1792), new PoorHouse(this, 2576, 1736),
				new PoorHouse(this, 2624, 1640), new Projects(this, 424, 1360), new Projects(this, 528, 1480),
				new Projects(this, 640, 1600), new Projects(this, 1160, 1768), new Projects(this, 1416, 1344),
				new Projects(this, 1688, 1344), new Projects(this, 1696, 1768), new Projects(this, 2016, 1344),
				new RancheroHouse(this, 3344, 3128),

				// religious buildings
				new CatholicChapel(this, 3512, 3136), new CatholicChurch(this, 1848, 1344),
				new RussianOrthodoxChurch(this, 824, 1768),

				// hospitals
				new GenericHospital(this, 1544, 1768), new GenericHospital(this, 2624, 1336),

				// the fuzz
				new Police(this, 1352, 1752),

				// special buildings
				new QuackerHQ(this, 928, 2208), new SanJuanCityHall(this, 1344, 2184), new TheHub(this, 1144, 2136),
				new TheHub(this, 1800, 2136), new JungleHQ(this, 1544, 2208),

				// warehouses
				new Warehouse(this, 832, 1240), new Warehouse(this, 1016, 1296), new Warehouse(this, 1256, 1360),
				new Warehouse(this, 1416, 1048), new Warehouse(this, 1696, 1048), new Warehouse(this, 2024, 1048),
				new Warehouse(this, 2216, 1360), new Warehouse(this, 2256, 1048), new Warehouse(this, 2416, 1360),
				new Warehouse(this, 2560, 1048) };
	}

	/**
	 * Edges of the map
	 */
	@Override
	protected MapTransporter[] getMapTransporterPlacement() {

		return new MapTransporter[] {

				new MapTransporter(this, 0, 0, Level.TECH, Direction.WEST, 8, this.getHeight() * 8),

				new MapTransporter(this, (this.getWidth() * 8) - 8, 0, Level.ORCHARD, Direction.EAST, 8,
						this.getHeight() * 8) };

	}

}
