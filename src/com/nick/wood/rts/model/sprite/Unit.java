package com.nick.wood.rts.model.sprite;

import com.nick.wood.rts.model.map.Tile;
import com.nick.wood.rts.model.map.TileType;
import com.nick.wood.rts.utils.Vector2I;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Unit {
    private final Shape shape;
    private List<Tile> pathTiles = new ArrayList<>();
    private int tileIndex = 0;
    private int waterSlower = 3;

    public Unit() {
        this.shape = new Circle(3);
    }

    public void update() {
        if (!pathTiles.isEmpty()) {
            if (tileIndex < pathTiles.size()) {
                Tile currentTile = pathTiles.get(pathTiles.size() - 1 - tileIndex);

                if (currentTile.getTileType() == TileType.CYAN) {
                    if (waterSlower != 0) {
                        waterSlower--;
                    } else {
                        waterSlower = 3;
                        this.shape.setTranslateX(currentTile.getX());
                        this.shape.setTranslateY(currentTile.getY());

                        tileIndex++;
                    }
                }
                else {

                    this.shape.setTranslateX(currentTile.getX());
                    this.shape.setTranslateY(currentTile.getY());

                    tileIndex++;
                }
            }
        }
    }

    public Shape getShape() {
        return shape;
    }

    public List<Tile> getPathTiles() {
        return pathTiles;
    }

    public void setPathTiles(List<Tile> pathTiles) {
        this.pathTiles = pathTiles;
        tileIndex = 0;
    }
}

