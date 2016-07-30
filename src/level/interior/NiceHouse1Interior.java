package level.interior;

import java.awt.Point;

import game.entities.structures.furniture.Bed;
import game.entities.structures.furniture.ChairFront;
import game.entities.structures.furniture.ChairSide;
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

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 252, 278, nextLevel, exitPoint));

		add(new Bed(this, 340, 240));
		add(new Bed(this, 272, 240));
		add(new Nightstand(this, 297, 240));

		add(new LongTable(this, 190, 235));
		add(new ChairFront(this, 192, 250));
		add(new ChairFront(this, 205, 250));
		add(new ChairFront(this, 192, 225));
		add(new ChairFront(this, 205, 225));

		add(new Sofa(this, 340, 151));
		add(new Television(this, 340, 177));

		add(new FilingCabinet(this, 160, 128));
		add(new FilingCabinet(this, 175, 128));
		add(new FilingCabinet(this, 190, 128));
		add(new FilingCabinet(this, 205, 128));
		add(new FilingCabinet(this, 220, 128));
		add(new FilingCabinet(this, 235, 128));
		add(new FilingCabinet(this, 250, 128));

		add(new SquareTable(this, 344, 207));

		add(new SquareTable(this, 205, 160));
		add(new ChairFront(this, 210, 175));
		add(new ChairFront(this, 210, 150));
		add(new ChairSide(this, 195, 165));
		add(new ChairSide(this, 220, 165));
	}

}
