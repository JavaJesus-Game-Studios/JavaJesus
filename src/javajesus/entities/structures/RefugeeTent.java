package javajesus.entities.structures;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.RefugeeTentInterior;

/*
 * A small tent
 */
public class RefugeeTent  extends Building {

	private static final long serialVersionUID = 2510389595988201303L;

	public RefugeeTent(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFF8FA8F, 0xFF000000 }, Sprite.refugee_tent, SolidEntity.QUARTER);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 34, y + 7,
				new RefugeeTentInterior(new Point(x + 40, y + 18), level)));
	}

}
