package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import game.entities.structures.transporters.TransporterStairCarpet;
import level.Level;
import utility.Direction;

public class ApartmentLobby extends Interior {

	private static final long serialVersionUID = 6462312596277450469L;
	
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
