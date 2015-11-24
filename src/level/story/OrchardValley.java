package level.story;

import java.awt.Point;

import game.Game;
import game.entities.structures.CatholicChapel;
import game.entities.structures.CatholicChurch;
import game.entities.structures.CaveEntrance;
import game.entities.structures.RancheroHouse;
import game.entities.structures.transporters.MapTransporter;
import level.Level;
import utility.Direction;

public class OrchardValley extends Level {

	private static final long serialVersionUID = -5221999559788504392L;

	public OrchardValley() {
		super("/Levels/Cities/Dubland.png", true, "Orchard Valley");
		this.spawnPoint = new Point(136, 1816);
		startingSpawnPoint = new Point(136, 1816);

	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		// catholic stuff
		this.addEntity(new CatholicChapel(this, 1520, 2256));
		this.addEntity(new CatholicChapel(this, 3816, 1480));
		this.addEntity(new CatholicChurch(this, 608, 1504));
		this.addEntity(new CatholicChurch(this, 3992, 1368));
		// cave entrances
		this.addEntity(new CaveEntrance(this, 112, 1768));
		this.addEntity(new CaveEntrance(this, 1376, 3496));
		this.addEntity(new CaveEntrance(this, 3472, 2888));
		this.addEntity(new CaveEntrance(this, 3832, 80));
		// casa de los rancheros
		this.addEntity(new RancheroHouse(this, 464, 1416));
		this.addEntity(new RancheroHouse(this, 1288, 2352));
		this.addEntity(new RancheroHouse(this, 1480, 1064));
		this.addEntity(new RancheroHouse(this, 1552, 2392));
		this.addEntity(new RancheroHouse(this, 4056, 1552));
	}

	protected void initMapTransporters() {
		this.addEntity(new MapTransporter(this, 0, 0, Game.levels.sanJuan,
				Direction.WEST, 8, this.height * 8));

	}

}
