package ca.javajesus.game.entities;

import ca.javajesus.game.gfx.Colours;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Demon extends Mob {

    private int colour = Colours.get(-1, 111, 300, 543);
    // Player the Demon is Aggroed
    private Player player;
    private double scaledSpeed = 0.35;

    public Demon(Level level, String name, double x, double y, int speed,
            Player player) {
        super(level, name, x, y, speed);
        this.player = player;
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
        int xa = 0;
        int ya = 0;

        if (player.x > this.x) {
            xa++;
        }
        if (player.x < this.x) {
            xa--;
        }
        if (player.y > this.y) {
            ya++;
        }
        if (player.y < this.y) {
            ya--;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya, scaledSpeed);
            isMoving = true;
        } else {
            isMoving = false;
        }

    }

    public void render(Screen screen) {
        int xTile = 0;
        int yTile = 11;
        int walkingSpeed = 4;
        int flipTop = (numSteps >> walkingSpeed) & 1;
        int flipBottom = (numSteps >> walkingSpeed) & 1;

        if (movingDir == 0) {
            xTile += 10;
        }
        if (movingDir == 1) {
            xTile += 2;
        } else if (movingDir > 1) {
            xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
            flipTop = (movingDir - 1) % 2;

        }

        int modifier = 8 * scale;
        double xOffset = x - modifier / 2.0;
        double yOffset = y - modifier / 2.0 - 4;
        
        // Upper body
        screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
                * 32, colour, flipTop, scale);
        
        // Upper body
        screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
                (xTile + 1) + yTile * 32, colour, flipTop, scale);
        
        
        // Middle Body
        screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
                xTile + (yTile + 1) * 32, colour, flipBottom, scale);
                                                                     
        // Middle Body
        screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
                + modifier, (xTile + 1) + (yTile + 1) * 32, colour, flipBottom,
                scale);
        
        // Lower Body
        screen.render(xOffset + (modifier * flipBottom), yOffset +  2 * modifier,
                xTile + (yTile + 2) * 32, colour, flipBottom, scale);
                                                                     
        // Lower Body
        screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
                + 2 * modifier, (xTile + 1) + (yTile + 2) * 32, colour, flipBottom,
                scale);

    }

}
