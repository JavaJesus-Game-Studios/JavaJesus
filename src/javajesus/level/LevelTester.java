package javajesus.level;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.FireEntity;
import javajesus.entities.Pickup;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.buildings.ApartmentHighRise;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.solid.buildings.Castle;
import javajesus.entities.solid.buildings.CastleTower;
import javajesus.entities.solid.buildings.CatholicChapel;
import javajesus.entities.solid.buildings.CatholicChurch;
import javajesus.entities.solid.buildings.CaveEntrance;
import javajesus.entities.solid.buildings.Factory;
import javajesus.entities.solid.buildings.GenericHospital;
import javajesus.entities.solid.buildings.GunStore;
import javajesus.entities.solid.buildings.Hotel;
import javajesus.entities.solid.buildings.Hut;
import javajesus.entities.solid.buildings.MineShaft;
import javajesus.entities.solid.buildings.ModernSkyscraper;
import javajesus.entities.solid.buildings.NiceHouse;
import javajesus.entities.solid.buildings.NiceHouse2;
import javajesus.entities.solid.buildings.Police;
import javajesus.entities.solid.buildings.PoorHouse;
import javajesus.entities.solid.buildings.Prison;
import javajesus.entities.solid.buildings.Projects;
import javajesus.entities.solid.buildings.RancheroHouse;
import javajesus.entities.solid.buildings.RefugeeTent;
import javajesus.entities.solid.buildings.RussianOrthodoxChurch;
import javajesus.entities.solid.buildings.ShantyHouse;
import javajesus.entities.solid.buildings.Skyscraper;
import javajesus.entities.solid.buildings.Tippee;
import javajesus.entities.solid.buildings.Warehouse;
import javajesus.entities.solid.buildings.hippyville.GreatTree;
import javajesus.entities.solid.buildings.hippyville.TreeHouse;
import javajesus.entities.solid.buildings.hippyville.UCGrizzly;
import javajesus.entities.solid.buildings.oakwood.OakwoodCityHall;
import javajesus.entities.solid.buildings.sancsico.ChinatownHouse;
import javajesus.entities.solid.buildings.sancsico.RussianClub;
import javajesus.entities.solid.buildings.sancsico.SanCiscoCityHall;
import javajesus.entities.solid.buildings.sancsico.SanCiscoSkyscraper;
import javajesus.entities.solid.buildings.sancsico.TriadHQ;
import javajesus.entities.solid.buildings.sanjuan.JugleHQ;
import javajesus.entities.solid.buildings.sanjuan.QuackerHQ;
import javajesus.entities.solid.buildings.sanjuan.SanJuanCityHall;
import javajesus.entities.solid.buildings.sanjuan.TheHub;
import javajesus.entities.solid.buildings.sequoia.SequoiaCinema;
import javajesus.entities.solid.buildings.sequoia.SequoiaSchool;
import javajesus.entities.solid.buildings.techtopia.Cafe;
import javajesus.entities.solid.buildings.techtopia.CardinalUniversity;
import javajesus.entities.solid.buildings.techtopia.PearHQ;
import javajesus.entities.solid.buildings.techtopia.RadarDish;
import javajesus.entities.solid.buildings.techtopia.TechTopiaCityHall;
import javajesus.entities.solid.buildings.techtopia.WeirdTechBuilding1;
import javajesus.entities.solid.buildings.techtopia.WeirdTechBuilding2;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.items.Item;

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
		super("/WORLD_DATA/TESTER_LEVELS/tile_tester.png", true,
				NAME, new Point(150, 100));
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
		return new Chest[] { new Chest(this, 218, 105, Item.laserRevolver, Item.shotgun, Item.assaultRifle,
		        Item.crossBow, Item.bazooka, Item.blackHoleGun, Item.flameThrower, Item.longSword, Item.claymore) };
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
				
		// pickups
		new Pickup(this, 230, 105, Item.apple),
		new Pickup(this, 240, 105, Item.orange),
		new Pickup(this, 250, 105, Item.banana),
		new Pickup(this, 260, 105, Item.feather),
		new Pickup(this, 270, 105, Item.revolver),
		new Pickup(this, 280, 105, Item.strongHealthPack),
		new Pickup(this, 290, 105, Item.quickHealthPack, true),

		// fire
		new FireEntity(this, 320, 100),
		
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
		b = new JugleHQ(this, x +=(int) b.getBounds().getWidth() + 50, 0),
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
