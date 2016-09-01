package level.story;

import game.entities.structures.CaveEntrance;
import game.entities.structures.Hotel;
import game.entities.structures.Hut;
import game.entities.structures.NiceHouse;
import game.entities.structures.PoorHouse;
import game.entities.structures.Tippee;
import game.entities.structures.transporters.MapTransporter;
import game.entities.structures.trees.Forest;
import level.Level;
import utility.Direction;

public class EdgeOfTheWoods extends Level {

	private static final long serialVersionUID = 7087469826147389899L;
	
	// instance of the level that the player uses
	public static Level level;
	
	/**
	 * Creates Edge Of The Woods level
	 */
	public EdgeOfTheWoods() {
		super("/Levels/Wilderness_Areas/Edge_of_the_Woods_Main.png", true,
				"Edge of the Woods");
		setSpawnPoint(2704, 552);
		
		System.err.println("Creating Edge Of The Woods");

	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {

		Forest.generateForest(this, 1360, 2040, 3608, 7816);
		add(new CaveEntrance(this, 1464, 7584));
		add(new CaveEntrance(this, 3264, 7368));
		add(new CaveEntrance(this, 2008, 6368));
		add(new Hut(this, 2440, 5480));
		add(new PoorHouse(this, 2560, 5360));
		add(new PoorHouse(this, 2400, 5384));
		add(new PoorHouse(this, 2280, 5392));
		add(new PoorHouse(this, 2104, 5376));
		add(new PoorHouse(this, 2008, 5352));
		add(new PoorHouse(this, 2176, 5136));
		add(new PoorHouse(this, 2152, 4888));
		add(new PoorHouse(this, 2192, 4808));
		add(new NiceHouse(this, 2280, 5008));
		add(new NiceHouse(this, 2384, 4992));
		add(new Hotel(this, 2464, 4936));
		add(new CaveEntrance(this, 3464, 3696));
		add(new CaveEntrance(this, 2056, 3448));
		add(new CaveEntrance(this, 1888, 2520));
		add(new CaveEntrance(this, 3216, 216));
		add(new Tippee(this, 2824, 1040));
		add(new Tippee(this, 2736, 1016));
		add(new Tippee(this, 2672, 968));
		add(new Tippee(this, 2600, 912));
		add(new Tippee(this, 2616, 816));
		add(new Tippee(this, 2704, 768));
		add(new Tippee(this, 2792, 840));
		add(new Tippee(this, 2856, 936));

	}

	/**
	 * Edges of the map
	 */
	protected void initMapTransporters() {

		add(new MapTransporter(this, 0, 0,
				EdgeOfTheWoodsTop.level, Direction.NORTH, this.getWidth() * 8,
				8));

		add(new MapTransporter(this, (this.getWidth() * 8) - 8, 0,
				LordHillsboroughsDomain.level, Direction.EAST, 8,
				this.getHeight() * 8 / 3));

		add(new MapTransporter(this, (this.getWidth() * 8) - 8,
				this.getHeight() * 8 / 3, BautistasDomain.level,
				Direction.EAST, 8, this.getHeight() * 8 / 3));

		add(new MapTransporter(this, (this.getWidth() * 8) - 8,
				this.getHeight() * 16 / 3, TechTopia.level, Direction.EAST, 8,
				this.getHeight() * 8 / 3));

	}

}
