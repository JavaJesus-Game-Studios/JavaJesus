package level.interior;

import java.awt.Point;

import game.entities.Entity;
import game.entities.Spawner;
import game.entities.npcs.NPC;
import game.entities.structures.furniture.Bed;
import game.entities.structures.furniture.ChairFront;
import game.entities.structures.furniture.ChairSide;
import game.entities.structures.furniture.Chest;
import game.entities.structures.furniture.FilingCabinet;
import game.entities.structures.furniture.LongTable;
import game.entities.structures.furniture.Nightstand;
import game.entities.structures.furniture.Sofa;
import game.entities.structures.furniture.SquareTable;
import game.entities.structures.furniture.Television;
import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class NiceHouse1Interior extends Interior {

	private static final long serialVersionUID = -1360325449829006520L;

	private Point exitPoint;

	public NiceHouse1Interior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Nice_House_1_Interiors/Nice_House1_Floor1.png", new Point(252, 272), level);
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
