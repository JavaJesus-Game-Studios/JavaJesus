package level.story;

import game.entities.structures.Cafe;
import game.entities.structures.CardinalUniversity;
import game.entities.structures.GenericHospital;
import game.entities.structures.Hotel;
import game.entities.structures.NiceHouse;
import game.entities.structures.NiceHouse2;
import game.entities.structures.PearHQ;
import game.entities.structures.Police;
import game.entities.structures.PoorHouse;
import game.entities.structures.RadarDish;
import game.entities.structures.SequoiaSchool;
import game.entities.structures.TechTopiaCityHall;
import game.entities.structures.WeirdTechBuilding1;
import game.entities.structures.WeirdTechBuilding2;
import game.entities.structures.transporters.MapTransporter;
import level.Level;
import utility.Direction;

public class TechTopia extends Level {

	private static final long serialVersionUID = 3330749489914073847L;
	
	public static final Level level = new TechTopia();

	public TechTopia() {
		super("/Levels/Cities/Tech_Topia.png", true, "Tech Topia");
		setSpawnPoint(1512, 584);
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new Cafe(this, 2224, 1224));
		add(new CardinalUniversity(this, 1392, 448));
		add(new GenericHospital(this, 1576, 1792));
		add(new GenericHospital(this, 1872, 1056));
		add(new Hotel(this, 1720, 1160));
		add(new Hotel(this, 1808, 1808));
		add(new NiceHouse(this, 1408, 1480));
		add(new NiceHouse(this, 1496, 1320));
		add(new NiceHouse(this, 1560, 1208));
		add(new NiceHouse(this, 1608, 1120));
		add(new NiceHouse(this, 1656, 1040));
		add(new NiceHouse(this, 2000, 1704));
		add(new NiceHouse(this, 2168, 2024));
		add(new NiceHouse(this, 2240, 1104));
		add(new NiceHouse(this, 2392, 1080));
		add(new NiceHouse(this, 2512, 1280));
		add(new NiceHouse(this, 2568, 1376));
		add(new NiceHouse2(this, 1824, 960));
		add(new NiceHouse2(this, 1912, 960));
		add(new NiceHouse2(this, 1936, 1176));
		add(new NiceHouse2(this, 1984, 1256));
		add(new NiceHouse2(this, 2000, 960));
		add(new NiceHouse2(this, 2088, 960));
		add(new NiceHouse2(this, 2096, 1864));
		add(new NiceHouse2(this, 2176, 960));
		add(new NiceHouse2(this, 2352, 1000));
		add(new NiceHouse2(this, 2384, 1880));
		add(new NiceHouse2(this, 2424, 1808));
		add(new NiceHouse2(this, 2464, 1736));
		add(new NiceHouse2(this, 2504, 1656));
		add(new NiceHouse2(this, 2544, 1584));
		add(new NiceHouse2(this, 2584, 1512));
		add(new PearHQ(this, 280, 1928));
		add(new Police(this, 1928, 1552));
		add(new PoorHouse(this, 1632, 1888));
		add(new PoorHouse(this, 1672, 1968));
		add(new PoorHouse(this, 1744, 2024));
		add(new PoorHouse(this, 1840, 2024));
		add(new PoorHouse(this, 1936, 2024));
		add(new PoorHouse(this, 2008, 1864));
		add(new PoorHouse(this, 2032, 2024));
		add(new PoorHouse(this, 3192, 3224));
		add(new PoorHouse(this, 3224, 2648));
		add(new PoorHouse(this, 3288, 3224));
		add(new PoorHouse(this, 3320, 2648));
		add(new PoorHouse(this, 3384, 3224));
		add(new PoorHouse(this, 3416, 2648));
		add(new PoorHouse(this, 3480, 3224));
		add(new PoorHouse(this, 3568, 2648));
		add(new PoorHouse(this, 3576, 3224));
		add(new PoorHouse(this, 3664, 2648));
		add(new PoorHouse(this, 3760, 2648));
		add(new PoorHouse(this, 3800, 3224));
		add(new PoorHouse(this, 3896, 3224));
		add(new PoorHouse(this, 3984, 3184));
		add(new PoorHouse(this, 4096, 3184));
		add(new PoorHouse(this, 4168, 3288));
		add(new RadarDish(this, 552, 384));
		add(new SequoiaSchool(this, 2304, 1480));
		add(new TechTopiaCityHall(this, 1832, 1416));
		add(new WeirdTechBuilding1(this, 640, 2240));
		add(new WeirdTechBuilding1(this, 1648, 1496));
		add(new WeirdTechBuilding1(this, 1736, 1448));
		add(new WeirdTechBuilding1(this, 2128, 1440));
		add(new WeirdTechBuilding1(this, 2152, 1208));
		add(new WeirdTechBuilding1(this, 2192, 1536));
		add(new WeirdTechBuilding1(this, 2248, 1656));
		add(new WeirdTechBuilding2(this, 296, 2184));
		add(new WeirdTechBuilding2(this, 296, 2368));
		add(new WeirdTechBuilding2(this, 1576, 1456));
		add(new WeirdTechBuilding2(this, 1648, 1296));
		add(new WeirdTechBuilding2(this, 1736, 1632));
		add(new WeirdTechBuilding2(this, 1848, 1576));
		add(new WeirdTechBuilding2(this, 2056, 1296));
		add(new WeirdTechBuilding2(this, 2240, 1352));
		add(new WeirdTechBuilding2(this, 2344, 1296));

	}

	@Override
	protected void initMapTransporters() {

		add(new MapTransporter(this, 0, 0,
				BautistasDomain.level, Direction.NORTH, this.getWidth() * 8, 8));
		add(new MapTransporter(this, 0, 0,
				EdgeOfTheWoods.level, Direction.WEST, 8,
				this.getHeight() * 8));

		add(new MapTransporter(this, (this.getWidth() * 8) - 8, 0,
				SanJuan.level, Direction.EAST, 8,
				this.getHeight() * 8));

	}

}