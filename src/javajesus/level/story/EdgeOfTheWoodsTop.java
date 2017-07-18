package javajesus.level.story;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.buildings.CaveEntrance;
import javajesus.entities.solid.buildings.Hotel;
import javajesus.entities.solid.buildings.NiceHouse;
import javajesus.entities.solid.buildings.PoorHouse;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class EdgeOfTheWoodsTop extends Level {

	private static final long serialVersionUID = -6149629568236162343L;

	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates Edge Of The Woods Top section
	 */
	public EdgeOfTheWoodsTop() {
		super("/WORLD_DATA/STORY_DATA/(X)WILDERNESS_AREA/Edge_of_The_Woods_Top.png", true, Level.EDGE_TOP, new Point(1832, 1544));

		System.err.println("Creating Edge Of The Woods Top");
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

		return new Entity[] {
				// caves
				new CaveEntrance(this, 1856, 688), new CaveEntrance(this, 2616, 1176),
				new CaveEntrance(this, 2448, 2440),

				// hotel
				new Hotel(this, 1872, 1408),

				// houses
				new PoorHouse(this, 2040, 1536), new PoorHouse(this, 2056, 1632), new PoorHouse(this, 1920, 1568),
				new PoorHouse(this, 1936, 1672), new NiceHouse(this, 2048, 1416) };
	}

	/**
	 * Edges of the map
	 */
	protected MapTransporter[] getMapTransporterPlacement() {

		return new MapTransporter[] {
				new MapTransporter(this, 0, (this.getHeight() * 8) - 8, Level.EDGE_MAIN, Direction.SOUTH,
						(this.getWidth() * 8), 8),
				new MapTransporter(this, (this.getWidth() * 8) - 8, 0, Level.CISCO, Direction.EAST, 8,
						this.getHeight() * 8)

		};

	}

}
