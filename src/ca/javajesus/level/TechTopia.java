package ca.javajesus.level;

import java.awt.Point;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.structures.Cafe;
import ca.javajesus.game.entities.structures.CardinalUniversity;
import ca.javajesus.game.entities.structures.GenericHospital;
import ca.javajesus.game.entities.structures.Hotel;
import ca.javajesus.game.entities.structures.NiceHouse;
import ca.javajesus.game.entities.structures.NiceHouse2;
import ca.javajesus.game.entities.structures.PearHQ;
import ca.javajesus.game.entities.structures.Police;
import ca.javajesus.game.entities.structures.PoorHouse;
import ca.javajesus.game.entities.structures.RadarDish;
import ca.javajesus.game.entities.structures.SequoiaSchool;
import ca.javajesus.game.entities.structures.TechTopiaCityHall;
import ca.javajesus.game.entities.structures.WeirdTechBuilding1;
import ca.javajesus.game.entities.structures.WeirdTechBuilding2;
import ca.javajesus.game.entities.structures.transporters.MapTransporter;

public class TechTopia extends Level {

	private static final long serialVersionUID = 3330749489914073847L;

	public TechTopia() {
		super("/Levels/Cities/Tech_Topia.png", true, "Tech Topia");
		this.spawnPoint = new Point(1512, 584);
		startingSpawnPoint = new Point(1512, 584);
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new Cafe(this, 2224, 1224));
		this.addEntity(new CardinalUniversity(this, 1392, 448));
		this.addEntity(new GenericHospital(this, 1576, 1792));
		this.addEntity(new GenericHospital(this, 1872, 1056));
		this.addEntity(new Hotel(this, 1720, 1160));
		this.addEntity(new Hotel(this, 1808, 1808));
		this.addEntity(new NiceHouse(this, 1408, 1480));
		this.addEntity(new NiceHouse(this, 1496, 1320));
		this.addEntity(new NiceHouse(this, 1560, 1208));
		this.addEntity(new NiceHouse(this, 1608, 1120));
		this.addEntity(new NiceHouse(this, 1656, 1040));
		this.addEntity(new NiceHouse(this, 2000, 1704));
		this.addEntity(new NiceHouse(this, 2168, 2024));
		this.addEntity(new NiceHouse(this, 2240, 1104));
		this.addEntity(new NiceHouse(this, 2392, 1080));
		this.addEntity(new NiceHouse(this, 2512, 1280));
		this.addEntity(new NiceHouse(this, 2568, 1376));
		this.addEntity(new NiceHouse2(this, 1824, 960));
		this.addEntity(new NiceHouse2(this, 1912, 960));
		this.addEntity(new NiceHouse2(this, 1936, 1176));
		this.addEntity(new NiceHouse2(this, 1984, 1256));
		this.addEntity(new NiceHouse2(this, 2000, 960));
		this.addEntity(new NiceHouse2(this, 2088, 960));
		this.addEntity(new NiceHouse2(this, 2096, 1864));
		this.addEntity(new NiceHouse2(this, 2176, 960));
		this.addEntity(new NiceHouse2(this, 2352, 1000));
		this.addEntity(new NiceHouse2(this, 2384, 1880));
		this.addEntity(new NiceHouse2(this, 2424, 1808));
		this.addEntity(new NiceHouse2(this, 2464, 1736));
		this.addEntity(new NiceHouse2(this, 2504, 1656));
		this.addEntity(new NiceHouse2(this, 2544, 1584));
		this.addEntity(new NiceHouse2(this, 2584, 1512));
		this.addEntity(new PearHQ(this, 280, 1928));
		this.addEntity(new Police(this, 1928, 1552));
		this.addEntity(new PoorHouse(this, 1632, 1888));
		this.addEntity(new PoorHouse(this, 1672, 1968));
		this.addEntity(new PoorHouse(this, 1744, 2024));
		this.addEntity(new PoorHouse(this, 1840, 2024));
		this.addEntity(new PoorHouse(this, 1936, 2024));
		this.addEntity(new PoorHouse(this, 2008, 1864));
		this.addEntity(new PoorHouse(this, 2032, 2024));
		this.addEntity(new PoorHouse(this, 3192, 3224));
		this.addEntity(new PoorHouse(this, 3224, 2648));
		this.addEntity(new PoorHouse(this, 3288, 3224));
		this.addEntity(new PoorHouse(this, 3320, 2648));
		this.addEntity(new PoorHouse(this, 3384, 3224));
		this.addEntity(new PoorHouse(this, 3416, 2648));
		this.addEntity(new PoorHouse(this, 3480, 3224));
		this.addEntity(new PoorHouse(this, 3568, 2648));
		this.addEntity(new PoorHouse(this, 3576, 3224));
		this.addEntity(new PoorHouse(this, 3664, 2648));
		this.addEntity(new PoorHouse(this, 3760, 2648));
		this.addEntity(new PoorHouse(this, 3800, 3224));
		this.addEntity(new PoorHouse(this, 3896, 3224));
		this.addEntity(new PoorHouse(this, 3984, 3184));
		this.addEntity(new PoorHouse(this, 4096, 3184));
		this.addEntity(new PoorHouse(this, 4168, 3288));
		this.addEntity(new RadarDish(this, 552, 384));
		this.addEntity(new SequoiaSchool(this, 2304, 1480));
		this.addEntity(new TechTopiaCityHall(this, 1832, 1416));
		this.addEntity(new WeirdTechBuilding1(this, 640, 2240));
		this.addEntity(new WeirdTechBuilding1(this, 1648, 1496));
		this.addEntity(new WeirdTechBuilding1(this, 1736, 1448));
		this.addEntity(new WeirdTechBuilding1(this, 2128, 1440));
		this.addEntity(new WeirdTechBuilding1(this, 2152, 1208));
		this.addEntity(new WeirdTechBuilding1(this, 2192, 1536));
		this.addEntity(new WeirdTechBuilding1(this, 2248, 1656));
		this.addEntity(new WeirdTechBuilding2(this, 296, 2184));
		this.addEntity(new WeirdTechBuilding2(this, 296, 2368));
		this.addEntity(new WeirdTechBuilding2(this, 1576, 1456));
		this.addEntity(new WeirdTechBuilding2(this, 1648, 1296));
		this.addEntity(new WeirdTechBuilding2(this, 1736, 1632));
		this.addEntity(new WeirdTechBuilding2(this, 1848, 1576));
		this.addEntity(new WeirdTechBuilding2(this, 2056, 1296));
		this.addEntity(new WeirdTechBuilding2(this, 2240, 1352));
		this.addEntity(new WeirdTechBuilding2(this, 2344, 1296));

	}

	@Override
	protected void initMapTransporters() {

		this.addEntity(new MapTransporter(this, 0, 0,
				Game.levels.bautistasDomain, new Point(Game.player.getX(),
						(Game.levels.bautistasDomain.height * 8) - 16),
				this.width * 8, 8));
		this.addEntity(new MapTransporter(this, 0, 0,
				Game.levels.edgeOfTheWoodsMain, new Point(
						(Game.levels.edgeOfTheWoodsMain.width * 8) - 16,
						Game.player.getY()
								+ Game.levels.edgeOfTheWoodsMain.height * 16
								/ 3), 8, this.height * 8));

		this.addEntity(new MapTransporter(this, (this.width * 8) - 8, 0,
				Game.levels.sanJuan, new Point(16, Game.player.getY()), 8,
				this.height * 8));

	}

}