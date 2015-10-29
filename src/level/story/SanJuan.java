package level.story;

import game.Game;
import game.entities.Mob.Direction;
import game.entities.structures.ApartmentHighRise;
import game.entities.structures.CatholicChapel;
import game.entities.structures.CatholicChurch;
import game.entities.structures.GenericHospital;
import game.entities.structures.Hotel;
import game.entities.structures.JungleHQ;
import game.entities.structures.NiceHouse;
import game.entities.structures.NiceHouse2;
import game.entities.structures.Police;
import game.entities.structures.PoorHouse;
import game.entities.structures.Projects;
import game.entities.structures.QuackerHQ;
import game.entities.structures.RancheroHouse;
import game.entities.structures.RussianOrthodoxChurch;
import game.entities.structures.SanJuanCityHall;
import game.entities.structures.TheHub;
import game.entities.structures.Warehouse;
import game.entities.structures.transporters.MapTransporter;

import java.awt.Point;

import level.Level;

public class SanJuan extends Level {

	private static final long serialVersionUID = -1623870972641933936L;

	public SanJuan() {
		super("/Levels/Cities/San_Juan.png", true, "San Juan");
		this.spawnPoint = new Point(1680, 2648);
		startingSpawnPoint = new Point(1680, 2648);

	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {

		// housing
		this.addEntity(new ApartmentHighRise(this, 1976, 1624));
		this.addEntity(new ApartmentHighRise(this, 2216, 2080));
		this.addEntity(new ApartmentHighRise(this, 2360, 2080));
		this.addEntity(new Hotel(this, 2096, 1768));
		this.addEntity(new NiceHouse(this, 1104, 2816));
		this.addEntity(new NiceHouse(this, 480, 2296));
		this.addEntity(new NiceHouse(this, 504, 2400));
		this.addEntity(new NiceHouse(this, 536, 2520));
		this.addEntity(new NiceHouse(this, 552, 2632));
		this.addEntity(new NiceHouse(this, 616, 2832));
		this.addEntity(new NiceHouse(this, 848, 2456));
		this.addEntity(new NiceHouse(this, 872, 2816));
		this.addEntity(new NiceHouse(this, 1056, 2496));
		this.addEntity(new NiceHouse(this, 1104, 2816));
		this.addEntity(new NiceHouse(this, 1248, 2712));
		this.addEntity(new NiceHouse(this, 1272, 2456));
		this.addEntity(new NiceHouse(this, 1464, 2560));
		this.addEntity(new NiceHouse(this, 1536, 2816));
		this.addEntity(new NiceHouse(this, 1584, 2552));
		this.addEntity(new NiceHouse(this, 1752, 2584));
		this.addEntity(new NiceHouse(this, 2024, 2728));
		this.addEntity(new NiceHouse(this, 2096, 2816));
		this.addEntity(new NiceHouse(this, 2224, 2696));
		this.addEntity(new NiceHouse(this, 2264, 2592));
		this.addEntity(new NiceHouse(this, 2304, 2464));
		this.addEntity(new NiceHouse2(this, 640, 2144));
		this.addEntity(new NiceHouse2(this, 640, 2256));
		this.addEntity(new NiceHouse2(this, 640, 2368));
		this.addEntity(new NiceHouse2(this, 640, 2480));
		this.addEntity(new NiceHouse2(this, 640, 2576));
		this.addEntity(new NiceHouse2(this, 640, 2688));
		this.addEntity(new NiceHouse2(this, 848, 2736));
		this.addEntity(new NiceHouse2(this, 952, 2496));
		this.addEntity(new NiceHouse2(this, 1000, 2816));
		this.addEntity(new NiceHouse2(this, 1168, 2496));
		this.addEntity(new NiceHouse2(this, 1216, 2816));
		this.addEntity(new NiceHouse2(this, 1448, 2816));
		this.addEntity(new NiceHouse2(this, 1456, 2680));
		this.addEntity(new NiceHouse2(this, 1472, 2456));
		this.addEntity(new NiceHouse2(this, 1608, 2448));
		this.addEntity(new NiceHouse2(this, 1624, 2816));
		this.addEntity(new NiceHouse2(this, 1760, 2448));
		this.addEntity(new NiceHouse2(this, 1768, 2816));
		this.addEntity(new NiceHouse2(this, 1824, 2680));
		this.addEntity(new NiceHouse2(this, 1856, 2568));
		this.addEntity(new NiceHouse2(this, 1888, 2464));
		this.addEntity(new NiceHouse2(this, 2008, 2816));
		this.addEntity(new NiceHouse2(this, 2048, 2632));
		this.addEntity(new NiceHouse2(this, 2080, 2536));
		this.addEntity(new NiceHouse2(this, 2096, 2456));
		this.addEntity(new NiceHouse2(this, 2192, 2816));
		this.addEntity(new NiceHouse2(this, 2440, 2816));
		this.addEntity(new NiceHouse2(this, 2528, 2816));
		this.addEntity(new PoorHouse(this, 624, 1176));
		this.addEntity(new PoorHouse(this, 720, 1208));
		this.addEntity(new PoorHouse(this, 744, 1704));
		this.addEntity(new PoorHouse(this, 952, 1280));
		this.addEntity(new PoorHouse(this, 1144, 1336));
		this.addEntity(new PoorHouse(this, 1552, 1368));
		this.addEntity(new PoorHouse(this, 1632, 1368));
		this.addEntity(new PoorHouse(this, 2256, 1792));
		this.addEntity(new PoorHouse(this, 2336, 1792));
		this.addEntity(new PoorHouse(this, 2416, 1792));
		this.addEntity(new PoorHouse(this, 2496, 1792));
		this.addEntity(new PoorHouse(this, 2576, 1736));
		this.addEntity(new PoorHouse(this, 2624, 1640));
		this.addEntity(new Projects(this, 424, 1360));
		this.addEntity(new Projects(this, 528, 1480));
		this.addEntity(new Projects(this, 640, 1600));
		this.addEntity(new Projects(this, 1160, 1768));
		this.addEntity(new Projects(this, 1416, 1344));
		this.addEntity(new Projects(this, 1688, 1344));
		this.addEntity(new Projects(this, 1696, 1768));
		this.addEntity(new Projects(this, 2016, 1344));
		this.addEntity(new RancheroHouse(this, 3344, 3128));

		// religious buildings
		this.addEntity(new CatholicChapel(this, 3512, 3136));
		this.addEntity(new CatholicChurch(this, 1848, 1344));
		this.addEntity(new RussianOrthodoxChurch(this, 824, 1768));

		// hospitals
		this.addEntity(new GenericHospital(this, 1544, 1768));
		this.addEntity(new GenericHospital(this, 2624, 1336));

		// the fuzz
		this.addEntity(new Police(this, 1352, 1752));

		// special buildings
		this.addEntity(new QuackerHQ(this, 928, 2208));
		this.addEntity(new SanJuanCityHall(this, 1344, 2184));
		this.addEntity(new TheHub(this, 1144, 2136));
		this.addEntity(new TheHub(this, 1800, 2136));
		this.addEntity(new JungleHQ(this, 1544, 2208));

		// warehouses
		this.addEntity(new Warehouse(this, 832, 1240));
		this.addEntity(new Warehouse(this, 1016, 1296));
		this.addEntity(new Warehouse(this, 1256, 1360));
		this.addEntity(new Warehouse(this, 1416, 1048));
		this.addEntity(new Warehouse(this, 1696, 1048));
		this.addEntity(new Warehouse(this, 2024, 1048));
		this.addEntity(new Warehouse(this, 2216, 1360));
		this.addEntity(new Warehouse(this, 2256, 1048));
		this.addEntity(new Warehouse(this, 2416, 1360));
		this.addEntity(new Warehouse(this, 2560, 1048));
	}

	@Override
	protected void initMapTransporters() {

		this.addEntity(new MapTransporter(this, 0, 0, Game.levels.techTopia,
				Direction.WEST, 8, this.height * 8));

		this.addEntity(new MapTransporter(this, (this.width * 8) - 8, 0,
				Game.levels.orchardValley, Direction.EAST,
				8, this.height * 8));

	}

}
