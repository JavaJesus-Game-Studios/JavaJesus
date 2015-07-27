package ca.javajesus.game.entities.structures;

import java.awt.Rectangle;

import ca.javajesus.game.Game;
import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.TransporterGlass;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.generation.OldRandomGeneration;

public class SanCiscoSkyscraper extends SolidEntity {

	public SanCiscoSkyscraper(Level level, int x, int y) {
		super(level, x, y, 106, 338);
		this.shadow = new Rectangle(width, (8 * height / 9));
		this.shadow.setLocation(x, y);
		this.bounds = new JavaRectangle(width, (height / 9) - 8, this);
		this.bounds.setLocation(x, y + shadow.height);
		/*
		level.addEntity(new TransporterGlass(level, x + 44, y + 660,
				new OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));
		level.addEntity(new TransporterGlass(level, x + 100, y + 660,
				new OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));
		level.addEntity(new TransporterGlass(level, x + 156, y + 660,
				new OldRandomGeneration(Game.WIDTH, Game.HEIGHT)));*/
	}

	public void render(Screen screen) {

		screen.render(x, y, new int[] { 0xFFEDECE0, 0xFFFFFFFF, 0xFF030074 },
				Sprite.sanCisco_skyscraper);

	}

}
