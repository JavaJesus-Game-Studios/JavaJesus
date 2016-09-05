package level.story;

import game.entities.structures.ApartmentHighRise;
import game.entities.structures.CatholicChurch;
import game.entities.structures.ChinatownHouse;
import game.entities.structures.Factory;
import game.entities.structures.GenericHospital;
import game.entities.structures.Hotel;
import game.entities.structures.ModernSkyscraper;
import game.entities.structures.NiceHouse;
import game.entities.structures.NiceHouse2;
import game.entities.structures.Police;
import game.entities.structures.PoorHouse;
import game.entities.structures.Projects;
import game.entities.structures.RefugeeTent;
import game.entities.structures.RussianClub;
import game.entities.structures.RussianOrthodoxChurch;
import game.entities.structures.SanCiscoCityHall;
import game.entities.structures.SanCiscoSkyscraper;
import game.entities.structures.Skyscraper;
import game.entities.structures.TriadHQ;
import game.entities.structures.Warehouse;
import game.entities.structures.transporters.MapTransporter;
import game.entities.vehicles.Boat;
import game.entities.vehicles.CenturyLeSabre;
import level.Level;
import utility.Direction;

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

	protected void initNPCPlacement() {
		
		add(new CenturyLeSabre(this, 3425, 2688));
		add(new Boat(this, "Boat", 488*8, 127*8, 2, 100));

		
	}

	protected void initSpawnerPlacement() {
		
	}

	protected void initChestPlacement() {
		
	}
	
	protected void otherEntityPlacement() {
		//Residential (Nob Hill)
			add(new NiceHouse2(this, 1712, 2100));
			add(new NiceHouse2(this, 1792, 2100));
			add(new NiceHouse2(this, 1872, 2100));
			add(new NiceHouse2(this, 1952, 2100));
			add(new NiceHouse2(this, 984, 2244));
			add(new NiceHouse2(this, 1064, 2244));
			add(new NiceHouse2(this, 1144, 2244));
			add(new NiceHouse2(this, 1224, 2244));
			add(new NiceHouse2(this, 1304, 2244));
			add(new NiceHouse2(this, 1384, 2244));
			add(new NiceHouse2(this, 1464, 2244));
			add(new NiceHouse2(this, 1544, 2244));
			add(new NiceHouse2(this, 1624, 2244));
			add(new NiceHouse2(this, 1704, 2244));
			add(new NiceHouse2(this, 1784, 2244));
			add(new NiceHouse2(this, 1864, 2244));
			add(new NiceHouse2(this, 1944, 2244));
			add(new NiceHouse2(this, 2024, 2244));
			add(new NiceHouse2(this, 1008, 2428));
			add(new NiceHouse2(this, 1088, 2428));
			add(new NiceHouse2(this, 1168, 2428));
			add(new NiceHouse2(this, 1248, 2428));
			add(new NiceHouse2(this, 1328, 2428));
			add(new NiceHouse2(this, 1408, 2428));
			add(new NiceHouse2(this, 1488, 2428));
			add(new NiceHouse2(this, 1568, 2428));
			add(new NiceHouse2(this, 1648, 2428));
			add(new NiceHouse2(this, 1728, 2428));
			add(new NiceHouse2(this, 1808, 2428));
			add(new NiceHouse2(this, 1888, 2428));
			add(new NiceHouse2(this, 1968, 2428));
			add(new NiceHouse2(this, 2048, 2428));
			add(new NiceHouse2(this, 1104, 2668));
			add(new NiceHouse2(this, 1208, 2652));
			add(new NiceHouse2(this, 1312, 2636));
			add(new NiceHouse2(this, 1416, 2620));
			add(new NiceHouse2(this, 1520, 2596));
			add(new NiceHouse2(this, 1624, 2580));

			
		//The T-Bone (Tenderloin, Mission)
			add(new Hotel(this, 2344, 2400));
			add(new PoorHouse(this, 2488, 2416));
			add(new PoorHouse(this, 2584, 2400));
			add(new PoorHouse(this, 2656, 2384));
			add(new PoorHouse(this, 2744, 2368));
			add(new PoorHouse(this, 2824, 2352));
			add(new PoorHouse(this, 2912, 2336));
			add(new PoorHouse(this, 2992, 2320));
			add(new PoorHouse(this, 3160, 2304));
			add(new PoorHouse(this, 3168, 2288));
			add(new PoorHouse(this, 3256, 2272));
			add(new PoorHouse(this, 3336, 2256));
			add(new PoorHouse(this, 2256, 2128));
			add(new PoorHouse(this, 2336, 2128));
			add(new PoorHouse(this, 2400, 2128));
			add(new PoorHouse(this, 2632, 2128));
			add(new PoorHouse(this, 2712, 2128));
			add(new PoorHouse(this, 2776, 2128));
			add(new PoorHouse(this, 2936, 2128));
			add(new PoorHouse(this, 3008, 2128));
			add(new PoorHouse(this, 3080, 2128));
			add(new PoorHouse(this, 3312, 2128));
			add(new PoorHouse(this, 3384, 2128));
			add(new PoorHouse(this, 3456, 2128));
			add(new PoorHouse(this, 2680, 1992));
			add(new PoorHouse(this, 2752, 1992));
			add(new PoorHouse(this, 2824, 1992));
			add(new PoorHouse(this, 2896, 1992));
			add(new PoorHouse(this, 2968, 1992));
			add(new PoorHouse(this, 3040, 1992));
			add(new PoorHouse(this, 3112, 1992));
			add(new PoorHouse(this, 3328, 1992));
			add(new PoorHouse(this, 3400, 1992));
			add(new PoorHouse(this, 3480, 1992));
			add(new PoorHouse(this, 3552, 1992));
			add(new CatholicChurch(this, 2328, 1976));
			add(new Projects(this, 3192, 1968));
			add(new NiceHouse(this, 2472, 1992));
			add(new NiceHouse(this, 2088, 1992));
			add(new NiceHouse(this, 2504, 2128));
			add(new NiceHouse(this, 2856, 2128));
			add(new NiceHouse(this, 3184, 2128));
			add(new NiceHouse(this, 2496, 2320));
			add(new NiceHouse2(this, 2312, 2272));
			add(new NiceHouse2(this, 2392, 2272));
			add(new RefugeeTent(this, 3256, 1808));
			add(new RefugeeTent(this, 3368, 1816));
			add(new RefugeeTent(this, 3488, 1800));
			add(new RefugeeTent(this, 3600, 1776));
			add(new RefugeeTent(this, 3696, 1840));
			add(new RefugeeTent(this, 3552, 1856));
			add(new RefugeeTent(this, 3440, 1896));
			add(new RefugeeTent(this, 3280, 1880));
			add(new RefugeeTent(this, 3784, 2168));
			add(new RefugeeTent(this, 3848, 2112));



		//Downtown (Business District)
			add(new Police(this, 1360, 1952));
			add(new SanCiscoCityHall(this, 1528, 1864));
			add(new GenericHospital(this, 1800, 1864));
			add(new SanCiscoSkyscraper(this, 2144, 1176));
			add(new ApartmentHighRise(this, 2408, 1600));
			add(new ApartmentHighRise(this, 2544, 1576));
			add(new ApartmentHighRise(this, 2512, 1120));
			add(new ApartmentHighRise(this, 2616, 1120));
			add(new ApartmentHighRise(this, 2968, 992));
			add(new ApartmentHighRise(this, 3112, 992));
			add(new ApartmentHighRise(this, 3224, 992));
			add(new ApartmentHighRise(this, 2200, 808));
			add(new ApartmentHighRise(this, 2312, 784));
			add(new ApartmentHighRise(this, 2432, 752));
			add(new ModernSkyscraper(this, 1896, 840));
			add(new ModernSkyscraper(this, 2672, 1520));
			add(new Skyscraper(this, 2880, 1464));
			add(new Skyscraper(this, 2400, 1088));
			add(new Skyscraper(this, 2072, 808));
			add(new Skyscraper(this, 3360, 1144));
			add(new Skyscraper(this, 3512, 1352));
			add(new Factory(this, 3160, 1304));
			add(new Factory(this, 3288, 1424));

			


		//Chinatown (Chinatown)
			add(new TriadHQ(this, 1832, 368));
			add(new ChinatownHouse(this, 2064, 472));
			add(new ChinatownHouse(this, 2144, 464));
			add(new ChinatownHouse(this, 2224, 456));
			add(new ChinatownHouse(this, 2304, 448));
			add(new ChinatownHouse(this, 2384, 440));
			add(new ChinatownHouse(this, 2464, 432));
			add(new ChinatownHouse(this, 2544, 424));
			add(new ChinatownHouse(this, 2624, 416));
			add(new ChinatownHouse(this, 2704, 408));
			add(new ChinatownHouse(this, 2784, 400));
			add(new ChinatownHouse(this, 2864, 392));
			add(new ChinatownHouse(this, 2696, 600));
			add(new ChinatownHouse(this, 2792, 600));
			add(new ChinatownHouse(this, 2880, 600));
			add(new ChinatownHouse(this, 2876, 600));
			add(new ChinatownHouse(this, 1800, 664));
			add(new ChinatownHouse(this, 1888, 664));
			add(new ChinatownHouse(this, 1976, 664));
			add(new ChinatownHouse(this, 2072, 664));
			add(new ChinatownHouse(this, 2168, 664));
			add(new ChinatownHouse(this, 2256, 664));
			add(new ChinatownHouse(this, 2344, 664));
			add(new ChinatownHouse(this, 2440, 664));
			add(new ChinatownHouse(this, 2816, 832));
			add(new ChinatownHouse(this, 2904, 808));
			add(new ChinatownHouse(this, 2992, 784));
			add(new ChinatownHouse(this, 3086, 760));
			add(new GenericHospital(this, 2688, 832));


		//Little Russia (Presidio)
			add(new ApartmentHighRise(this, 712, 1336));
			add(new ApartmentHighRise(this, 1024, 1336));
			add(new Skyscraper(this, 968, 752));
			add(new Skyscraper(this, 864, 1320));
			add(new RussianOrthodoxChurch(this, 568, 1480));
			add(new RussianClub(this, 480, 1152));
			add(new Hotel(this, 832, 920));
			add(new Warehouse(this, 688,584));
			add(new Warehouse(this, 840,568));
			add(new Warehouse(this, 992,560));
			add(new Warehouse(this, 1144,544));
			add(new Warehouse(this, 1296,528));
			add(new Warehouse(this, 1448,512));
			add(new Warehouse(this, 632,1144));
			add(new Warehouse(this, 544,936));
			add(new Warehouse(this, 688,936));
			add(new PoorHouse(this, 208, 648));
			add(new PoorHouse(this, 288, 640));
			add(new PoorHouse(this, 360, 632));
			add(new PoorHouse(this, 432, 624));
			add(new PoorHouse(this, 520, 616));
			add(new PoorHouse(this, 608, 608));
			add(new PoorHouse(this, 320, 944));
			add(new PoorHouse(this, 384, 944));
			add(new PoorHouse(this, 456, 944));
			add(new PoorHouse(this, 784, 1152));
			add(new PoorHouse(this, 864, 1144));
			add(new PoorHouse(this, 928, 1144));
			add(new PoorHouse(this, 1000, 1144));
			add(new PoorHouse(this, 1056, 1136));
			add(new NiceHouse2(this, 1224, 760));
			add(new NiceHouse2(this, 1304, 760));
			add(new NiceHouse2(this, 1384, 760));
			add(new NiceHouse2(this, 1464, 760));
			add(new NiceHouse2(this, 1544, 760));
			add(new NiceHouse(this, 1264, 944));
			add(new NiceHouse(this, 1360, 944));
			add(new NiceHouse(this, 1456, 944));
			add(new NiceHouse(this, 1552, 944));
			add(new NiceHouse(this, 1552, 1136));
			add(new NiceHouse(this, 1648, 1136));
			add(new Police(this, 1280, 1104));
			add(new CatholicChurch(this, 1432, 1112));
			add(new NiceHouse(this, 1288, 1504));
			add(new NiceHouse(this, 1384, 1488));
			add(new NiceHouse(this, 1480, 1472));
			add(new NiceHouse(this, 1576, 1456));
			add(new NiceHouse(this, 1672, 1440));
			add(new RefugeeTent(this, 672, 1680));
			add(new RefugeeTent(this, 736, 1760));
			add(new RefugeeTent(this, 800, 1904));
			add(new RefugeeTent(this, 872, 2056));
			add(new RefugeeTent(this, 848, 1656));
			add(new Projects(this, 1336, 1672));
			add(new Projects(this, 1544, 1672));
			add(new Projects(this, 1752, 1672));

	}

	/**
	 * Edges of the map
	 */
	@Override
	protected void initMapTransporters() {
		add(new MapTransporter(this, 0, (this.getHeight() * 8) - 8, Level.HILLSBOROUGH,
				Direction.SOUTH,
				this.getWidth() * 8, 8));
		
		add(new MapTransporter(this, 0, 0,
				Level.EDGE_TOP, Direction.WEST, 8, this.getHeight() * 8));
		
	}
	
	

}
