package level.story;

import game.entities.structures.CatholicChapel;
import game.entities.structures.CatholicChurch;
import game.entities.structures.CaveEntrance;
import game.entities.structures.GunStore;
import game.entities.structures.NiceHouse;
import game.entities.structures.NiceHouse2;
import game.entities.structures.PoorHouse;
import game.entities.structures.Prison;
import game.entities.structures.Projects;
import game.entities.structures.RancheroHouse;
import game.entities.structures.RefugeeTent;
import game.entities.structures.SequoiaCinema;
import game.entities.structures.SequoiaSchool;
import game.entities.structures.ShantyHouse;
import game.entities.structures.Warehouse;
import game.entities.structures.transporters.MapTransporter;
import level.Level;
import utility.Direction;

public class BautistasDomain extends Level {

	private static final long serialVersionUID = 7176790184478980943L;
	
	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates Bautistas Domain
	 */
	public BautistasDomain() {
		super("/Levels/Cities/Domain_of_Ranchero_Bautista.png", true,
				"Bautista's Domain");
		setSpawnPoint(2896, 64);

		System.err.println("Creating Bautistas Domain");
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		// Sequoia City

		// churches and chapels
		add(new CatholicChapel(this, 2312, 1280));
		add(new CatholicChurch(this, 920, 2048));
		add(new CatholicChurch(this, 1960, 136));

		// cave entrances
		add(new CaveEntrance(this, 176, 184));
		add(new CaveEntrance(this, 1808, 432));

		// cinema
		add(new SequoiaCinema(this, 2800, 1832));

		// /gunstore
		add(new GunStore(this, 2952, 1896));

		// houses
		add(new NiceHouse(this, 1880, 1808));
		add(new NiceHouse(this, 1880, 1944));
		add(new NiceHouse(this, 1912, 1488));
		add(new NiceHouse(this, 2080, 1632));
		add(new NiceHouse(this, 2080, 1808));
		add(new NiceHouse(this, 2080, 1944));
		add(new NiceHouse(this, 2904, 2048));
		add(new NiceHouse(this, 3016, 2072));
		add(new NiceHouse(this, 3128, 2088));
		add(new NiceHouse2(this, 1800, 1488));
		add(new NiceHouse2(this, 1848, 1632));
		add(new NiceHouse2(this, 1968, 1632));
		add(new NiceHouse2(this, 1992, 1808));
		add(new NiceHouse2(this, 1992, 1944));
		add(new NiceHouse2(this, 2048, 1488));
		add(new NiceHouse2(this, 2288, 1504));
		add(new NiceHouse2(this, 2400, 1504));
		add(new NiceHouse2(this, 2512, 1504));
		add(new NiceHouse2(this, 2624, 1504));
		add(new NiceHouse2(this, 2736, 1504));
		add(new NiceHouse2(this, 2848, 1504));
		add(new NiceHouse2(this, 2960, 1504));
		add(new PoorHouse(this, 2000, 616));
		add(new PoorHouse(this, 2088, 616));
		add(new PoorHouse(this, 2088, 920));
		add(new PoorHouse(this, 2176, 616));
		add(new PoorHouse(this, 2192, 920));
		add(new PoorHouse(this, 2288, 616));
		add(new PoorHouse(this, 2296, 920));
		add(new PoorHouse(this, 2400, 920));
		add(new PoorHouse(this, 2504, 920));
		add(new PoorHouse(this, 2504, 1264));
		add(new PoorHouse(this, 2520, 616));
		add(new PoorHouse(this, 2608, 920));
		add(new PoorHouse(this, 2624, 616));
		add(new PoorHouse(this, 2632, 1256));
		add(new PoorHouse(this, 2712, 920));
		add(new PoorHouse(this, 2736, 1248));
		add(new PoorHouse(this, 2816, 920));
		add(new PoorHouse(this, 2848, 1240));
		add(new PoorHouse(this, 2944, 1232));
		add(new RancheroHouse(this, 520, 2112));
		add(new ShantyHouse(this, 1688, 280));
		add(new ShantyHouse(this, 1792, 248));
		add(new ShantyHouse(this, 1888, 216));
		add(new ShantyHouse(this, 2080, 160));
		add(new ShantyHouse(this, 2144, 128));
		add(new ShantyHouse(this, 2232, 104));
		add(new ShantyHouse(this, 2280, 1200));
		add(new ShantyHouse(this, 2304, 1096));
		add(new ShantyHouse(this, 2312, 112));
		add(new ShantyHouse(this, 2360, 1144));
		add(new ShantyHouse(this, 2408, 112));
		add(new ShantyHouse(this, 2448, 1216));
		add(new ShantyHouse(this, 2464, 1120));
		add(new ShantyHouse(this, 2472, 112));
		add(new ShantyHouse(this, 2536, 1144));
		add(new ShantyHouse(this, 2568, 120));
		add(new ShantyHouse(this, 2632, 1120));
		add(new ShantyHouse(this, 2648, 120));
		add(new ShantyHouse(this, 2648, 1192));
		add(new ShantyHouse(this, 2736, 128));

		// african-american lodging
		add(new Prison(this, 1648, 592));
		add(new Projects(this, 1696, 896));
		add(new Projects(this, 1792, 1264));
		add(new Projects(this, 1832, 592));
		add(new Projects(this, 1840, 1088));
		add(new Projects(this, 1920, 896));
		add(new Projects(this, 2008, 1272));
		add(new Projects(this, 2376, 352));
		add(new Projects(this, 2704, 592));
		add(new RefugeeTent(this, 1912, 512));
		add(new RefugeeTent(this, 2048, 400));
		add(new RefugeeTent(this, 2128, 448));
		add(new RefugeeTent(this, 2264, 488));
		add(new RefugeeTent(this, 2288, 328));
		add(new RefugeeTent(this, 2424, 424));
		add(new RefugeeTent(this, 2528, 352));
		add(new RefugeeTent(this, 2616, 480));
		add(new RefugeeTent(this, 2744, 352));
		add(new RefugeeTent(this, 2768, 456));
		add(new RefugeeTent(this, 3320, 1680));
		add(new RefugeeTent(this, 3336, 1384));
		add(new RefugeeTent(this, 3432, 1504));

		// caucasian lodging
		add(new SequoiaSchool(this, 2264, 1688));

		// warehouses
		add(new Warehouse(this, 2968, 1408));
		add(new Warehouse(this, 3088, 648));
		add(new Warehouse(this, 3128, 800));
		add(new Warehouse(this, 3168, 1088));
		add(new Warehouse(this, 3200, 1232));
		add(new Warehouse(this, 3280, 752));
		add(new Warehouse(this, 3440, 712));

	}

	/**
	 * Edges of the map
	 */
	protected void initMapTransporters() {
		add(new MapTransporter(this, 0, 0,
				LordHillsboroughsDomain.level, Direction.NORTH,
				(this.getWidth() * 8), 8));
		add(new MapTransporter(this, 0, (this.getHeight() * 8) - 8,
				TechTopia.level, Direction.SOUTH, (this.getWidth() * 8), 8));
		add(new MapTransporter(this, 0, 0,
				EdgeOfTheWoods.level, Direction.WEST, 8,
				this.getHeight() * 8));

	}
}
