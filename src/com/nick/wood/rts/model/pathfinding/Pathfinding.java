package com.nick.wood.rts.model.pathfinding;

import com.nick.wood.rts.model.map.Tile;
import com.nick.wood.rts.model.map.TileType;
import com.nick.wood.rts.utils.TileScoreComparator;
import com.nick.wood.rts.utils.Vector2I;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Pathfinding {

    private Tile[][] tiles;
    private Tile currentTile;
    private Vector2I endPosition;
    private int maxWidth;
    private int maxHeight;

    private final TileScoreComparator tileScoreComparator = new TileScoreComparator();

    public Pathfinding() {
    }

    public List<Tile> getRoute(Tile[][] tiles, Vector2I startPosition, Vector2I endPosition) {
        this.tiles = tiles;
        this.maxWidth = tiles.length;
        this.maxHeight = tiles[0].length;
        this.endPosition = endPosition;
        
        resetAllTiles();

        PriorityQueue<Tile> queue = new PriorityQueue<>(tileScoreComparator);
        queue.add(tiles[startPosition.getX()][startPosition.getY()]);

        boolean routeAvailable = false;

        while (!queue.isEmpty()) {

            do {
                if (queue.isEmpty()) break;
                currentTile = queue.remove();
            } while (!currentTile.isOpen());

            currentTile.setOpen(false);

            int currentX = currentTile.getRowNumber();
            int currentY = currentTile.getColNumber();
            int currentScore = currentTile.getScore();

            if (currentTile.getRowNumber() == endPosition.getX() && currentTile.getColNumber() == endPosition.getY()) {
                // at the end, return path
                System.out.println("Found end tile");
                routeAvailable = true;
                break;
            }

            // loop through neighbours and get scores. add these onto temp open list
            int smallestScore = 9999999;
            for (int x = -1; x <= 1; x+=2) {
                int nextX = currentX + x;
                // currentY is now nextY
                if (validTile(nextX, currentY)) {
                    int score = getScoreOfTile(tiles[nextX][currentY], currentScore);
                    if (score < smallestScore) {
                        smallestScore = score;
                    }
                    Tile thisTile = tiles[nextX][currentY];
                    thisTile.setScore(score);
                    queue.add(thisTile);
                    thisTile.setParent(currentTile);
                }
            }

            for (int y = -1; y <= 1; y+=2) {
                // currentX is now nextX
                int nextY = currentY + y;
                if (validTile(currentX, nextY)) {
                    int score = getScoreOfTile(tiles[currentX][nextY], currentScore);
                    if (score < smallestScore) {
                        smallestScore = score;
                    }
                    Tile thisTile = tiles[currentX][nextY];
                    thisTile.setScore(score);
                    queue.add(thisTile);
                    thisTile.setParent(currentTile);
                }
            }


        }

        // get List of tiles using current tile
        // returns reverse list btw
        if (routeAvailable) return getPath(currentTile);
        return new ArrayList<>();
    }

    private void resetAllTiles() {
        for (Tile[] tile : tiles) {
            for (int col = 0; col < tiles[0].length; col++) {
                tile[col].setOpen(true);
                tile[col].setParent(null);
                tile[col].setScore(0);

            }
        }
    }

    private List<Tile> getPath(Tile currentTile) {
        List<Tile> path = new ArrayList<>();
        while (currentTile != null) {
            path.add(currentTile);
            currentTile = currentTile.getParent();
        }
        return path;
    }

    private int distanceScoreAway(Tile currentTile) {
        return Math.abs(endPosition.getX() - currentTile.getColNumber()) + Math.abs(endPosition.getY() - currentTile.getRowNumber());
    }

    private int getScoreOfTile(Tile tile, int currentScore) {
        int guessScoreLeft = distanceScoreAway(tile);
        int extraMovementCost = 0;
        if (tile.getTileType() == TileType.CYAN) {
            extraMovementCost+=1000;
        }
        int movementScore = currentScore + 1;
        return guessScoreLeft + movementScore + extraMovementCost;
    }

    private boolean validTile(int nextX, int nextY) {
        if (nextX >= 0 && nextX < maxWidth) {
            if (nextY >= 0 && nextY < maxHeight) {
                return tiles[nextX][nextY].isOpen() && tiles[nextX][nextY].getTileType() != TileType.BLUE;
            }
        }
        return false;
    }
}
