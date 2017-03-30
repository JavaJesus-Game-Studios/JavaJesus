package level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.TransporterInterior;
import javajesus.entities.structures.transporters.TransporterStair;
import level.Level;
import utility.Direction;

public class SkyscraperLobby extends Interior {

	private static final long serialVersionUID = 6039299252858515390L;

	private Point exitPoint;

	public SkyscraperLobby(Point point, Level level) {
		super("/Buildings/Generic Interiors/Skyscraper_Interiors/Skyscraper_Lobby.png", new Point(1944, 2048), level);
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
		return new Entity[] { new TransporterInterior(this, 1944, 2048, nextLevel, exitPoint),
				new TransporterStair(this, 2112, 1968, new SkyscraperFloor(new Point(2116, 1971), this, 1),
						new Point(2105, 2016), Direction.EAST, TransporterStair.WOOD) };
	}
}
