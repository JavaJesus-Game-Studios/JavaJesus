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
import game.entities.npcs.Knight;
import game.entities.npcs.Kobe;
import game.entities.npcs.LordHillsborough;
import game.entities.npcs.NPC;
import game.entities.npcs.Octavius;
import game.entities.npcs.Peasant;
import game.entities.npcs.Ranchero;
import game.entities.npcs.Son;
import game.entities.npcs.Wife;
import game.entities.npcs.Zorra;
import game.entities.npcs.aggressive.Gorilla;
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

public class Level1 extends Level {

	private static final long serialVersionUID = 5943407753519754342L;
	
	public static final String NAME = "Tester Level 1";
	
	public static final Level level = new Level1();

	public Level1() {
		super("/Levels/Test_Levels/tile_tester_level.png", true,
				NAME);
		setSpawnPoint(50, 50);
	}

	public void initNPCPlacement() {
		add(new UCGrizzly(this, 50, 50));

		add(new LordHillsborough(this, 50, 50));
		add(new Wife(this, 50, 70));
		add(new Daughter(this, 50, 90));
		add(new Son(this, 50, 110));
		add(new Octavius(this, 50, 130));
		add(new Kobe(this, 50, 150));
		add(new Bautista(this, 50, 170));
		add(new Jobs(this, 50, 190));
		add(new Zorra(this, 50, 210));
		add(new Ranchero(this, 50, 230));
		add(new NativeAmerican(this, 20, 70, NativeAmerican.MALE));
		add(new NativeAmerican(this, 20, 90, NativeAmerican.FEMALE));
		add(new Gorilla(this, 200, 200));

		add(new Istrahiim(this, 250, 180));

		add(new Knight(this, 200, 100, 150, "linear", 20));
		add(new PoliceOfficer(this, 160, 250));
		add(new NPC(this, "Citizen-Female", 200, 400,
				1, 16, 16, 100,
				new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0, 8,
				"cross", 30));
		add(new NPC(this, "Citizen-Male", 200, 500, 1,
				16, 16, 100, new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB },
				0, 0, "circle", 2));
		add(new NPC(this, "Fox", 250, 75, 1, 16, 16,
				100, new int[] { 0xFF111111, 0xFFFFA800, 0xFFFFFFFF }, 0, 14,
				"cross", 50));
		add(new TechWarrior(this, 400, 250));
		add(new SWATOfficer(this, 370, 120, 200, "linear", 20));
		add(new Peasant(this, 2005, 950, Peasant.MALE));
		add(new Peasant(this, 2025, 950, Peasant.FEMALE));
		add(new Peasant(this, 2035, 950, Peasant.BOY));
		add(new Peasant(this, 2045, 950, Peasant.GIRL));
		add(new Jesus(this, 300, 400, "stand", 30));

		add(new Cyclops(this, 600, 300, 1, 100));
		add(new GangMember(this, 430, 120, 1, 100,
				GangMember.TRIAD));
		add(new Centaur(this, 500, 200, 1, 100));
		add(new Monkey(this, 70, 70, 1, 100));
		add(new GangMember(this, 100, 150, 1, 100, 1));

	}

	@Override
	public void initSpawnerPlacement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initChestPlacement() {
		add(new Chest(this, 100, 200, Item.shotgun));

	}

	@Override
	public void otherEntityPlacement() {

		Forest.generateForest(this, 0, 500, 500, 500);

		add(new CenturyLeSabre(this, 350, 300));
		add(new Truck(this, 400, 300));
		add(new SportsCar(this, 450, 300));
		add(new Boat(this, "Boat", 357, 532, 2, 100));

		add(new Horse(this, 666, 930, 200, Horse.BROWN));

		add(new PoorHouse(this, 100, 50));
		add(new NiceHouse(this, 600, 50));
		add(new Hut(this, 300, 50));
		add(new CatholicChurch(this, 400, 25));
		add(new CastleTower(this, 500, 50));
		add(new CaveEntrance(this, 200, 50));
		add(new Skyscraper(this, 200, 200));
		add(new SanCiscoSkyscraper(this, 10, 2084));

		add(new VestPickup(this, 110, 25));
		add(new KnightPickup(this, 120, 25));
		add(new HornedPickup(this, 130, 25));
		add(new IstrahiimPickup(this, 140, 25));

		add(new QuickHealthPickup(this, 150, 25));
		add(new StrongHealthPack(this, 160, 25));
		add(new AssaultRifleAmmo(this, 170, 25));
		add(new RevolverAmmoPickup(this, 180, 25));
		add(new ShotgunAmmoPickup(this, 190, 25));
		add(new LaserAmmoPickup(this, 200, 25));
		add(new ArrowPickup(this, 210, 25));

		add(new GenericHospital(this, 700, 50));
		add(new Tippee(this, 838, 85));
		add(new RefugeeTent(this, 936, 85));
		add(new ChinatownHouse(this, 1038, 85));
		add(new Warehouse(this, 1166, 27));
		add(new TriadHQ(this, 1304, 85));
		add(new Police(this, 690, 212));
		add(new RussianClub(this, 948, 212));
		add(new GunStore(this, 843, 407));
		add(new NiceHouse2(this, 605, 150));
		add(new MineShaft(this, 1567, 386));
		add(new Castle(this, 1567, 75));
		add(new ApartmentHighRise(this, 1112, 201));
		add(new ModernSkyscraper(this, 1028, 432));
		add(new Factory(this, 825, 564));
		add(new RancheroHouse(this, 702, 757));
		add(new Hotel(this, 852, 757));
		add(new Prison(this, 1002, 757));
		add(new RussianOrthodoxChurch(this, 1202, 757));
		add(new SanCiscoCityHall(this, 1202, 957));
		add(new CatholicChapel(this, 1022, 957));
		add(new ShantyHouse(this, 802, 957));

		add(new Projects(this, 1200, 1200));
		add(new RadarDish(this, 1200, 1300));
		add(new GreatTree(this, 1200, 1450));
		add(new TreeHouse(this, 1300, 1200));
		add(new UCGrizzly(this, 1300, 1300));
		add(new OakwoodCityHall(this, 1400, 1400));
		add(new JungleHQ(this, 1300, 1500));
		add(new QuackerHQ(this, 1400, 1200));
		add(new SanJuanCityHall(this, 1500, 1000));
		add(new TheHub(this, 1500, 1100));
		add(new SequoiaCinema(this, 1500, 1300));
		add(new SequoiaSchool(this, 1500, 1400));

		add(new Cafe(this, 1400, 400));
		add(new CardinalUniversity(this, 1400, 600));
		add(new PearHQ(this, 1400, 700));
		add(new TechTopiaCityHall(this, 1400, 890));
		add(new WeirdTechBuilding1(this, 1500, 900));
		add(new WeirdTechBuilding2(this, 1546, 900));

		for (int i = 0; i < 4; i++)
			add(new FireEntity(this, 230 + i * 8, 130));

	}

	@Override
	protected void initMapTransporters() {
		// TODO Auto-generated method stub

	}

}
