package ca.javajesus.game.entities;

import java.util.Random;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Spawner extends Entity {

    private Entity entity;
    private Level level;
    private Screen screen;
    private Random random = new Random();

    public Spawner(double x, double y, Level level, Entity entity, Screen screen) {
        super(level);
        this.x = x;
        this.y = y;
        this.level = level;
        this.entity = entity;
        this.screen = screen;
    }

    public void tick() {
        if (level.getEntities().size() > screen.getGame().ENTITY_LIMIT) {
            return;
        }
        if (random.nextInt(1000) == 0) {
            spawnMob();
        }

    }

    public void spawnMob() {
        Mob mob;
        if (entity instanceof Demon) {
            mob = new Demon(level, "Demon", x, y, 1, screen.getGame().player);
        } else {
            return;
        }
        screen.getGame().player.getLevel().addEntity(mob);
    }

    public void render(Screen screen) {
    }

}
