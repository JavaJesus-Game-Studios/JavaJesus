package ca.javajesus.level;

import java.awt.Point;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.structures.CaveEntrance;
import ca.javajesus.game.entities.structures.Hotel;
import ca.javajesus.game.entities.structures.Hut;
import ca.javajesus.game.entities.structures.NiceHouse;
import ca.javajesus.game.entities.structures.PoorHouse;
import ca.javajesus.game.entities.structures.Tippee;
import ca.javajesus.game.entities.structures.transporters.MapTransporter;
import ca.javajesus.game.entities.structures.trees.Forest;

public class EdgeOfTheWoods extends Level {

	private static final long serialVersionUID = 7087469826147389899L;

	public EdgeOfTheWoods() {
		super("/Levels/Wilderness_Areas/Edge_of_the_Woods_Main.png", true,
				"Edge of the Woods");
		this.spawnPoint = new Point(2704, 552);
		startingSpawnPoint = new Point(2704, 552);

	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {

		this.addEntity(new Forest(this, 1360, 2040, 3608, 7816));
		this.addEntity(new CaveEntrance(this, 1464, 7584));
		this.addEntity(new CaveEntrance(this, 3264, 7368));
		this.addEntity(new CaveEntrance(this, 2008, 6368));
		this.addEntity(new Hut(this, 2440, 5480));
		this.addEntity(new PoorHouse(this, 2560, 5360));
		this.addEntity(new PoorHouse(this, 2400, 5384));
		this.addEntity(new PoorHouse(this, 2280, 5392));
		this.addEntity(new PoorHouse(this, 2104, 5376));
		this.addEntity(new PoorHouse(this, 2008, 5352));
		this.addEntity(new PoorHouse(this, 2176, 5136));
		this.addEntity(new PoorHouse(this, 2152, 4888));
		this.addEntity(new PoorHouse(this, 2192, 4808));
		this.addEntity(new NiceHouse(this, 2280, 5008));
		this.addEntity(new NiceHouse(this, 2384, 4992));
		this.addEntity(new Hotel(this, 2464, 4936));
		this.addEntity(new CaveEntrance(this, 3464, 3696));
		this.addEntity(new CaveEntrance(this, 2056, 3448));
		this.addEntity(new CaveEntrance(this, 1888, 2520));
		this.addEntity(new CaveEntrance(this, 3216, 216));
		this.addEntity(new Tippee(this, 2824, 1040));
		this.addEntity(new Tippee(this, 2736, 1016));
		this.addEntity(new Tippee(this, 2672, 968));
		this.addEntity(new Tippee(this, 2600, 912));
		this.addEntity(new Tippee(this, 2616, 816));
		this.addEntity(new Tippee(this, 2704, 768));
		this.addEntity(new Tippee(this, 2792, 840));
		this.addEntity(new Tippee(this, 2856, 936));

	}

	protected void initMapTransporters() {

		this.addEntity(new MapTransporter(this, 0, 0,
				Game.levels.edgeOfTheWoodsTop, new Point(Game.player.getX(),
						(Game.levels.edgeOfTheWoodsTop.height * 8) - 16),
				this.width * 8, 8));

		this.addEntity(new MapTransporter(this, (this.width * 8) - 8, 0,
				Game.levels.lordHillsboroughsDomain, new Point(16,
						(int) (Game.player.getY())), 8, this.height * 8 / 3));

		this.addEntity(new MapTransporter(this, (this.width * 8) - 8,
				this.height * 8 / 3, Game.levels.bautistasDomain, new Point(16,
						Game.player.getY() - (this.height * 8 / 3) ), 8, this.height * 8 / 3));

		this.addEntity(new MapTransporter(this, (this.width * 8) - 8, this.height * 16 / 3,
				Game.levels.techTopia, new Point(16,
						Game.player.getY() - (this.height * 16 / 3)), 8, this.height * 8 / 3));

	}

}
