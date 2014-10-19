package ca.javajesus.game.entities;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Projectile extends Particle {

	private int speed;
	private int direction;
	private int direction2;

	public Projectile(Level level, int tileNumber, int color, double x, double y, int speed,
			int direction) {
		super(level, tileNumber, color, x, y);
		this.speed = speed;
		this.direction = direction;
		this.direction2 = 4;
	}
	
	public Projectile(Level level, int tileNumber, int color, double x, double y, int speed,
			int direction1, int direction2) {
		super(level, tileNumber, color, x, y);
		this.speed = speed;
		this.direction = direction1;
		this.direction2 = direction2;
	}

	public void render(Screen screen) {
		switch (direction) {
		case 0: {
			this.y -= speed;
			break;
		}
		case 1: {
			this.y += speed;
			break;
		}
		case 2: {
			this.x -= speed;
			break;
		}
		case 3: {
			this.x += speed;
			break;
		}
		default:
			break;
		}
		switch (direction2) {
		case 0: {
			this.y -= speed;
			break;
		}
		case 1: {
			this.y += speed;
			break;
		}
		case 2: {
			this.x -= speed;
			break;
		}
		case 3: {
			this.x += speed;
			break;
		}
		default:
			break;
		}
		screen.render(this.x, this.y, tileNumber, color, 1, 1, sheet);
	}

}
