package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.Jesus;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.TransporterInterior;
import items.Item;
import level.Level;

public class CatholicChurchInterior extends Interior {

	private static final long serialVersionUID = 1502730285198440557L;
	
	private Point exitPoint;

	public CatholicChurchInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Catholic_Church_Interior.png", new Point(256,
				272), level);
		this.exitPoint = point;
	}

	protected NPC[] getNPCPlacement() {
		
		// its not a church without jesus
		return new NPC[] {new Jesus(this, 248, 153, "", 0)};
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return new Chest[] {new Chest(this, 377, 191, Item.strongHealthPack, Item.heavenlySword)};
	}

	protected Entity[] getOtherPlacement() {
		return new Entity[] {new TransporterInterior(this, 252, 278, nextLevel,
				exitPoint), new Spawner(this, 248, 173, Spawner.HEALTH_PACK)};
	}

}
