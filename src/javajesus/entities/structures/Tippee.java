package javajesus.entities.structures;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import level.Level;
import level.interior.TippeeInterior;

/*
 * It looks like tippee is spelled wrong
 */
public class Tippee extends Building {

	private static final long serialVersionUID = 5182709078376633434L;

	public Tippee(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF522900, 0xFF977F66, 0xFF335C33 }, Sprite.tippee, SolidEntity.QUARTER);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 10, y + 34, new TippeeInterior(new Point(x + 16, y + 45), level)));
	}

}
