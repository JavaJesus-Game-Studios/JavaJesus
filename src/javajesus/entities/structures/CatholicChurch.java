package javajesus.entities.structures;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.CatholicChurchInterior;

/*
 * A bigger place to pray!
 */
public class CatholicChurch extends Building {

	private static final long serialVersionUID = -3808305570492236904L;

	public CatholicChurch(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFD30000 }, Sprite.catholic_church, SolidEntity.HALF);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 38, y + 59,
				new CatholicChurchInterior(new Point(x + 44, y + 70), getLevel())));
	}

}
