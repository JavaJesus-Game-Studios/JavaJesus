package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Bed;
import javajesus.entities.solid.furniture.ChairFront;
import javajesus.entities.solid.furniture.ChairSide;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.solid.furniture.FilingCabinet;
import javajesus.entities.solid.furniture.LongTable;
import javajesus.entities.solid.furniture.Nightstand;
import javajesus.entities.solid.furniture.Sofa;
import javajesus.entities.solid.furniture.SquareTable;
import javajesus.entities.solid.furniture.Television;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class NiceHouse1Interior extends Interior {

	private Point exitPoint;

	public NiceHouse1Interior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Nice_House_1_Interiors/Nice_House1_Floor1.png", new Point(244, 264), level);
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
		
		return new Entity[] { new TransporterInterior(this, 252, 278, nextLevel, exitPoint),

				new Bed(this, 340, 240), new Bed(this, 272, 240), new Nightstand(this, 297, 240),

				new LongTable(this, 190, 235), new ChairFront(this, 192, 250), new ChairFront(this, 205, 250),
				new ChairFront(this, 192, 225), new ChairFront(this, 205, 225),

				new Sofa(this, 340, 151), new Television(this, 340, 177),

				new FilingCabinet(this, 160, 128), new FilingCabinet(this, 175, 128), new FilingCabinet(this, 190, 128),
				new FilingCabinet(this, 205, 128), new FilingCabinet(this, 220, 128), new FilingCabinet(this, 235, 128),
				new FilingCabinet(this, 250, 128),

				new SquareTable(this, 344, 207),

				new SquareTable(this, 205, 160), new ChairFront(this, 210, 175), new ChairFront(this, 210, 150),
				new ChairSide(this, 195, 165), new ChairSide(this, 220, 165)

		};
	}

}
