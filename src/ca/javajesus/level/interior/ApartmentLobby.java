package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.game.entities.structures.transporters.TransporterStairCarpet;
import ca.javajesus.game.entities.structures.transporters.TransporterStairWood;
import ca.javajesus.level.Level;

public class ApartmentLobby extends Interior {

	private Point exitPoint;
	
	public ApartmentLobby(Point point, Level level) {
		super("/Buildings/Generic Interiors/Apartment_Interiors/Apartment_Lobby.png", new Point(
				1952, 2048), level);
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
		this.addEntity(new TransporterStairCarpet(this, 2113, 1710, 
				new SkyscraperFloor(new Point(2113, 1710), this, 1), new Point(2105, 2016), Direction.NORTH));
	}
}
