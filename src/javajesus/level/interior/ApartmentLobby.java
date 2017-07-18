package javajesus.level.interior;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.entities.transporters.TransporterStair;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class ApartmentLobby extends Interior {

	private static final long serialVersionUID = 6462312596277450469L;
	
	private Point exitPoint;
	
	public ApartmentLobby(Point point, Level level) {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/APARTMENT_INTERIORS/Apartment_Lobby.png", new Point(
				1944, 2040), level);
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
		
		return new Entity[] {
				
				new TransporterInterior(this, 1944, 2048, nextLevel,
				exitPoint),
				
				new TransporterStair(this, 2113, 1710, 
				new SkyscraperFloor(new Point(2113, 1710), this, 1), new Point(2105, 2016), Direction.NORTH, TransporterStair.CARPET)
		};
		
	}
}
