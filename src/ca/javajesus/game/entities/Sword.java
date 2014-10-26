package ca.javajesus.game.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Sword extends Entity {

	protected SpriteSheet sheet = SpriteSheet.swords;
	protected int tileNumber;
	protected int color;
	protected int width = 16;
	protected int height = 16;
	private final Rectangle hitBox = new Rectangle(20, 20);
	protected int degrees;
	protected int tickCount;
	protected BufferedImage image;

	public Sword(Level level, int tileNumber, int color, double x, double y,
			Player player) {
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y - 15;
		getImage();

	}

	private void getImage() {
		try {
			this.image = ImageIO.read(Sword.class
					.getResource("/Tiles/sword_sheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {

		tickCount++;
		degrees++;
		attackPos1();

	}

	// V Slash
	public void attackPos1() {

		double swingTick = tickCount * Math.PI / 60.0;

		if (swingTick < 3 * Math.PI / 4.0) {
			this.y += Math.sin(swingTick) / 3.0;
			this.x += Math.cos(swingTick) / 3.0;

		}

		if (tickCount > 60) {
			level.remEntity(this);
		}
	}

	// H Slash
	public void attackPos2() {

	}

	public void attackPos3() {

	}

	// Jab Jab Jab
	public void swing() {

	}

	public void render(Screen screen) {

		/*BufferStrategy bs = screen.getGame().getBufferStrategy();
		if (bs == null) {
			screen.getGame().createBufferStrategy(3);
			return;
		}

		Graphics g = image.createGraphics().create();
		g.drawImage(image, (int) this.x, (int) this.y, width, height, null);
		((Graphics2D) g).rotate(degrees);
		//g.dispose();
		bs.show();*/

		screen.render(this.x, this.y, tileNumber, color, 1, 2, sheet);

		hitBox.setLocation((int) this.x, (int) this.y);
		for (Entity entity : level.getEntities()) {
			if (entity instanceof Mob) {
				if (hitBox.intersects(((Mob) entity).hitBox)) {
					// ((Mob) entity).health--;
				}
			}

		}
	}

}
