package ca.javajesus.level;

import java.util.Random;

public class RandomLevel extends Level {
    
    private static final Random random = new Random();
    protected int[] grid;

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    /** Generates the tiles array of the map */
    protected void generateLevel() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tile = x + y * width;

                // Makes sand borders around the map
                if (x < 3 || x > 60 || y < 3 || y > 60) {
                    if (tiles[tile] != water) {
                        tiles[tile] = sand;
                    }
                }

                // Makes random dirt patches
                if (random.nextInt(400) == 0) {
                    generateDirt(x, y);
                }

                // Generates roads
                if (random.nextInt(250) == 0) {
                    generateRoad(x, y);
                }

                // Generates water
                if (random.nextInt(350) == 0) {
                    generateWater(x, y);
                }

                // Sets default tiles to grass
                if (tiles[tile] == 0) {
                   /* int rand = random.nextInt(5);
                    if (rand == 0) {
                        type = grass;
                        tiles[tile] = grass;
                    } else if (rand == 1) {

                    }*/
                }

                getTile(x, y);

            }
        }

    }

    /** Determines the type of road created */
    private void generateRoad(int x, int y) {

        switch (random.nextInt(4)) {
        case 0:
            generateEastRoad(x, y);
            break;
        case 1:
            generateWestRoad(x, y);
            break;
        case 2:
            generateNorthRoad(x, y);
            break;
        case 3:
            generateSouthRoad(x, y);
            break;
        default:
            break;
        }

    }

    /** Generates a road segment from left to right */
    private void generateEastRoad(int x, int y) {

        // Road segment length
        int length = random.nextInt(15) + 10;

        // Main road generation
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2; j++) {
                if (i + x + (y + j) * width >= tiles.length
                        || i + x + (y + j) * width <= 0)
                    continue;
                if (tiles[i + x + (y + j) * width] == water) {
                    tiles[i + x + (y + j) * width] = lily;
                } else {
                    tiles[i + x + (y + j) * width] = road;
                }
            }

            // Chance of road breaking north or south
            if (random.nextInt(15) == 0) {
                switch (random.nextInt(3)) {
                case 0:
                    generateNorthRoad(i + x, y - 1);
                    break;
                case 1:
                    generateSouthRoad(i + x, y + 1);
                    break;
                case 2: {
                    generateNorthRoad(i + x, y - 1);
                    generateSouthRoad(i + x, y + 1);
                    break;
                }
                default:
                    break;
                }

            }

        }

        // Chance of road to continue once it ends
        if (random.nextInt(5) == 0) {
            generateRoad(x + length, y);
        }

    }

    /** Generates a road segment from right to left */
    private void generateWestRoad(int x, int y) {

        // Road segment length
        int length = random.nextInt(15) + 10;
        length *= -1;

        // Main road generation
        for (int i = length; i > 0; i--) {
            for (int j = 0; j < 2; j++) {
                if (i + x + (y + j) * width <= 0
                        || i + x + (y + j) * width >= tiles.length)
                    continue;
                if (tiles[i + x + (y + j) * width] == water) {
                    tiles[i + x + (y + j) * width] = lily;
                } else {
                    tiles[i + x + (y + j) * width] = road;
                }
            }

            // Chance of road breaking north or south
            if (random.nextInt(15) == 0) {
                switch (random.nextInt(3)) {
                case 0:
                    generateNorthRoad(i + x, y - 1);
                    break;
                case 1:
                    generateSouthRoad(i + x, y + 1);
                    break;
                case 2: {
                    generateNorthRoad(i + x, y - 1);
                    generateSouthRoad(i + x, y + 1);
                    break;
                }
                default:
                    break;
                }

            }

        }

        // Chance of road to continue once it ends
        if (random.nextInt(5) == 0) {
            generateRoad(x + length, y);
        }

    }

    /** Generates a road segment from down to up */
    private void generateNorthRoad(int x, int y) {

        // Road segment length
        int length = random.nextInt(15) + 10;
        length *= -1;

        // Main road generation
        for (int i = length; i > 0; i--) {
            for (int j = 0; j < 2; j++) {
                if (x + j + (y + i) * width <= 0
                        || x + j + (y + i) * width >= tiles.length)
                    continue;
                if (tiles[x + j + (y + i) * width] == water) {
                    tiles[x + j + (y + i) * width] = lily;
                } else {
                    tiles[x + j + (y + i) * width] = road;
                }
            }

            // Chance of road breaking east or west
            if (random.nextInt(15) == 0) {
                switch (random.nextInt(3)) {
                case 0:
                    generateEastRoad(x + 1, y + i);
                    break;
                case 1:
                    generateWestRoad(x - 1, y + i);
                    break;
                case 2: {
                    generateEastRoad(x + 1, y + i);
                    generateWestRoad(x - 1, y + i);
                    break;
                }
                default:
                    break;
                }
            }

        }
        // Chance of road to continue once it ends
        if (random.nextInt(5) == 0) {
            generateRoad(x, y + length);
        }

    }

    /** Generates a road segment from up to down */
    private void generateSouthRoad(int x, int y) {

        // Road segment length
        int length = random.nextInt(15) + 10;

        // Main road generation
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2; j++) {
                if (x + j + (y + i) * width >= tiles.length
                        || x + j + (y + i) * width <= 0)
                    continue;
                if (tiles[x + j + (y + i) * width] == water) {
                    tiles[x + j + (y + i) * width] = lily;
                } else {
                    tiles[x + j + (y + i) * width] = road;
                }
            }

            // Chance of road breaking east or west
            if (random.nextInt(15) == 0) {
                switch (random.nextInt(3)) {
                case 0:
                    generateEastRoad(x + 1, y + i);
                    break;
                case 1:
                    generateWestRoad(x - 1, y + i);
                    break;
                case 2: {
                    generateEastRoad(x + 1, y + i);
                    generateWestRoad(x - 1, y + i);
                    break;
                }
                default:
                    break;
                }
            }

        }
        // Chance of road to continue once it ends
        if (random.nextInt(5) == 0) {
            generateRoad(x, y + length);
        }

    }

    private void generateDirt(int x, int y) {

        int radius = random.nextInt(5) + 8;
        if (radius % 2 == 1)
            radius++;

        for (int i = radius; i > 1; i--) {
            for (int angle = 0; angle < 360; angle++) {
                double theta = angle;
                int tile = (int) (x + radius
                        * (Math.sin(theta) * Math.cos(theta)))
                        + (int) (y + radius
                                * (Math.sin(theta) * Math.sin(theta))) * width;
                if (tile <= 0 || tile >= width * height) {
                    break;
                } else if (tiles[tile] != 0) {

                } else {
                    tiles[tile] = dirt;
                }

            }
            radius--;
        }

    }

    private void generateWater(int x, int y) {

        byte type;
        int radius = random.nextInt(5) + 8;
        int outerCircle = radius;

        for (int i = radius; i > 1; i--) {
            if (i == outerCircle) {
                type = sand;
            } else if (i == outerCircle - 1) {
                type = waterSand;
            } else {
                type = water;
            }
            for (int angle = 0; angle < 360; angle++) {
                double theta = angle;
                int tile = (int) (x + radius
                        * (Math.sin(theta) * Math.cos(theta)))
                        + (int) (y + radius
                                * (Math.sin(theta) * Math.sin(theta))) * width;

                if (tile <= 0 || tile >= width * height) {
                    break;
                } else if (tiles[tile] == road) {
                    tiles[tile] = lily;
                } else {
                    tiles[tile] = type;
                }
            }
            radius--;
        }

    }

}
