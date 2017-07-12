package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.Jesus;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.items.Item;
import javajesus.level.Level;

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
