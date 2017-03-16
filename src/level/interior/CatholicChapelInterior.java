package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class CatholicChapelInterior extends Interior {

	private static final long serialVersionUID = 5172539623347110557L;

	private Point exitPoint;

	public CatholicChapelInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Catholic_Chapel_Interior.png", new Point(256, 272), level);
		this.exitPoint = point;
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
		return new Entity[] {new TransporterInterior(this, 256, 278, nextLevel, exitPoint)};
	}

}
