package ca.javajesus.game.entities;

import java.util.Random;
import ca.javajesus.game.Game;
import ca.javajesus.game.gfx.Colours;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class HealthPack extends Particle 

{

    private static int healthPackColour = Colours.get(-1, 111, -1, 400);

    public HealthPack(Level level, int tileNumber, double x, double y) {
        super(level, tileNumber, healthPackColour, x, y);
    }

    public void setOffset(int yTileOffset) {
        this.tileNumber = yTileOffset * 32;
    }
    
    Random rand = new Random();
    

    public void render(Screen screen) {

        this.x = rand.nextInt(400);
        this.y = rand.nextInt(400);

        screen.render(this.x, this.y, tileNumber, color, 1, 1, sheet);
    }
    
}
