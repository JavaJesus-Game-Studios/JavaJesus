package level;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.ApartmentHighRise;
import game.entities.structures.Building;
import game.entities.structures.Castle;
import game.entities.structures.CastleTower;
import game.entities.structures.CatholicChapel;
import game.entities.structures.CatholicChurch;
import game.entities.structures.CaveEntrance;
import game.entities.structures.Factory;
import game.entities.structures.GenericHospital;
import game.entities.structures.GunStore;
import game.entities.structures.Hotel;
import game.entities.structures.Hut;
import game.entities.structures.MineShaft;
import game.entities.structures.ModernSkyscraper;
import game.entities.structures.NiceHouse;
import game.entities.structures.NiceHouse2;
import game.entities.structures.Police;
import game.entities.structures.PoorHouse;
import game.entities.structures.Prison;
import game.entities.structures.Projects;
import game.entities.structures.RancheroHouse;
import game.entities.structures.RefugeeTent;
import game.entities.structures.RussianOrthodoxChurch;
import game.entities.structures.ShantyHouse;
import game.entities.structures.Skyscraper;
import game.entities.structures.Tippee;
import game.entities.structures.Warehouse;
import game.entities.structures.furniture.Chest;
import game.entities.structures.hippyville.GreatTree;
import game.entities.structures.hippyville.TreeHouse;
import game.entities.structures.hippyville.UCGrizzly;
import game.entities.structures.oakwood.OakwoodCityHall;
import game.entities.structures.sancisco.ChinatownHouse;
import game.entities.structures.sancisco.RussianClub;
import game.entities.structures.sancisco.SanCiscoCityHall;
import game.entities.structures.sancisco.SanCiscoSkyscraper;
import game.entities.structures.sancisco.TriadHQ;
import game.entities.structures.sanjuan.JungleHQ;
import game.entities.structures.sanjuan.QuackerHQ;
import game.entities.structures.sanjuan.SanJuanCityHall;
import game.entities.structures.sanjuan.TheHub;
import game.entities.structures.sequoia.SequoiaCinema;
import game.entities.structures.sequoia.SequoiaSchool;
import game.entities.structures.techtopia.Cafe;
import game.entities.structures.techtopia.CardinalUniversity;
import game.entities.structures.techtopia.PearHQ;
import game.entities.structures.techtopia.RadarDish;
import game.entities.structures.techtopia.TechTopiaCityHall;
import game.entities.structures.techtopia.WeirdTechBuilding1;
import game.entities.structures.techtopia.WeirdTechBuilding2;
import game.entities.structures.transporters.MapTransporter;

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
