package ca.javajesus.game.entities;

import java.awt.Rectangle;
import java.util.Random;

import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;

public abstract class Mob extends Entity {

    protected String name;
    protected double speed;
    protected int numSteps = 0;
    protected boolean isMoving;
    protected int movingDir = 1;
    protected int scale = 1;
    public double velocity;
    public int width;
    public int height;
    protected double health;
    protected Rectangle hitBox;
    protected SpriteSheet sheet;

    public Mob(Level level, String name, double x, double y, int speed,
            int width, int height, double health, SpriteSheet sheet) {
        super(level);
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.health = health;
        this.hitBox = new Rectangle(width, height);
        this.sheet = sheet;
    }

    public void move(int xa, int ya, double speed) {
        velocity = this.speed * speed;
        if (xa != 0 && ya != 0) {
            move(xa, 0, velocity);
            move(0, ya, velocity);
            numSteps--;
            return;
        }
        numSteps++;
        if (!hasCollided(xa, ya)) {
            if (ya < 0)
                movingDir = 0;
            if (ya > 0)
                movingDir = 1;
            if (xa < 0)
                movingDir = 2;
            if (xa > 0)
                movingDir = 3;

            x += xa * velocity;
            y += ya * velocity;
        }
    }

    public abstract boolean hasCollided(int xa, int ya);

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    protected boolean isSolidTile(int xa, int ya, int x, int y) {
        if (level == null) {
            return false;
        }
        int xx = (int) this.x;
        int yy = (int) this.y;
        Tile lastTile = level.getTile((xx + x) >> 3, (yy + y) >> 3);
        Tile newTile = level.getTile((xx + x + xa) >> 3, (yy + y + ya) >> 3);
        if (!lastTile.equals(newTile) && newTile.isSolid()) {
            return true;
        }
        return false;
    }

    protected boolean isWaterTile(int xa, int ya, int x, int y) {
        if (level == null) {
            return false;
        }
        int xx = (int) this.x;
        int yy = (int) this.y;
        Tile lastTile = level.getTile((xx + x) >> 3, (yy + y) >> 3);
        Tile newTile = level.getTile((xx + x + xa) >> 3, (yy + y + ya) >> 3);
        if (!lastTile.equals(newTile) && newTile.equals(Tile.WATER)) {
            return true;
        }
        return false;
    }

    protected boolean isMobCollision() {
        if (level == null) {
            return false;
        }
        for (int i = 0; i < level.getEntities().size(); i++) {
            Mob entity;
            if (level.getEntities().get(i) instanceof Mob) {
                entity = (Mob) level.getEntities().get(i);
                if (entity == this) {
                    continue;
                }
            } else {
                continue;
            }
            if (this.hitBox.intersects(entity.hitBox)) {
                return true;
            }

        }
        return false;
    }

    protected void moveRandomly() {
        Random random = new Random();
        int xa = random.nextInt(3) - 1;
        int ya = random.nextInt(3) - 1;

        if (xa != 0 || ya != 0) {
            move(xa, ya, this.speed);
            isMoving = true;
        } else {
            isMoving = false;
        }
    }
    

    public String getName() {
        return name;
    }
}
