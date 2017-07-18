package javajesus.level.story;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.buildings.CatholicChapel;
import javajesus.entities.solid.buildings.CatholicChurch;
import javajesus.entities.solid.buildings.CaveEntrance;
import javajesus.entities.solid.buildings.GunStore;
import javajesus.entities.solid.buildings.NiceHouse;
import javajesus.entities.solid.buildings.NiceHouse2;
import javajesus.entities.solid.buildings.PoorHouse;
import javajesus.entities.solid.buildings.Prison;
import javajesus.entities.solid.buildings.Projects;
import javajesus.entities.solid.buildings.RancheroHouse;
import javajesus.entities.solid.buildings.RefugeeTent;
import javajesus.entities.solid.buildings.ShantyHouse;
import javajesus.entities.solid.buildings.Warehouse;
import javajesus.entities.solid.buildings.sequoia.SequoiaCinema;
import javajesus.entities.solid.buildings.sequoia.SequoiaSchool;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class BautistasDomain extends Level {

	private static final long serialVersionUID = 7176790184478980943L;

	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates Bautistas Domain
	 */
	public BautistasDomain() {
		super("/WORLD_DATA/STORY_DATA/(X)CITY_LEVELS/Domain_of_Ranchero_Bautista.png", true, Level.BAUTISTA, new Point(2896, 64));

		System.err.println("Creating Bautistas Domain");
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
		// Sequoia City
		return new Entity[] {

				// churches and chapels
				new CatholicChapel(this, 2312, 1280), new CatholicChurch(this, 920, 2048),
				new CatholicChurch(this, 1960, 136),

				// cave entrances
				new CaveEntrance(this, 176, 184), new CaveEntrance(this, 1808, 432),

				// cinema
				new SequoiaCinema(this, 2800, 1832),

				// /gunstore
				new GunStore(this, 2952, 1896),

				// houses
				new NiceHouse(this, 1880, 1808), new NiceHouse(this, 1880, 1944), new NiceHouse(this, 1912, 1488),
				new NiceHouse(this, 2080, 1632), new NiceHouse(this, 2080, 1808), new NiceHouse(this, 2080, 1944),
				new NiceHouse(this, 2904, 2048), new NiceHouse(this, 3016, 2072), new NiceHouse(this, 3128, 2088),
				new NiceHouse2(this, 1800, 1488), new NiceHouse2(this, 1848, 1632), new NiceHouse2(this, 1968, 1632),
				new NiceHouse2(this, 1992, 1808), new NiceHouse2(this, 1992, 1944), new NiceHouse2(this, 2048, 1488),
				new NiceHouse2(this, 2288, 1504), new NiceHouse2(this, 2400, 1504), new NiceHouse2(this, 2512, 1504),
				new NiceHouse2(this, 2624, 1504), new NiceHouse2(this, 2736, 1504), new NiceHouse2(this, 2848, 1504),
				new NiceHouse2(this, 2960, 1504), new PoorHouse(this, 2000, 616), new PoorHouse(this, 2088, 616),
				new PoorHouse(this, 2088, 920), new PoorHouse(this, 2176, 616), new PoorHouse(this, 2192, 920),
				new PoorHouse(this, 2288, 616), new PoorHouse(this, 2296, 920), new PoorHouse(this, 2400, 920),
				new PoorHouse(this, 2504, 920), new PoorHouse(this, 2504, 1264), new PoorHouse(this, 2520, 616),
				new PoorHouse(this, 2608, 920), new PoorHouse(this, 2624, 616), new PoorHouse(this, 2632, 1256),
				new PoorHouse(this, 2712, 920), new PoorHouse(this, 2736, 1248), new PoorHouse(this, 2816, 920),
				new PoorHouse(this, 2848, 1240), new PoorHouse(this, 2944, 1232), new RancheroHouse(this, 520, 2112),
				new ShantyHouse(this, 1688, 280), new ShantyHouse(this, 1792, 248), new ShantyHouse(this, 1888, 216),
				new ShantyHouse(this, 2080, 160), new ShantyHouse(this, 2144, 128), new ShantyHouse(this, 2232, 104),
				new ShantyHouse(this, 2280, 1200), new ShantyHouse(this, 2304, 1096), new ShantyHouse(this, 2312, 112),
				new ShantyHouse(this, 2360, 1144), new ShantyHouse(this, 2408, 112), new ShantyHouse(this, 2448, 1216),
				new ShantyHouse(this, 2464, 1120), new ShantyHouse(this, 2472, 112), new ShantyHouse(this, 2536, 1144),
				new ShantyHouse(this, 2568, 120), new ShantyHouse(this, 2632, 1120), new ShantyHouse(this, 2648, 120),
				new ShantyHouse(this, 2648, 1192), new ShantyHouse(this, 2736, 128),

				// african-american lodging
				new Prison(this, 1648, 592), new Projects(this, 1696, 896), new Projects(this, 1792, 1264),
				new Projects(this, 1832, 592), new Projects(this, 1840, 1088), new Projects(this, 1920, 896),
				new Projects(this, 2008, 1272), new Projects(this, 2376, 352), new Projects(this, 2704, 592),
				new RefugeeTent(this, 1912, 512), new RefugeeTent(this, 2048, 400), new RefugeeTent(this, 2128, 448),
				new RefugeeTent(this, 2264, 488), new RefugeeTent(this, 2288, 328), new RefugeeTent(this, 2424, 424),
				new RefugeeTent(this, 2528, 352), new RefugeeTent(this, 2616, 480), new RefugeeTent(this, 2744, 352),
				new RefugeeTent(this, 2768, 456), new RefugeeTent(this, 3320, 1680), new RefugeeTent(this, 3336, 1384),
				new RefugeeTent(this, 3432, 1504),

				// caucasian lodging
				new SequoiaSchool(this, 2264, 1688),

				// warehouses
				new Warehouse(this, 2968, 1408), new Warehouse(this, 3088, 648), new Warehouse(this, 3128, 800),
				new Warehouse(this, 3168, 1088), new Warehouse(this, 3200, 1232), new Warehouse(this, 3280, 752),
				new Warehouse(this, 3440, 712)

		};

	}

	/**
	 * Edges of the map
	 */
	protected MapTransporter[] getMapTransporterPlacement() {

		return new MapTransporter[] {
				new MapTransporter(this, 0, 0, Level.HILLSBOROUGH, Direction.NORTH, (this.getWidth() * 8), 8),
				new MapTransporter(this, 0, (this.getHeight() * 8) - 8, Level.TECH, Direction.SOUTH,
						(this.getWidth() * 8), 8),
				new MapTransporter(this, 0, 0, Level.EDGE_MAIN, Direction.WEST, 8, this.getHeight() * 8)

		};

	}
}
