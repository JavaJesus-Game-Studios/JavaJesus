package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class CafeInterior extends Interior {

	private static final long serialVersionUID = -7327198235470021081L;
	
	private Point exitPoint;
	
	public CafeInterior(Point point, Level level) {
		super("/Buildings/Unique_TechTopia_Interiors/Cafe_Interior.png", new Point(944, 920), level);	
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
		return new Entity[] {new TransporterInterior(this, 944, 920, nextLevel, exitPoint)};
	}

}
