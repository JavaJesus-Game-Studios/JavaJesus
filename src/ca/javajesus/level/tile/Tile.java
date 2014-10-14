package ca.javajesus.level.tile;

import ca.javajesus.game.gfx.Colours;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public abstract class Tile {

    /**
     * Each slots represents a different color on the sprite sheet -1 is
     * transparent, aka 255 First digit of number is red, second digit is green,
     * third digit is blue To convert to this 6 bit format, take the RGB value
     * individually Multiply by 5 and divide by 255 -- Round Use that number in
     * the respective RGB slot
     * 
     * Instead, use my Colours.toRGB Method if you don't want to do the work
     * 
     * Or, use my Colours.toHex
     */
    public static final Tile[] tiles = new Tile[256];
    public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colours.get(
            000, -1, -1, -1), 0xFF000000);
    public static final Tile STONE = new BasicSolidTile(1, 1, 0, Colours.get(
            -1, 333, -1, -1), 0xFF555555);
    public static final Tile GRASS = new BaseTile(2, 2, 0, Colours.get(-1, 131,
            141, -1), 0xFF00FF00);
    public static final Tile SAND = new BaseTile(4, 2, 0, Colours.get(-1, 540,
            550, -1), 0xFF00FF00);
    public static final Tile WATERSAND = new AnimatedTile(8, new int[][] {
            { 0, 4 }, { 1, 4 }, { 2, 4 }, { 3, 4 }, { 2, 4 }, { 1, 4 } },
            Colours.get(-1, Colours.toHex("#DBEDD2"), 115, -1), 0xFF0000FF,
            1000);
    public static final Tile WATER = new AnimatedTile(3, new int[][] {
            { 0, 4 }, { 1, 4 }, { 2, 4 }, { 3, 4 }, { 2, 4 }, { 1, 4 } },
            Colours.get(-1, 004, 115, -1), 0xFF0000FF, 1000);
    public static final Tile FIRE = new AnimatedTile(5, new int[][] { { 0, 3 },
            { 1, 3 }, { 2, 3 }, { 3, 3 }, { 4, 3 }, { 3, 3 }, { 2, 3 },
            { 1, 3 } }, Colours.get(Colours.toHex("#521B14"),
            Colours.toHex("#F7790A"), Colours.toHex("#F51F07"), -1),
            0xFF0000FF, 500);
    public static final Tile ROAD1 = new BaseTile(6, 3, 0, Colours.get(-1,
            000, -1, -1), 0xFF00FF00);
    public static final Tile ROAD2 = new BaseTile(7, 4, 0, Colours.get(-1,
            000, Colours.toHex("#BFAD47"), -1), 0xFF00FF00);

    protected byte id;
    protected boolean solid;
    protected boolean emitter;
    private int levelColour;
    private boolean hasMob;

    public Tile(int id, boolean isSolid, boolean isEmitter, int levelColour) {
        this.id = (byte) id;
        if (tiles[id] != null)
            throw new RuntimeException("Duplicate tile id on " + id);
        this.solid = isSolid;
        this.emitter = isEmitter;
        this.levelColour = levelColour;
        tiles[id] = this;
    }

    public byte getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isEmitter() {
        return emitter;
    }

    public int getLevelColour() {
        return levelColour;
    }

    public boolean hasMob() {
        return hasMob;
    }

    public abstract void tick();

    public abstract void render(Screen screen, Level level, int x, int y);

}
