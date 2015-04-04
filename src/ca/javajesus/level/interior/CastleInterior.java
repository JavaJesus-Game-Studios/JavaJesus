package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.entities.npcs.LordHillsborough;
import ca.javajesus.game.entities.structures.furniture.Throne;
import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.game.entities.structures.transporters.TransporterStairStone;
import ca.javajesus.level.Level;

public class CastleInterior extends Interior{
	private Point exitPoint;

	public CastleInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Interiors/Castle_1_Main_Hall.png", new Point(504,
				464), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {
		this.addEntity(new LordHillsborough(this, 504, 256));
		this.addEntity(new Throne(this, 504, 200 ));
		
	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 504, 472, nextLevel,
				exitPoint));
		this.addEntity(new TransporterStairStone(this, 352, 240, new CastleInteriorFloor1(new Point(336, 280), this), new Point(336, 280), Direction.NORTH));

	}

}


