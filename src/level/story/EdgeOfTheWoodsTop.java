package level.story;

import game.entities.structures.CaveEntrance;
import game.entities.structures.Hotel;
import game.entities.structures.NiceHouse;
import game.entities.structures.PoorHouse;
import game.entities.structures.transporters.MapTransporter;
import level.Level;
import utility.Direction;

public class EdgeOfTheWoodsTop extends Level {

	private static final long serialVersionUID = -6149629568236162343L;
	
	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates Edge Of The Woods Top section
	 */
	public EdgeOfTheWoodsTop() {
		super("/Levels/Wilderness_Areas/Edge_of_The_Woods_Top.png", true,
				"Edge of the Woods Top");
		setSpawnPoint(1832, 1544);
		
		System.err.println("Creating Edge Of The Woods Top");
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		// caves
		add(new CaveEntrance(this, 1856, 688));
		add(new CaveEntrance(this, 2616, 1176));
		add(new CaveEntrance(this, 2448, 2440));

		// hotel
		add(new Hotel(this, 1872, 1408));

		// houses
		add(new PoorHouse(this, 2040, 1536));
		add(new PoorHouse(this, 2056, 1632));
		add(new PoorHouse(this, 1920, 1568));
		add(new PoorHouse(this, 1936, 1672));
		add(new NiceHouse(this, 2048, 1416));
	}

	/**
	 * Edges of the map
	 */
	protected void initMapTransporters() {
		add(new MapTransporter(this, 0, (this.getHeight() * 8) - 8,
				EdgeOfTheWoods.level, Direction.SOUTH, (this.getWidth() * 8), 8));
		add(new MapTransporter(this, (this.getWidth() * 8) - 8, 0,
				SanCisco.level, Direction.EAST, 8,
				this.getHeight() * 8));

	}

}
