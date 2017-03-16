package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class UCGrizzlyInterior extends Interior {

	private static final long serialVersionUID = -1432476228711565355L;
	
	private Point exitPoint;
	
	public UCGrizzlyInterior(Point point, Level level) {
		super("/Buildings/Unique_HippyVille_Interiors/UC_Grizzly_Interior.png", new Point(944, 920), level);	
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
