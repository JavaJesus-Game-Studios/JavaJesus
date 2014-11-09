package ca.javajesus.game.entities;

import java.awt.geom.Ellipse2D;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Demon extends Mob {

    /** Color of the Demon */
    private int colour = Colors.get(-1, 111, 300, 550);
    private int fireballColour = Colors.get(-1, 550, Colors.toHex("#F7790A"),
            300);

    /** The player the demon is chasing */
    private Mob mob;

    private double scaledSpeed = 0.35;

    /** Range that the demon can target a player */
    protected Ellipse2D.Double aggroRadius;

    protected final int RADIUS = 32 * 8;
    private boolean cooldown = true;
    private int tickCount = 0;

    public Demon(Level level, String name, double x, double y, int speed) {
        super(level, name, x, y, speed, 14, 24, SpriteSheet.enemies, 100);
        this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
                RADIUS, RADIUS);
        checkRadius();
    }

    private void checkRadius() {

        for (Entity entity : level.getEntities()) {
            if (entity instanceof Player || entity instanceof NPC) {
                if (this.aggroRadius.intersects(((Mob) entity).hitBox)) {
                    this.mob = (Mob) entity;
                    return;
                }
            }
        }

    }

    public boolean hasCollided(int xa, int ya) {
        int xMin = 0;
        int xMax = 7;
        int yMin = 3;
        int yMax = 7;
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMin) || isWaterTile(xa, ya, x, yMin)) {
                return true;
            }
        }
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMax) || isWaterTile(xa, ya, x, yMax)) {
                return true;
            }
        }
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMin, y) || isWaterTile(xa, ya, xMin, y)) {
                return true;
            }
        }
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMax, y) || isWaterTile(xa, ya, xMax, y)) {
                return true;
            }
        }

        return false;
    }

    public void tick() {

        updateHealth();
        checkRadius();

        if (isMobCollision()) {
            moveRandomly();
            return;
        }
        int xa = 0;
        int ya = 0;
        if (this.aggroRadius.intersects(mob.hitBox)) {
            
            if ((int) mob.x > (int) this.x) {
                xa++;
            }
            if ((int) mob.x < (int) this.x) {
                xa--;
            }
            if ((int) mob.y > (int) this.y) {
                ya++;
            }
            if ((int) mob.y < (int) this.y) {
                ya--;
            }
            tickCount++;

            if (tickCount % 100 == 0) {
                cooldown = false;
            } else {
                cooldown = true;
            }
        }

        if (xa != 0 || ya != 0) {
            if (isMobCollision()) {
                isMoving = false;
                return;
            }
            move(xa, ya, scaledSpeed);
            isMoving = true;
        } else {
            isMoving = false;
        }

    }

    public void render(Screen screen) {

        this.hitBox.setLocation((int) this.x, (int) this.y - 16);
        this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
                RADIUS);
        int xTile = 0;
        int yTile = 0;
        int walkingSpeed = 4;
        int flipTop = (numSteps >> walkingSpeed) & 1;
        int flipMiddle = (numSteps >> walkingSpeed) & 1;
        int flipBottom = (numSteps >> walkingSpeed) & 1;

        if (movingDir == 0) {
            xTile += 10;
        }
        if (movingDir == 1) {
            xTile += 2;
        } else if (movingDir > 1) {
            xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
            flipTop = (movingDir - 1) % 2;
            flipMiddle = (movingDir - 1) % 2;
            flipBottom = (movingDir - 1) % 2;
        }

        int modifier = 8 * scale;
        double xOffset = x - modifier / 2;
        double yOffset = (y - modifier / 2 - 4) - modifier;

        // Upper body
        screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
                * 32, colour, flipTop, scale, sheet);

        // Upper body
        screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
                (xTile + 1) + yTile * 32, colour, flipTop, scale, sheet);

        // Middle Body
        screen.render(xOffset + (modifier * flipMiddle), yOffset + modifier,
                xTile + (yTile + 1) * 32, colour, flipBottom, scale, sheet);

        // Middle Body
        screen.render(xOffset + modifier - (modifier * flipMiddle), yOffset
                + modifier, (xTile + 1) + (yTile + 1) * 32, colour, flipBottom,
                scale, sheet);

        // Lower Body
        screen.render(xOffset + (modifier * flipBottom),
                yOffset + 2 * modifier, xTile + (yTile + 2) * 32, colour,
                flipBottom, scale, sheet);

        // Lower Body
        screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + 2
                * modifier, (xTile + 1) + (yTile + 2) * 32, colour, flipBottom,
                scale, sheet);

        if (!cooldown) {
            if (mob == null) {
                return;
            }
            level.addEntity(new Projectile(level, 6, 6, 0, fireballColour,
                    this.x + 5, (this.y - 7), 4, mob.x, mob.y));
        }

    }

}
