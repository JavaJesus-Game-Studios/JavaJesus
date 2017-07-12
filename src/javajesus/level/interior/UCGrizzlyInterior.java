package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

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
