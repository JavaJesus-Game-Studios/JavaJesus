package ca.javajesus.game.entities.weapons;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;

public class Sword extends Entity {

	private Sprite[] sprites;
	private Sprite sprite;
	private Player player;
	protected int color;
	private final int swingSpeed = 30;
	private int tickCount = 0;
	private int swordCount = 0;

	public Sword(Level level, Sprite[] sprites, Player player, int color) {
		super(level);
		this.sprites = sprites;
		this.sprite = sprites[sprites.length - 1];
		this.player = player;
		this.color = color;
	}

	public void tick() {

		tickCount++;
		if (tickCount > swingSpeed) {
			tickCount = 0;
			swordCount = 0;
			level.remEntity(this);
		}
		if (tickCount % (swingSpeed / 15) == 0) {
			swordCount++;
			if (sprites.length - swordCount - 1 >= 0)
				this.sprite = sprites[sprites.length - swordCount - 1];
		}
	}

	public void render(Screen screen) {
		switch(player.movingDir)
		{
		case 3:  
		this.x = player.x;
        this.y = player.y - 15;
        break;
        
		case 2:
		this.x = player.x;
	    this.y = player.y - 15;
	    break;
	    
		case 1:
	    this.x = player.x;
	    this.y = player.y - 15;
	    break;
	    
		case 0:
	    this.x = player.x;
	    this.y = player.y - 15;
	    break;
	            
		}
	    
		screen.render((int) x + 6, (int) y - 15, player.movingDir, color, sprite);
		if(tickCount>12)
		{
		    tickCount = 0;
		    swordCount = 0;
		    level.remEntity(this);
		}
	}
	
}
