package level;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.ApartmentHighRise;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.Castle;
import javajesus.entities.structures.CastleTower;
import javajesus.entities.structures.CatholicChapel;
import javajesus.entities.structures.CatholicChurch;
import javajesus.entities.structures.CaveEntrance;
import javajesus.entities.structures.Factory;
import javajesus.entities.structures.GenericHospital;
import javajesus.entities.structures.GunStore;
import javajesus.entities.structures.Hotel;
import javajesus.entities.structures.Hut;
import javajesus.entities.structures.MineShaft;
import javajesus.entities.structures.ModernSkyscraper;
import javajesus.entities.structures.NiceHouse;
import javajesus.entities.structures.NiceHouse2;
import javajesus.entities.structures.Police;
import javajesus.entities.structures.PoorHouse;
import javajesus.entities.structures.Prison;
import javajesus.entities.structures.Projects;
import javajesus.entities.structures.RancheroHouse;
import javajesus.entities.structures.RefugeeTent;
import javajesus.entities.structures.RussianOrthodoxChurch;
import javajesus.entities.structures.ShantyHouse;
import javajesus.entities.structures.Skyscraper;
import javajesus.entities.structures.Tippee;
import javajesus.entities.structures.Warehouse;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.hippyville.GreatTree;
import javajesus.entities.structures.hippyville.TreeHouse;
import javajesus.entities.structures.hippyville.UCGrizzly;
import javajesus.entities.structures.oakwood.OakwoodCityHall;
import javajesus.entities.structures.sancisco.ChinatownHouse;
import javajesus.entities.structures.sancisco.RussianClub;
import javajesus.entities.structures.sancisco.SanCiscoCityHall;
import javajesus.entities.structures.sancisco.SanCiscoSkyscraper;
import javajesus.entities.structures.sancisco.TriadHQ;
import javajesus.entities.structures.sanjuan.JungleHQ;
import javajesus.entities.structures.sanjuan.QuackerHQ;
import javajesus.entities.structures.sanjuan.SanJuanCityHall;
import javajesus.entities.structures.sanjuan.TheHub;
import javajesus.entities.structures.sequoia.SequoiaCinema;
import javajesus.entities.structures.sequoia.SequoiaSchool;
import javajesus.entities.structures.techtopia.Cafe;
import javajesus.entities.structures.techtopia.CardinalUniversity;
import javajesus.entities.structures.techtopia.PearHQ;
import javajesus.entities.structures.techtopia.RadarDish;
import javajesus.entities.structures.techtopia.TechTopiaCityHall;
import javajesus.entities.structures.techtopia.WeirdTechBuilding1;
import javajesus.entities.structures.techtopia.WeirdTechBuilding2;
import javajesus.entities.structures.transporters.MapTransporter;

/* Tester level for testing all of our entities */
public class LevelTester extends Level {

	// for serialization
	private static final long serialVersionUID = 5943407753519754342L;
	
	// Name of the level
	private static final String NAME = "Tester Level 1";
	
	// the only instance of the level
	public static final Level level = new LevelTester();

	/**
	 * Creates  Tester Level
	 */
	public LevelTester() {
		super("/Levels/Test_Levels/tile_tester_level.png", true,
				NAME);
		setSpawnPoint(150, 100);
	}

	/** 
	 * NPC Placement
	 * @return 
	 */
	public NPC[] getNPCPlacement() {
		return null;
	}

	/**
	 * Monster Spawners
	 */
	public Spawner[] getSpawnerPlacement() {
		return null;
	}

	/**
	 * Chest placement
	 */
	public Chest[] getChestPlacement() {
		return null;
	}

	/**
	 * All structures
	 */
	public Entity[] getOtherPlacement() {
		
		// last building
		Building b;
		
		// x location
		int x = 50;
		
		Entity[] entities = {
		
		/* First Row of buildings */
		b = new ApartmentHighRise(this, x, 0),
		b = new Cafe(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new CardinalUniversity(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new Castle(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new CastleTower(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new CatholicChurch(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new CatholicChapel(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new CaveEntrance(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new ChinatownHouse(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new Factory(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new GenericHospital(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new GreatTree(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new GunStore(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new Hotel(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new Hut(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new JungleHQ(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new MineShaft(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new ModernSkyscraper(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new NiceHouse(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		b = new NiceHouse2(this, x +=(int) b.getBounds().getWidth() + 50, 0),
		
		/* Second row of  buildings */
		b = new OakwoodCityHall(this, x = 50, 200),
		b = new PearHQ(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new Police(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new PoorHouse(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new Prison(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new Projects(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new QuackerHQ(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new RadarDish(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new RancheroHouse(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new RefugeeTent(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new RussianClub(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new RussianOrthodoxChurch(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new SanCiscoCityHall(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new SanCiscoSkyscraper(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new SanJuanCityHall(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new SequoiaCinema(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new SequoiaSchool(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new ShantyHouse(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		b = new Skyscraper(this, x +=(int) b.getBounds().getWidth() + 50, 200),
		
		/* Third row of  buildings */
		b = new TechTopiaCityHall(this, x = 50, 400),
		b = new TheHub(this, x +=(int) b.getBounds().getWidth() + 50, 400),
		b = new Tippee(this, x +=(int) b.getBounds().getWidth() + 50, 400),
		b = new TreeHouse(this, x +=(int) b.getBounds().getWidth() + 50, 400),
		b = new TriadHQ(this, x +=(int) b.getBounds().getWidth() + 50, 400),
		b = new UCGrizzly(this, x +=(int) b.getBounds().getWidth() + 50, 400),
		b = new Warehouse(this, x +=(int) b.getBounds().getWidth() + 50, 400),
		b = new WeirdTechBuilding1(this, x +=(int) b.getBounds().getWidth() + 50, 400),
		b = new WeirdTechBuilding2(this, x +=(int) b.getBounds().getWidth() + 50, 400),
		
		};
		
		return entities;

	}

	@Override
	protected MapTransporter[] getMapTransporterPlacement() {
		return null;

	}

}
