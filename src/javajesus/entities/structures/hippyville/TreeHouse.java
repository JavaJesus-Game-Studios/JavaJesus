package javajesus.entities.structures.hippyville;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.TreeHouseInterior;

public class TreeHouse extends Building {

	private static final long serialVersionUID = 1952515854865848495L;

	public TreeHouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF143914, 0xFF522900 }, Sprite.treehouse, SolidEntity.TWO_THIRDS);
		
		this.setBounds(getBounds().x + 22, getBounds().y, getBounds().width - 44, getBounds().height);
		
		level.add(
				new Transporter(level, x + 18, y + 30, new TreeHouseInterior(new Point(x + 24, y + 42), level)));
	}

}
