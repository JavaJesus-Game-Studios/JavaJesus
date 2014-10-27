package ca.javajesus.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.Game;
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
	protected Player player;
	protected double swingTick;
	protected double angle;

	public Sword(Level level, int tileNumber, int color, double x, double y,
			Player player) {
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y - 15;
		this.player = player;
		getImage();

	}

	private void getImage() {
		try {
			this.image = ImageIO.read(new File("res/Tiles/sword_sheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {

		tickCount++;
		angle += 10;
		if (angle > 360) {
			angle = 0;
		}
		attackPos1();

	}

	// V Slash
	public void attackPos1() {

		swingTick = tickCount * Math.PI / 60.0;

		/*if (swingTick < 3 * Math.PI / 4.0) {
			this.x += Math.cos(swingTick) / 3.0;
			this.y += Math.sin(swingTick) / 3.0;

		}*/

		if (tickCount > 48) {
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

		// screen.render(this.x, this.y, tileNumber, color, 1, 1.5, sheet);

		hitBox.setLocation((int) this.x, (int) this.y);
		for (Entity entity : level.getEntities()) {
			if (entity instanceof Mob) {
				if (hitBox.intersects(((Mob) entity).hitBox)) {
					// ((Mob) entity).health--;
				}
			}

		}
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		Point p = getSwordHandlePoint();

		g2d.setColor(Color.RED);
		g2d.drawLine(Game.WIDTH * Game.SCALE / 2, Game.HEIGHT * Game.SCALE / 2, p.x, p.y);

		AffineTransform at = AffineTransform.getTranslateInstance(p.x, p.y);
		
		at.rotate(Math.toRadians(-angle));
		g2d.setTransform(at);
		g2d.drawImage(image, 0, 0, width, height, null);

		g2d.dispose();
	}
	
	protected Point getSwordHandlePoint() {

		// Player Height
        int radius = 10;

        int x = Math.round(width / 2);
        int y = Math.round(height / 2);

        double rads = Math.toRadians(angle - 180); // Make 0 point out to the right...
        // If you add sword.getWidth, you might be able to change the above...
        int fullLength = Math.round((radius / 2f)) - image.getWidth();

        // Calculate the outter point of the line
        int xPos = Math.round((float) (x + Math.cos(rads) * fullLength)) + Game.WIDTH * Game.SCALE / 2;
        int yPos = Math.round((float) (y - Math.sin(rads) * fullLength)) + Game.HEIGHT * Game.SCALE / 2;
        
        return new Point(xPos, yPos);

    }

}
