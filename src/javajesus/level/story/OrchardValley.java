package javajesus.level.story;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.buildings.CatholicChapel;
import javajesus.entities.solid.buildings.CatholicChurch;
import javajesus.entities.solid.buildings.CaveEntrance;
import javajesus.entities.solid.buildings.RancheroHouse;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class OrchardValley extends Level {

	private static final long serialVersionUID = -5221999559788504392L;

	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates Orchard Valley
	 */
	public OrchardValley() {
		super("/Levels/Cities/Dubland.png", true, Level.ORCHARD, new Point(136, 1816));

		System.err.println("Creating Orchard Valley");

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
				// catholic stuff
				new CatholicChapel(this, 1520, 2256), new CatholicChapel(this, 3816, 1480),
				new CatholicChurch(this, 608, 1504), new CatholicChurch(this, 3992, 1368),
				// cave entrances
				new CaveEntrance(this, 112, 1768), new CaveEntrance(this, 1376, 3496),
				new CaveEntrance(this, 3472, 2888), new CaveEntrance(this, 3832, 80),
				// casa de los rancheros
				new RancheroHouse(this, 464, 1416), new RancheroHouse(this, 1288, 2352),
				new RancheroHouse(this, 1480, 1064), new RancheroHouse(this, 1552, 2392),
				new RancheroHouse(this, 4056, 1552) };
	}

	/**
	 * Edges of the map
	 */
	protected MapTransporter[] getMapTransporterPlacement() {
		return new MapTransporter[] {
				new MapTransporter(this, 0, 0, Level.JUAN, Direction.WEST, 8, this.getHeight() * 8) };

	}

}
