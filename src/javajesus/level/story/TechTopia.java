package javajesus.level.story;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.GenericHospital;
import javajesus.entities.structures.Hotel;
import javajesus.entities.structures.NiceHouse;
import javajesus.entities.structures.NiceHouse2;
import javajesus.entities.structures.Police;
import javajesus.entities.structures.PoorHouse;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.sequoia.SequoiaSchool;
import javajesus.entities.structures.techtopia.Cafe;
import javajesus.entities.structures.techtopia.CardinalUniversity;
import javajesus.entities.structures.techtopia.PearHQ;
import javajesus.entities.structures.techtopia.RadarDish;
import javajesus.entities.structures.techtopia.TechTopiaCityHall;
import javajesus.entities.structures.techtopia.WeirdTechBuilding1;
import javajesus.entities.structures.techtopia.WeirdTechBuilding2;
import javajesus.entities.structures.transporters.MapTransporter;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class TechTopia extends Level {

	private static final long serialVersionUID = 3330749489914073847L;

	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates Tech Topia
	 */
	public TechTopia() {
		super("/Levels/Cities/Tech_Topia.png", true, Level.TECH, new Point(1512, 584));

		System.err.println("Creating Tech Topia");
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

		return new Entity[] { new Cafe(this, 2224, 1224), new CardinalUniversity(this, 1392, 448),
				new GenericHospital(this, 1576, 1792), new GenericHospital(this, 1872, 1056),
				new Hotel(this, 1720, 1160), new Hotel(this, 1808, 1808), new NiceHouse(this, 1408, 1480),
				new NiceHouse(this, 1496, 1320), new NiceHouse(this, 1560, 1208), new NiceHouse(this, 1608, 1120),
				new NiceHouse(this, 1656, 1040), new NiceHouse(this, 2000, 1704), new NiceHouse(this, 2168, 2024),
				new NiceHouse(this, 2240, 1104), new NiceHouse(this, 2392, 1080), new NiceHouse(this, 2512, 1280),
				new NiceHouse(this, 2568, 1376), new NiceHouse2(this, 1824, 960), new NiceHouse2(this, 1912, 960),
				new NiceHouse2(this, 1936, 1176), new NiceHouse2(this, 1984, 1256), new NiceHouse2(this, 2000, 960),
				new NiceHouse2(this, 2088, 960), new NiceHouse2(this, 2096, 1864), new NiceHouse2(this, 2176, 960),
				new NiceHouse2(this, 2352, 1000), new NiceHouse2(this, 2384, 1880), new NiceHouse2(this, 2424, 1808),
				new NiceHouse2(this, 2464, 1736), new NiceHouse2(this, 2504, 1656), new NiceHouse2(this, 2544, 1584),
				new NiceHouse2(this, 2584, 1512), new PearHQ(this, 280, 1928), new Police(this, 1928, 1552),
				new PoorHouse(this, 1632, 1888), new PoorHouse(this, 1672, 1968), new PoorHouse(this, 1744, 2024),
				new PoorHouse(this, 1840, 2024), new PoorHouse(this, 1936, 2024), new PoorHouse(this, 2008, 1864),
				new PoorHouse(this, 2032, 2024), new PoorHouse(this, 3192, 3224), new PoorHouse(this, 3224, 2648),
				new PoorHouse(this, 3288, 3224), new PoorHouse(this, 3320, 2648), new PoorHouse(this, 3384, 3224),
				new PoorHouse(this, 3416, 2648), new PoorHouse(this, 3480, 3224), new PoorHouse(this, 3568, 2648),
				new PoorHouse(this, 3576, 3224), new PoorHouse(this, 3664, 2648), new PoorHouse(this, 3760, 2648),
				new PoorHouse(this, 3800, 3224), new PoorHouse(this, 3896, 3224), new PoorHouse(this, 3984, 3184),
				new PoorHouse(this, 4096, 3184), new PoorHouse(this, 4168, 3288), new RadarDish(this, 552, 384),
				new SequoiaSchool(this, 2304, 1480), new TechTopiaCityHall(this, 1832, 1416),
				new WeirdTechBuilding1(this, 640, 2240), new WeirdTechBuilding1(this, 1648, 1496),
				new WeirdTechBuilding1(this, 1736, 1448), new WeirdTechBuilding1(this, 2128, 1440),
				new WeirdTechBuilding1(this, 2152, 1208), new WeirdTechBuilding1(this, 2192, 1536),
				new WeirdTechBuilding1(this, 2248, 1656), new WeirdTechBuilding2(this, 296, 2184),
				new WeirdTechBuilding2(this, 296, 2368), new WeirdTechBuilding2(this, 1576, 1456),
				new WeirdTechBuilding2(this, 1648, 1296), new WeirdTechBuilding2(this, 1736, 1632),
				new WeirdTechBuilding2(this, 1848, 1576), new WeirdTechBuilding2(this, 2056, 1296),
				new WeirdTechBuilding2(this, 2240, 1352), new WeirdTechBuilding2(this, 2344, 1296) };

	}

	/**
	 * Edges of the map
	 */
	@Override
	protected MapTransporter[] getMapTransporterPlacement() {

		return new MapTransporter[] {

				new MapTransporter(this, 0, 0, Level.BAUTISTA, Direction.NORTH, this.getWidth() * 8, 8),
				new MapTransporter(this, 0, 0, Level.EDGE_MAIN, Direction.WEST, 8, this.getHeight() * 8),

				new MapTransporter(this, (this.getWidth() * 8) - 8, 0, Level.JUAN, Direction.EAST, 8,
						this.getHeight() * 8) };

	}

}