package ca.javajesus.game.entities;


	import java.awt.Rectangle;
import java.util.Random;

import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

	public class DestructibleTile extends Entity{
		
		public double health;
		public double startHealth;
		public Rectangle hitBox;
		public boolean hasDied;
		protected int healthTickCount = 0;
		public final Rectangle bounds;
		protected int color;

		private Random random = new Random();

		public DestructibleTile(Level level, double x, double y, int width, int height, double defaultHealth) {
			super(level);
			this.isSolid = true;
			this.x = x;
			this.y = y;
			this.bounds = new Rectangle(width, (height / 2) - 8);
			this.bounds.setLocation((int) x, (int) y);
			this.health = defaultHealth;
			this.startHealth = defaultHealth;
			this.hitBox = new Rectangle(width, height);

		}

		public void tick() {

		}

		public void render(Screen screen) {

		}
		/** Triggers the death animation and closure */
		public void kill() {

			hasDied = true;
			level.remEntity(this);
		}
		public void damage(int a, int b) {
			int damage = random.nextInt(b - a + 1) + a;
			if (damage > 0)
				this.health -= damage;
		}

	}
