package ca.javajesus.game.entities;

import ca.javajesus.game.InputHandler;
import ca.javajesus.game.gfx.Colours;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Player extends Mob {

    private InputHandler input;
    private int colour = Colours.get(-1, 111, 300, 543);
    private int scale = 1;
    protected boolean isSwimming = false;
    private int tickCount = 0;
    private boolean changeLevel;
    private double scaledSpeed;

    public Player(Level level, double x, double y, InputHandler input) {
        super(level, "player", x, y, 1, 16, 16, SpriteSheet.player);
        this.input = input;
    }

    public double getPlayerVelocity() {
        return velocity;
    }

    public Level getLevel() {
        if (level == null) {
            return new Level("/levels/water_test_level.png");
        }
        return level;
    }

    public void tick() {
        int xa = 0;
        int ya = 0;
        if (input.space.isPressed()) {
            changeLevel = true;
        }
        if (input.up.isPressed()) {
            ya--;
        }
        if (input.down.isPressed()) {
            ya++;
        }
        if (input.left.isPressed()) {
            xa--;
        }
        if (input.right.isPressed()) {
            xa++;
        }
        if (isSwimming) {
            scaledSpeed = 0.35;
        } else if (input.shift.isPressed()) {
            scaledSpeed = 3;
        } else {
            scaledSpeed = 1;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya, scaledSpeed);
            isMoving = true;
        } else {
            isMoving = false;
        }
        int xx = (int) x;
        int yy = (int) y;
        if (level.getTile(xx >> 3, yy >> 3).getId() == 3) {
            isSwimming = true;
        }
        if (isSwimming && level.getTile(xx >> 3, yy >> 3).getId() != 3) {
            isSwimming = false;
        }
        tickCount++;

    }

    public void render(Screen screen) {
        this.hitBox.setLocation((int) this.x, (int) this.y);
        if (changeLevel) {
            level.remEntity(this);
            init(screen.getGame().randomLevel);
            screen.getGame().updateLevel();
            changeLevel = false;
            level.addEntity(this);
        }

        int xTile = 0;
        int yTile = 0;
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
        if (isSwimming) {
            int waterColour = 0;
            yOffset += 4;
            if (tickCount % 60 < 15) {
                waterColour = Colours.get(-1, 225, -1, -1);
            } else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
                yOffset -= 1;
                waterColour = Colours.get(-1, 115, 225, -1);
            } else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
                waterColour = Colours.get(-1, 115, -1, -1);
            } else {
                yOffset -= 1;
                waterColour = Colours.get(-1, 225, 225, -1);
            }
            screen.render(xOffset, yOffset + 3, 0 + 26 * 32, waterColour, 0x00,
                    1, sheet);
            screen.render(xOffset + 8, yOffset + 3, 0 + 26 * 32, waterColour,
                    0x01, 1, sheet);
        }
        screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
                * 32, colour, flipTop, scale, sheet);// upper body
        screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
                (xTile + 1) + yTile * 32, colour, flipTop, scale, sheet);// upper
                                                                         // body
        if (!isSwimming) {
            screen.render(xOffset + (modifier * flipBottom),
                    yOffset + modifier, xTile + (yTile + 1) * 32, colour,
                    flipBottom, scale, sheet);// lower body
            screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
                    + modifier, (xTile + 1) + (yTile + 1) * 32, colour,
                    flipBottom, scale, sheet);// lower body

        }
    }

    public boolean hasCollided(int xa, int ya) {
        int xMin = 0;
        int xMax = 7;
        int yMin = 3;
        int yMax = 7;
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMin)) {
                return true;
            }
        }
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMax)) {
                return true;
            }
        }
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMin, y)) {
                return true;
            }
        }
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMax, y)) {
                return true;
            }
        }

        return false;
    }
}
