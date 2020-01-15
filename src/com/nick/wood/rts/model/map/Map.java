package com.nick.wood.rts.model.map;

public class Map {

    private Tile[][] tileMatrix;
    private final TileType[][] tileTypes;

    private final int TILE_WIDTH = 1;
    private final int TILE_HEIGHT = 1;

    public Map(TileType[][] tileTypes) {
        this.tileTypes = tileTypes;
        this.tileMatrix = new Tile[tileTypes.length][tileTypes[0].length];
        int rowNumber = 0;
        for (TileType[] row : tileTypes) {
            int colNumber = 0;
            for (TileType item : row) {
                Tile tile = new Tile(item, rowNumber, colNumber, TILE_HEIGHT, TILE_WIDTH);
                tileMatrix[rowNumber][colNumber] = tile;
                colNumber++;
            }
            rowNumber++;
        }
    }

    public Tile[][] getTileMatrix() {
        return tileMatrix;
    }
}
