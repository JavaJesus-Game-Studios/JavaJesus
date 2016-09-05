package level.story;

import game.entities.structures.CatholicChapel;
import game.entities.structures.CatholicChurch;
import game.entities.structures.CaveEntrance;
import game.entities.structures.RancheroHouse;
import game.entities.structures.transporters.MapTransporter;
import level.Level;
import utility.Direction;

public class OrchardValley extends Level {

	private static final long serialVersionUID = -5221999559788504392L;
	
	// instance of the level that the player uses
	public static Level level;

	/**
	 * Creates Orchard Valley
	 */
	public OrchardValley() {
		super("/Levels/Cities/Dubland.png", true, Level.ORCHARD);
		setSpawnPoint(136, 1816);
		
		System.err.println("Creating Orchard Valley");

	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		// catholic stuff
		add(new CatholicChapel(this, 1520, 2256));
		add(new CatholicChapel(this, 3816, 1480));
		add(new CatholicChurch(this, 608, 1504));
		add(new CatholicChurch(this, 3992, 1368));
		// cave entrances
		add(new CaveEntrance(this, 112, 1768));
		add(new CaveEntrance(this, 1376, 3496));
		add(new CaveEntrance(this, 3472, 2888));
		add(new CaveEntrance(this, 3832, 80));
		// casa de los rancheros
		add(new RancheroHouse(this, 464, 1416));
		add(new RancheroHouse(this, 1288, 2352));
		add(new RancheroHouse(this, 1480, 1064));
		add(new RancheroHouse(this, 1552, 2392));
		add(new RancheroHouse(this, 4056, 1552));
	}

	/**
	 * Edges of the map
	 */
	protected void initMapTransporters() {
		add(new MapTransporter(this, 0, 0, Level.JUAN,
				Direction.WEST, 8, this.getHeight() * 8));

	}

}
