package level.story;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.CaveEntrance;
import javajesus.entities.structures.Hotel;
import javajesus.entities.structures.Hut;
import javajesus.entities.structures.NiceHouse;
import javajesus.entities.structures.PoorHouse;
import javajesus.entities.structures.Tippee;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.MapTransporter;
import javajesus.entities.structures.trees.Forest;
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
		super("/Levels/Wilderness_Areas/Edge_of_the_Woods_Main.png", true, Level.EDGE_MAIN);
		setSpawnPoint(2704, 552);

		System.err.println("Creating Edge Of The Woods");
	}

	protected NPC[] getNPCPlacement() {
		return null;
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return null;
	}

	protected Entity[] getOtherPlacement() {
		
		Forest.generateForest(this, 1360, 2040, 3608, 7816);

		return new Entity[] { new CaveEntrance(this, 1464, 7584), new CaveEntrance(this, 3264, 7368),
				new CaveEntrance(this, 2008, 6368), new Hut(this, 2440, 5480), new PoorHouse(this, 2560, 5360),
				new PoorHouse(this, 2400, 5384), new PoorHouse(this, 2280, 5392), new PoorHouse(this, 2104, 5376),
				new PoorHouse(this, 2008, 5352), new PoorHouse(this, 2176, 5136), new PoorHouse(this, 2152, 4888),
				new PoorHouse(this, 2192, 4808), new NiceHouse(this, 2280, 5008), new NiceHouse(this, 2384, 4992),
				new Hotel(this, 2464, 4936), new CaveEntrance(this, 3464, 3696), new CaveEntrance(this, 2056, 3448),
				new CaveEntrance(this, 1888, 2520), new CaveEntrance(this, 3216, 216), new Tippee(this, 2824, 1040),
				new Tippee(this, 2736, 1016), new Tippee(this, 2672, 968), new Tippee(this, 2600, 912),
				new Tippee(this, 2616, 816), new Tippee(this, 2704, 768), new Tippee(this, 2792, 840),
				new Tippee(this, 2856, 936) };

	}

	/**
	 * Edges of the map
	 */
	protected MapTransporter[] getMapTransporterPlacement() {

		return new MapTransporter[] {

				new MapTransporter(this, 0, 0, Level.EDGE_TOP, Direction.NORTH, this.getWidth() * 8, 8),

				new MapTransporter(this, (this.getWidth() * 8) - 8, 0, Level.HILLSBOROUGH, Direction.EAST, 8,
						this.getHeight() * 8 / 3),

				new MapTransporter(this, (this.getWidth() * 8) - 8, this.getHeight() * 8 / 3, Level.BAUTISTA,
						Direction.EAST, 8, this.getHeight() * 8 / 3),

				new MapTransporter(this, (this.getWidth() * 8) - 8, this.getHeight() * 16 / 3, Level.TECH,
						Direction.EAST, 8, this.getHeight() * 8 / 3)

		};

	}

}
