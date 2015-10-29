package level;

import game.entities.FireEntity;
import game.entities.monsters.Centaur;
import game.entities.monsters.Cyclops;
import game.entities.monsters.GangMember;
import game.entities.monsters.Monkey;
import game.entities.npcs.Bautista;
import game.entities.npcs.Daughter;
import game.entities.npcs.Istrahiim;
import game.entities.npcs.Jesus;
import game.entities.npcs.Jobs;
import game.entities.npcs.Kobe;
import game.entities.npcs.LordHillsborough;
import game.entities.npcs.NPC;
import game.entities.npcs.Octavius;
import game.entities.npcs.Peasant;
import game.entities.npcs.Ranchero;
import game.entities.npcs.Son;
import game.entities.npcs.Wife;
import game.entities.npcs.Zorra;
import game.entities.npcs.NPC.Gender;
import game.entities.npcs.aggressive.Gorilla;
import game.entities.npcs.aggressive.Knight;
import game.entities.npcs.aggressive.NativeAmerican;
import game.entities.npcs.aggressive.PoliceOfficer;
import game.entities.npcs.aggressive.SWATOfficer;
import game.entities.npcs.aggressive.TechWarrior;
import game.entities.particles.pickups.ArrowPickup;
import game.entities.particles.pickups.AssaultRifleAmmo;
import game.entities.particles.pickups.HornedPickup;
import game.entities.particles.pickups.IstrahiimPickup;
import game.entities.particles.pickups.KnightPickup;
import game.entities.particles.pickups.LaserAmmoPickup;
import game.entities.particles.pickups.QuickHealthPickup;
import game.entities.particles.pickups.RevolverAmmoPickup;
import game.entities.particles.pickups.ShotgunAmmoPickup;
import game.entities.particles.pickups.StrongHealthPack;
import game.entities.particles.pickups.VestPickup;
import game.entities.structures.ApartmentHighRise;
import game.entities.structures.Cafe;
import game.entities.structures.CardinalUniversity;
import game.entities.structures.Castle;
import game.entities.structures.CastleTower;
import game.entities.structures.CatholicChapel;
import game.entities.structures.CatholicChurch;
import game.entities.structures.CaveEntrance;
import game.entities.structures.ChinatownHouse;
import game.entities.structures.Factory;
import game.entities.structures.GenericHospital;
import game.entities.structures.GreatTree;
import game.entities.structures.GunStore;
import game.entities.structures.Hotel;
import game.entities.structures.Hut;
import game.entities.structures.JungleHQ;
import game.entities.structures.MineShaft;
import game.entities.structures.ModernSkyscraper;
import game.entities.structures.NiceHouse;
import game.entities.structures.NiceHouse2;
import game.entities.structures.OakwoodCityHall;
import game.entities.structures.PearHQ;
import game.entities.structures.Police;
import game.entities.structures.PoorHouse;
import game.entities.structures.Prison;
import game.entities.structures.Projects;
import game.entities.structures.QuackerHQ;
import game.entities.structures.RadarDish;
import game.entities.structures.RancheroHouse;
import game.entities.structures.RefugeeTent;
import game.entities.structures.RussianClub;
import game.entities.structures.RussianOrthodoxChurch;
import game.entities.structures.SanCiscoCityHall;
import game.entities.structures.SanCiscoSkyscraper;
import game.entities.structures.SanJuanCityHall;
import game.entities.structures.SequoiaCinema;
import game.entities.structures.SequoiaSchool;
import game.entities.structures.ShantyHouse;
import game.entities.structures.Skyscraper;
import game.entities.structures.TechTopiaCityHall;
import game.entities.structures.TheHub;
import game.entities.structures.Tippee;
import game.entities.structures.TreeHouse;
import game.entities.structures.TriadHQ;
import game.entities.structures.UCGrizzly;
import game.entities.structures.Warehouse;
import game.entities.structures.WeirdTechBuilding1;
import game.entities.structures.WeirdTechBuilding2;
import game.entities.structures.furniture.Chest;
import game.entities.structures.trees.Forest;
import game.entities.vehicles.Boat;
import game.entities.vehicles.CenturyLeSabre;
import game.entities.vehicles.Horse;
import game.entities.vehicles.SportsCar;
import game.entities.vehicles.Truck;
import items.Item;

import java.awt.Point;
import java.util.ArrayList;

public class Level1 extends Level {

	private static final long serialVersionUID = 5943407753519754342L;

	public Level1() {
		super("/Levels/Test_Levels/tile_tester_level.png", true,
				"Tester Level 1");
		this.spawnPoint = new Point(50, 50);
		startingSpawnPoint = new Point(50, 50);
	}

	public void initNPCPlacement() {
		this.addEntity(new UCGrizzly(this, 50, 50));

		this.addEntity(new LordHillsborough(this, 50, 50));
		this.addEntity(new Wife(this, 50, 70));
		this.addEntity(new Daughter(this, 50, 90));
		this.addEntity(new Son(this, 50, 110));
		this.addEntity(new Octavius(this, 50, 130));
		this.addEntity(new Kobe(this, 50, 150));
		this.addEntity(new Bautista(this, 50, 170));
		this.addEntity(new Jobs(this, 50, 190));
		this.addEntity(new Zorra(this, 50, 210));
		this.addEntity(new Ranchero(this, 50, 230));
		this.addEntity(new NativeAmerican(this, 20, 70, Gender.MALE));
		this.addEntity(new NativeAmerican(this, 20, 90, Gender.FEMALE));
		this.addEntity(new Gorilla(this, 200, 200));

		this.addEntity(new Istrahiim(this, 250, 180));

		this.addEntity(new Knight(this, 200, 100, "linear", 20));
		this.addEntity(new PoliceOfficer(this, 160, 250));
		this.addEntity(new NPC(this, "Citizen-Female", 200, 400,
				1, 16, 16, 100,
				new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0, 8,
				"cross", 30));
		this.addEntity(new NPC(this, "Citizen-Male", 200, 500, 1,
				16, 16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB },
				0, 0, "circle", 2));
		this.addEntity(new NPC(this, "Fox", 250, 75, 1, 16, 16,
				100, new int[] { 0xFF111111, 0xFFFFA800, 0xFFFFFFFF }, 0, 14,
				"cross", 50));
		this.addEntity(new TechWarrior(this, 400, 250));
		this.addEntity(new SWATOfficer(this, 370, 120, 200, "linear", 20));
		this.addEntity(new Peasant(this, 2005, 950, Gender.MALE));
		this.addEntity(new Peasant(this, 2025, 950, Gender.FEMALE));
		this.addEntity(new Peasant(this, 2035, 950, Gender.BOY));
		this.addEntity(new Peasant(this, 2045, 950, Gender.GIRL));
		this.addEntity(new Jesus(this, 300, 400, "stand", 30));

		this.addEntity(new Cyclops(this, 600, 300));
		this.addEntity(new GangMember(this, "Gang Member 1", 430, 120, 1, 100,
				0));
		this.addEntity(new Centaur(this, "HorseThing", 500, 200, 1, 100));
		this.addEntity(new Monkey(this, "Monkey", 70, 70, 1, 100));
		this.addEntity(new GangMember(this, "Criminal", 100, 150, 1, 100, 1));

	}

	@Override
	public void initSpawnerPlacement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initChestPlacement() {
		ArrayList<Item> chest1 = new ArrayList<Item>();
		chest1.add(Item.shotgun);
		this.addEntity(new Chest(this, 100, 200, chest1));

	}

	@Override
	public void otherEntityPlacement() {

		this.addEntity(new Forest(this, 0, 500, 500, 500));

		this.addEntity(new CenturyLeSabre(this, 350, 300));
		this.addEntity(new Truck(this, 400, 300));
		this.addEntity(new SportsCar(this, 450, 300));
		this.addEntity(new Boat(this, "Boat", 357, 532, 2, 100));

		this.addEntity(new Horse(this, 666, 930, 0));

		this.addEntity(new PoorHouse(this, 100, 50));
		this.addEntity(new NiceHouse(this, 600, 50));
		this.addEntity(new Hut(this, 300, 50));
		this.addEntity(new CatholicChurch(this, 400, 25));
		this.addEntity(new CastleTower(this, 500, 50));
		this.addEntity(new CaveEntrance(this, 200, 50));
		this.addEntity(new Skyscraper(this, 200, 200));
		this.addEntity(new SanCiscoSkyscraper(this, 10, 2084));

		this.addEntity(new VestPickup(this, 110, 25));
		this.addEntity(new KnightPickup(this, 120, 25));
		this.addEntity(new HornedPickup(this, 130, 25));
		this.addEntity(new IstrahiimPickup(this, 140, 25));

		this.addEntity(new QuickHealthPickup(this, 150, 25));
		this.addEntity(new StrongHealthPack(this, 160, 25));
		this.addEntity(new AssaultRifleAmmo(this, 170, 25));
		this.addEntity(new RevolverAmmoPickup(this, 180, 25));
		this.addEntity(new ShotgunAmmoPickup(this, 190, 25));
		this.addEntity(new LaserAmmoPickup(this, 200, 25));
		this.addEntity(new ArrowPickup(this, 210, 25));

		this.addEntity(new GenericHospital(this, 700, 50));
		this.addEntity(new Tippee(this, 838, 85));
		this.addEntity(new RefugeeTent(this, 936, 85));
		this.addEntity(new ChinatownHouse(this, 1038, 85));
		this.addEntity(new Warehouse(this, 1166, 27));
		this.addEntity(new TriadHQ(this, 1304, 85));
		this.addEntity(new Police(this, 690, 212));
		this.addEntity(new RussianClub(this, 948, 212));
		this.addEntity(new GunStore(this, 843, 407));
		this.addEntity(new NiceHouse2(this, 605, 150));
		this.addEntity(new MineShaft(this, 1567, 386));
		this.addEntity(new Castle(this, 1567, 75));
		this.addEntity(new ApartmentHighRise(this, 1112, 201));
		this.addEntity(new ModernSkyscraper(this, 1028, 432));
		this.addEntity(new Factory(this, 825, 564));
		this.addEntity(new RancheroHouse(this, 702, 757));
		this.addEntity(new Hotel(this, 852, 757));
		this.addEntity(new Prison(this, 1002, 757));
		this.addEntity(new RussianOrthodoxChurch(this, 1202, 757));
		this.addEntity(new SanCiscoCityHall(this, 1202, 957));
		this.addEntity(new CatholicChapel(this, 1022, 957));
		this.addEntity(new ShantyHouse(this, 802, 957));

		this.addEntity(new Projects(this, 1200, 1200));
		this.addEntity(new RadarDish(this, 1200, 1300));
		this.addEntity(new GreatTree(this, 1200, 1450));
		this.addEntity(new TreeHouse(this, 1300, 1200));
		this.addEntity(new UCGrizzly(this, 1300, 1300));
		this.addEntity(new OakwoodCityHall(this, 1400, 1400));
		this.addEntity(new JungleHQ(this, 1300, 1500));
		this.addEntity(new QuackerHQ(this, 1400, 1200));
		this.addEntity(new SanJuanCityHall(this, 1500, 1000));
		this.addEntity(new TheHub(this, 1500, 1100));
		this.addEntity(new SequoiaCinema(this, 1500, 1300));
		this.addEntity(new SequoiaSchool(this, 1500, 1400));

		this.addEntity(new Cafe(this, 1400, 400));
		this.addEntity(new CardinalUniversity(this, 1400, 600));
		this.addEntity(new PearHQ(this, 1400, 700));
		this.addEntity(new TechTopiaCityHall(this, 1400, 890));
		this.addEntity(new WeirdTechBuilding1(this, 1500, 900));
		this.addEntity(new WeirdTechBuilding2(this, 1546, 900));

		for (int i = 0; i < 4; i++)
			this.addEntity(new FireEntity(this, 230 + i * 8, 130));

	}

	@Override
	protected void initMapTransporters() {
		// TODO Auto-generated method stub

	}

}
