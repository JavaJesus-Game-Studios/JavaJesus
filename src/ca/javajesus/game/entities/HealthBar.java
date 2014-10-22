package ca.javajesus.game.entities;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;
import ca.javajesus.game.entities.Player;

public class HealthBar extends Particle
{
    private Player player;
    
    public HealthBar(Level level, int tileNumber, int color, double x, double y)
    {
        super(level, tileNumber, color, x, y);
    }
    
    public void render(Screen screen)
    {
        player = screen.getGame().player;
      
        {
        this.x = player.x;
        this.y = player.y - 18;
        }
        screen.render(this.x + 3, this.y, tileNumber, color, 1, 1, sheet);
        screen.render(this.x - 5, this.y, tileNumber + 1, color, 1, 1, sheet);
    }
}