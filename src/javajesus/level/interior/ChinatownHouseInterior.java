package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterInterior;
import javajesus.level.Level;

public class ChinatownHouseInterior extends Interior {

	private static final long serialVersionUID = 8609252348723234227L;
	
	private Point exitPoint;
	
	public ChinatownHouseInterior(Point point, Level level) {
		super("/Buildings/Unique_San_Cisco_Interiors/Chinatown_House_Interior.png", new Point(872, 752), level);	
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
		return new Entity[] {new TransporterInterior(this, 872, 752, nextLevel, exitPoint)};
	}

}
