package level.interior;

import game.entities.Mob.Direction;
import game.entities.structures.transporters.TransporterInterior;
import game.entities.structures.transporters.TransporterStairWood;

import java.awt.Point;

import level.Level;

public class SkyscraperLobby extends Interior {

	private Point exitPoint;
	
	public SkyscraperLobby(Point point, Level level) {
		super("/Buildings/Generic Interiors/Skyscraper_Interiors/Skyscraper_Lobby.png", new Point(
				1944, 2048), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 1944, 2048, nextLevel,
				exitPoint));
		this.addEntity(new TransporterStairWood(this, 2112, 1968, 
				new SkyscraperFloor(new Point(2116, 1971), this, 1), new Point(2105, 2016), Direction.EAST));
	}
}
