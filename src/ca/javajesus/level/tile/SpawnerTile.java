package ca.javajesus.level.tile;

import java.util.Random;

import ca.javajesus.game.entities.Demon;
import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class SpawnerTile extends Tile {

    protected int tileId;
    protected int tileColour;
    Random random = new Random();
    Demon demon;

    public SpawnerTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, false, false, levelColour);
        this.tileId = x + y * 32;
        this.tileColour = tileColour;
    }

    public void tick() {

    }

    public void spawnMob(Entity entity) {

    }

    public void render(Screen screen, Level level, int x, int y) {
        screen.render(x, y, tileId, tileColour, 0x00, 1);
        if (random.nextInt(15000) == 0) {
            demon = new Demon(level, "Demon", x, y, 1, screen.getGame().player);
            screen.getGame().player.getLevel().addEntity(demon);

        }
    }
}
