package ca.javajesus.level;

import java.awt.Point;
import java.util.ArrayList;

import ca.javajesus.game.entities.FireEntity;
import ca.javajesus.game.entities.monsters.Cyclops;
import ca.javajesus.game.entities.monsters.Monster;
import ca.javajesus.game.entities.npcs.NPC;
import ca.javajesus.game.entities.npcs.Policeman;
import ca.javajesus.game.entities.particles.Pickup;
import ca.javajesus.game.entities.structures.ApartmentHighRise;
import ca.javajesus.game.entities.structures.Cafe;
import ca.javajesus.game.entities.structures.CardinalUniversity;
import ca.javajesus.game.entities.structures.Castle;
import ca.javajesus.game.entities.structures.CastleTower;
import ca.javajesus.game.entities.structures.CatholicChapel;
import ca.javajesus.game.entities.structures.CatholicChurch;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.structures.ChinatownHouse;
import ca.javajesus.game.entities.structures.Factory;
import ca.javajesus.game.entities.structures.GenericHospital;
import ca.javajesus.game.entities.structures.GreatTree;
import ca.javajesus.game.entities.structures.GunStore;
import ca.javajesus.game.entities.structures.Hotel;
import ca.javajesus.game.entities.structures.Hut;
import ca.javajesus.game.entities.structures.JungleHQ;
import ca.javajesus.game.entities.structures.MineShaft;
import ca.javajesus.game.entities.structures.ModernSkyscraper;
import ca.javajesus.game.entities.structures.NiceHouse;
import ca.javajesus.game.entities.structures.NiceHouse2;
import ca.javajesus.game.entities.structures.OakwoodCityHall;
import ca.javajesus.game.entities.structures.Police;
import ca.javajesus.game.entities.structures.PoorHouse;
import ca.javajesus.game.entities.structures.Prison;
import ca.javajesus.game.entities.structures.Projects;
import ca.javajesus.game.entities.structures.QuackerHQ;
import ca.javajesus.game.entities.structures.RadarDish;
import ca.javajesus.game.entities.structures.RancheroHouse;
import ca.javajesus.game.entities.structures.RefugeeTent;
import ca.javajesus.game.entities.structures.RussianClub;
import ca.javajesus.game.entities.structures.RussianOrthodoxChurch;
import ca.javajesus.game.entities.structures.SanCiscoCityHall;
import ca.javajesus.game.entities.structures.SanCiscoSkyscraper;
import ca.javajesus.game.entities.structures.SanJuanCityHall;
import ca.javajesus.game.entities.structures.SequoiaCinema;
import ca.javajesus.game.entities.structures.SequoiaSchool;
import ca.javajesus.game.entities.structures.ShantyHouse;
import ca.javajesus.game.entities.structures.Skyscraper;
import ca.javajesus.game.entities.structures.TechTopiaCityHall;
import ca.javajesus.game.entities.structures.TheHub;
import ca.javajesus.game.entities.structures.Tippee;
import ca.javajesus.game.entities.structures.TreeHouse;
import ca.javajesus.game.entities.structures.TriadHQ;
import ca.javajesus.game.entities.structures.UCGrizzly;
import ca.javajesus.game.entities.structures.Warehouse;
import ca.javajesus.game.entities.structures.WeirdTechBuilding1;
import ca.javajesus.game.entities.structures.WeirdTechBuilding2;
import ca.javajesus.game.entities.structures.furniture.Chest;
import ca.javajesus.game.entities.vehicles.Boat;
import ca.javajesus.game.entities.vehicles.Vehicle;
import ca.javajesus.items.Item;

public class Level1 extends Level {

	public Level1() {
		super("/Levels/Test_Levels/tile_tester_level.png", true);
		this.spawnPoint = new Point(50, 50);
		startingSpawnPoint = new Point(50, 50);
	}

	public void initNPCPlacement() {
		this.addEntity(NPC.npc1);
		this.addEntity(NPC.npc2);
		this.addEntity(NPC.npc3);
		this.addEntity(NPC.npc4);
		this.addEntity(NPC.npc5);
		this.addEntity(NPC.npc6);
		this.addEntity(new Policeman(this, 370, 120, 200, "linear", 20));
		this.addEntity(NPC.npc8);
		this.addEntity(NPC.npc9);
		this.addEntity(NPC.npc10);
		this.addEntity(NPC.Jesus);
		
		this.addEntity(new Cyclops(this, 600, 300));
		this.addEntity(Monster.gang1);
		this.addEntity(Monster.horseThing1);
		this.addEntity(Monster.monkey);
		this.addEntity(Monster.gang2);
		// this.addEntity(Monster.man2);

	}

	@Override
	public void initSpawnerPlacement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initChestPlacement() {
		ArrayList<Item> chest1 = new ArrayList<Item>();
		chest1.add(Item.bazooka);
		this.addEntity(new Chest(this, 100, 200, chest1));

	}

	@Override
	public void otherEntityPlacement() {
		
		this.addEntity(Vehicle.vehicle1);
		this.addEntity(new Boat(this, null, 357, 532, 2, 100));
		this.addEntity(new PoorHouse(this, 100, 50));
		this.addEntity(new NiceHouse(this, 600, 50));
		this.addEntity(new Hut(this, 300, 50));
		this.addEntity(new CatholicChurch(this, 400, 25));
		this.addEntity(new CastleTower(this, 500, 50));
		this.addEntity(new CaveEntrance(this, 200, 50));
		this.addEntity(new Skyscraper(this, 200, 200));
		this.addEntity(new SanCiscoSkyscraper(this, 10, 2084));
		
		this.addEntity(new Pickup(this, 110, 25, Item.vest));
		this.addEntity(new Pickup(this, 120, 25, Item.knight));
		this.addEntity(new Pickup(this, 130, 25, Item.horned));
		this.addEntity(new Pickup(this, 140, 25, Item.owl));
		
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
		this.addEntity(new ShantyHouse(this, 802, 957, 802, 957));
		
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

}
