package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class TreeHouseInterior extends Interior {

	private static final long serialVersionUID = -5998920750795035872L;

	private Point exitPoint;

	public TreeHouseInterior(Point point, Level level) {
		super("/Buildings/Unique_HippyVille_Interiors/Tree_House_Interior.png", new Point(392, 464), level);
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
		return new Entity[] {new TransporterInterior(this, 392, 464, nextLevel, exitPoint)};
	}

}
