package ca.javajesus.game.entities;

import java.util.Random;

import ca.javajesus.game.Game;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Spawner extends Entity {

    private Entity entity;
    private Random random = new Random();

    public Spawner(Level level, double x, double y, Entity entity) {
        super(level);
        this.x = x;
        this.y = y;
        this.entity = entity;
    }

    public void tick() {
        if (level.getEntities().size() > Game.ENTITY_LIMIT) {
            return;
        }
        if (random.nextInt(1000) == 0) {
            spawnMob();
        }

    }

    public void spawnMob() {

        this.level.addEntity(entity);
    }

    public void render(Screen screen) {
    }

}
