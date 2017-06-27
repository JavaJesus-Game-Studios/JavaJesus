package javajesus.level.story;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.ApartmentHighRise;
import javajesus.entities.structures.CatholicChurch;
import javajesus.entities.structures.Factory;
import javajesus.entities.structures.GenericHospital;
import javajesus.entities.structures.Hotel;
import javajesus.entities.structures.ModernSkyscraper;
import javajesus.entities.structures.NiceHouse;
import javajesus.entities.structures.NiceHouse2;
import javajesus.entities.structures.Police;
import javajesus.entities.structures.PoorHouse;
import javajesus.entities.structures.Projects;
import javajesus.entities.structures.RefugeeTent;
import javajesus.entities.structures.RussianOrthodoxChurch;
import javajesus.entities.structures.Skyscraper;
import javajesus.entities.structures.Warehouse;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.sancisco.ChinatownHouse;
import javajesus.entities.structures.sancisco.RussianClub;
import javajesus.entities.structures.sancisco.SanCiscoCityHall;
import javajesus.entities.structures.sancisco.SanCiscoSkyscraper;
import javajesus.entities.structures.sancisco.TriadHQ;
import javajesus.entities.structures.transporters.MapTransporter;
import javajesus.entities.vehicles.Boat;
import javajesus.entities.vehicles.CenturyLeSabre;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A Parody of San Francisco
 */
public class SanCisco extends Level {

	private static final long serialVersionUID = 220527283938650811L;

	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates San Cisco
	 */
	public SanCisco() {
		super("/Levels/Cities/San_Cisco.png", true, Level.CISCO);
		setSpawnPoint(3400, 2688);

		System.err.println("Creating San Cisco");
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

				new CenturyLeSabre(this, 3425, 2688), new Boat(this, "Boat", 488 * 8, 127 * 8, 2, 100),

				// Residential (Nob Hill)
				new NiceHouse2(this, 1712, 2100), new NiceHouse2(this, 1792, 2100), new NiceHouse2(this, 1872, 2100),
				new NiceHouse2(this, 1952, 2100), new NiceHouse2(this, 984, 2244), new NiceHouse2(this, 1064, 2244),
				new NiceHouse2(this, 1144, 2244), new NiceHouse2(this, 1224, 2244), new NiceHouse2(this, 1304, 2244),
				new NiceHouse2(this, 1384, 2244), new NiceHouse2(this, 1464, 2244), new NiceHouse2(this, 1544, 2244),
				new NiceHouse2(this, 1624, 2244), new NiceHouse2(this, 1704, 2244), new NiceHouse2(this, 1784, 2244),
				new NiceHouse2(this, 1864, 2244), new NiceHouse2(this, 1944, 2244), new NiceHouse2(this, 2024, 2244),
				new NiceHouse2(this, 1008, 2428), new NiceHouse2(this, 1088, 2428), new NiceHouse2(this, 1168, 2428),
				new NiceHouse2(this, 1248, 2428), new NiceHouse2(this, 1328, 2428), new NiceHouse2(this, 1408, 2428),
				new NiceHouse2(this, 1488, 2428), new NiceHouse2(this, 1568, 2428), new NiceHouse2(this, 1648, 2428),
				new NiceHouse2(this, 1728, 2428), new NiceHouse2(this, 1808, 2428), new NiceHouse2(this, 1888, 2428),
				new NiceHouse2(this, 1968, 2428), new NiceHouse2(this, 2048, 2428), new NiceHouse2(this, 1104, 2668),
				new NiceHouse2(this, 1208, 2652), new NiceHouse2(this, 1312, 2636), new NiceHouse2(this, 1416, 2620),
				new NiceHouse2(this, 1520, 2596), new NiceHouse2(this, 1624, 2580),

				// The T-Bone (Tenderloin, Mission)
				new Hotel(this, 2344, 2400), new PoorHouse(this, 2488, 2416), new PoorHouse(this, 2584, 2400),
				new PoorHouse(this, 2656, 2384), new PoorHouse(this, 2744, 2368), new PoorHouse(this, 2824, 2352),
				new PoorHouse(this, 2912, 2336), new PoorHouse(this, 2992, 2320), new PoorHouse(this, 3160, 2304),
				new PoorHouse(this, 3168, 2288), new PoorHouse(this, 3256, 2272), new PoorHouse(this, 3336, 2256),
				new PoorHouse(this, 2256, 2128), new PoorHouse(this, 2336, 2128), new PoorHouse(this, 2400, 2128),
				new PoorHouse(this, 2632, 2128), new PoorHouse(this, 2712, 2128), new PoorHouse(this, 2776, 2128),
				new PoorHouse(this, 2936, 2128), new PoorHouse(this, 3008, 2128), new PoorHouse(this, 3080, 2128),
				new PoorHouse(this, 3312, 2128), new PoorHouse(this, 3384, 2128), new PoorHouse(this, 3456, 2128),
				new PoorHouse(this, 2680, 1992), new PoorHouse(this, 2752, 1992), new PoorHouse(this, 2824, 1992),
				new PoorHouse(this, 2896, 1992), new PoorHouse(this, 2968, 1992), new PoorHouse(this, 3040, 1992),
				new PoorHouse(this, 3112, 1992), new PoorHouse(this, 3328, 1992), new PoorHouse(this, 3400, 1992),
				new PoorHouse(this, 3480, 1992), new PoorHouse(this, 3552, 1992), new CatholicChurch(this, 2328, 1976),
				new Projects(this, 3192, 1968), new NiceHouse(this, 2472, 1992), new NiceHouse(this, 2088, 1992),
				new NiceHouse(this, 2504, 2128), new NiceHouse(this, 2856, 2128), new NiceHouse(this, 3184, 2128),
				new NiceHouse(this, 2496, 2320), new NiceHouse2(this, 2312, 2272), new NiceHouse2(this, 2392, 2272),
				new RefugeeTent(this, 3256, 1808), new RefugeeTent(this, 3368, 1816), new RefugeeTent(this, 3488, 1800),
				new RefugeeTent(this, 3600, 1776), new RefugeeTent(this, 3696, 1840), new RefugeeTent(this, 3552, 1856),
				new RefugeeTent(this, 3440, 1896), new RefugeeTent(this, 3280, 1880), new RefugeeTent(this, 3784, 2168),
				new RefugeeTent(this, 3848, 2112),

				// Downtown (Business District)
				new Police(this, 1360, 1952), new SanCiscoCityHall(this, 1528, 1864),
				new GenericHospital(this, 1800, 1864), new SanCiscoSkyscraper(this, 2144, 1176),
				new ApartmentHighRise(this, 2408, 1600), new ApartmentHighRise(this, 2544, 1576),
				new ApartmentHighRise(this, 2512, 1120), new ApartmentHighRise(this, 2616, 1120),
				new ApartmentHighRise(this, 2968, 992), new ApartmentHighRise(this, 3112, 992),
				new ApartmentHighRise(this, 3224, 992), new ApartmentHighRise(this, 2200, 808),
				new ApartmentHighRise(this, 2312, 784), new ApartmentHighRise(this, 2432, 752),
				new ModernSkyscraper(this, 1896, 840), new ModernSkyscraper(this, 2672, 1520),
				new Skyscraper(this, 2880, 1464), new Skyscraper(this, 2400, 1088), new Skyscraper(this, 2072, 808),
				new Skyscraper(this, 3360, 1144), new Skyscraper(this, 3512, 1352), new Factory(this, 3160, 1304),
				new Factory(this, 3288, 1424),

				// Chinatown (Chinatown)
				new TriadHQ(this, 1832, 368), new ChinatownHouse(this, 2064, 472), new ChinatownHouse(this, 2144, 464),
				new ChinatownHouse(this, 2224, 456), new ChinatownHouse(this, 2304, 448),
				new ChinatownHouse(this, 2384, 440), new ChinatownHouse(this, 2464, 432),
				new ChinatownHouse(this, 2544, 424), new ChinatownHouse(this, 2624, 416),
				new ChinatownHouse(this, 2704, 408), new ChinatownHouse(this, 2784, 400),
				new ChinatownHouse(this, 2864, 392), new ChinatownHouse(this, 2696, 600),
				new ChinatownHouse(this, 2792, 600), new ChinatownHouse(this, 2880, 600),
				new ChinatownHouse(this, 2876, 600), new ChinatownHouse(this, 1800, 664),
				new ChinatownHouse(this, 1888, 664), new ChinatownHouse(this, 1976, 664),
				new ChinatownHouse(this, 2072, 664), new ChinatownHouse(this, 2168, 664),
				new ChinatownHouse(this, 2256, 664), new ChinatownHouse(this, 2344, 664),
				new ChinatownHouse(this, 2440, 664), new ChinatownHouse(this, 2816, 832),
				new ChinatownHouse(this, 2904, 808), new ChinatownHouse(this, 2992, 784),
				new ChinatownHouse(this, 3086, 760), new GenericHospital(this, 2688, 832),

				// Little Russia (Presidio)
				new ApartmentHighRise(this, 712, 1336), new ApartmentHighRise(this, 1024, 1336),
				new Skyscraper(this, 968, 752), new Skyscraper(this, 864, 1320),
				new RussianOrthodoxChurch(this, 568, 1480), new RussianClub(this, 480, 1152), new Hotel(this, 832, 920),
				new Warehouse(this, 688, 584), new Warehouse(this, 840, 568), new Warehouse(this, 992, 560),
				new Warehouse(this, 1144, 544), new Warehouse(this, 1296, 528), new Warehouse(this, 1448, 512),
				new Warehouse(this, 632, 1144), new Warehouse(this, 544, 936), new Warehouse(this, 688, 936),
				new PoorHouse(this, 208, 648), new PoorHouse(this, 288, 640), new PoorHouse(this, 360, 632),
				new PoorHouse(this, 432, 624), new PoorHouse(this, 520, 616), new PoorHouse(this, 608, 608),
				new PoorHouse(this, 320, 944), new PoorHouse(this, 384, 944), new PoorHouse(this, 456, 944),
				new PoorHouse(this, 784, 1152), new PoorHouse(this, 864, 1144), new PoorHouse(this, 928, 1144),
				new PoorHouse(this, 1000, 1144), new PoorHouse(this, 1056, 1136), new NiceHouse2(this, 1224, 760),
				new NiceHouse2(this, 1304, 760), new NiceHouse2(this, 1384, 760), new NiceHouse2(this, 1464, 760),
				new NiceHouse2(this, 1544, 760), new NiceHouse(this, 1264, 944), new NiceHouse(this, 1360, 944),
				new NiceHouse(this, 1456, 944), new NiceHouse(this, 1552, 944), new NiceHouse(this, 1552, 1136),
				new NiceHouse(this, 1648, 1136), new Police(this, 1280, 1104), new CatholicChurch(this, 1432, 1112),
				new NiceHouse(this, 1288, 1504), new NiceHouse(this, 1384, 1488), new NiceHouse(this, 1480, 1472),
				new NiceHouse(this, 1576, 1456), new NiceHouse(this, 1672, 1440), new RefugeeTent(this, 672, 1680),
				new RefugeeTent(this, 736, 1760), new RefugeeTent(this, 800, 1904), new RefugeeTent(this, 872, 2056),
				new RefugeeTent(this, 848, 1656), new Projects(this, 1336, 1672), new Projects(this, 1544, 1672),
				new Projects(this, 1752, 1672) };

	}

	/**
	 * Edges of the map
	 */
	@Override
	protected MapTransporter[] getMapTransporterPlacement() {

		return new MapTransporter[] { new MapTransporter(this, 0, (this.getHeight() * 8) - 8, Level.HILLSBOROUGH,
				Direction.SOUTH, this.getWidth() * 8, 8),

				new MapTransporter(this, 0, 0, Level.EDGE_TOP, Direction.WEST, 8, this.getHeight() * 8) };

	}

}
