package ca.javajesus.level;

import java.awt.Point;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.npcs.NPC;
import ca.javajesus.game.entities.structures.Castle;
import ca.javajesus.game.entities.structures.CastleTower;
import ca.javajesus.game.entities.structures.CatholicChapel;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.structures.GenericHospital;
import ca.javajesus.game.entities.structures.GunStore;
import ca.javajesus.game.entities.structures.Hotel;
import ca.javajesus.game.entities.structures.Hut;
import ca.javajesus.game.entities.structures.NiceHouse;
import ca.javajesus.game.entities.structures.NiceHouse2;
import ca.javajesus.game.entities.structures.trees.Forest;
import ca.javajesus.game.entities.vehicles.Vehicle;

public class LordHillsboroughsDomain extends Level{
	public LordHillsboroughsDomain(){
		super("/Levels/Cities/Domain of Lord Hillsborough.png", true);
		this.spawnPoint = new Point(1366, 1450);
		startingSpawnPoint = new Point(1366, 1450);

	}

	protected void initNPCPlacement() {
		
	}

	 
	protected void initSpawnerPlacement() {
		 
		
	}

	 
	protected void initChestPlacement() {
		 
		
	}

	 
	protected void otherEntityPlacement() {
		
		
		//The Huts
		this.addEntity(new Hut(this, 632, 288));
		this.addEntity(new Hut(this, 1560, 288));
		this.addEntity(new Hut(this, 632, 784));
		this.addEntity(new Hut(this, 2290, 264));
		this.addEntity(new Hut(this, 960, 1328));
		this.addEntity(new Hut(this, 1112, 1328));
		this.addEntity(new Hut(this, 1064, 1416));
		this.addEntity(new Hut(this, 936, 1480));
		this.addEntity(new Hut(this, 1088, 1528));
		
		//Knight's Tower
		this.addEntity(new CastleTower(this, 1721, 1025));
		
		//Lord Hillsborough's Castle
		this.addEntity(new Castle(this, 1208, 1240));

		//White Oaks 
		this.addEntity(new GenericHospital(this, 1734, 1656));
		this.addEntity(new GunStore(this, 2160, 1694));
		this.addEntity(new Hotel(this, 2208, 1840));
		this.addEntity(new CatholicChapel(this, 1872, 1673));

		this.addEntity(new NiceHouse2(this, 1928, 1508));
		this.addEntity(new NiceHouse2(this, 2024, 1508));
		this.addEntity(new NiceHouse2(this, 2120, 1508));
		this.addEntity(new NiceHouse2(this, 1976, 1676));
		this.addEntity(new NiceHouse2(this, 2072, 1676));
		this.addEntity(new NiceHouse2(this, 2264, 1676));
		
		this.addEntity(new NiceHouse(this, 1824, 1511));
		this.addEntity(new NiceHouse(this, 2216, 1511));
		this.addEntity(new NiceHouse(this, 1766, 1863));
		this.addEntity(new NiceHouse(this, 1832, 1863));
		this.addEntity(new NiceHouse(this, 1928, 1863));
		this.addEntity(new NiceHouse(this, 2024, 1863));
		this.addEntity(new NiceHouse(this, 2120, 1863));
		

		
		//Sequoia Shores
		this.addEntity(new NiceHouse2(this, 3063, 308));
		this.addEntity(new NiceHouse2(this, 2976, 448));
		this.addEntity(new NiceHouse2(this, 3264, 448));

		this.addEntity(new NiceHouse(this, 3160, 431));
		this.addEntity(new NiceHouse(this, 2876, 431));


		//Caves
		this.addEntity(new CaveEntrance(this, 1024, 40));
		this.addEntity(new CaveEntrance(this, 744, 2208));
		this.addEntity(new CaveEntrance(this, 3216, 1624));

		//this.addEntity(new Forest(this, 1360, 2040, 500, 500));
		//this.addEntity(new Forest(this, 2968, 1000, 256, 624));
		
		//this.addEntity(new Forest(this, 1366, 1450, 500, 500));
		//this.addEntity(new Forest(this, 0, 0, 3000, 3000));
		
	}
}
