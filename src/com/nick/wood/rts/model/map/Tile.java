package com.nick.wood.rts.model.map;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {

    private final int rowNumber;
    private final int colNumber;
    private final SimpleIntegerProperty x = new SimpleIntegerProperty();
    private final SimpleIntegerProperty y = new SimpleIntegerProperty();
    private final SimpleIntegerProperty height = new SimpleIntegerProperty();
    private final SimpleIntegerProperty width = new SimpleIntegerProperty();
    private final SimpleObjectProperty<TileType> tileType = new SimpleObjectProperty<>();
    private int score;
    private Tile parent;
    private boolean open;

    public Tile(TileType tileType, int x, int y, int height, int width) {
        rowNumber = x;
        colNumber = y;
        this.tileType.set(tileType);
        this.x.set(x * width);
        this.y.set(y * height);
        this.height.set(height);
        this.width.set(width);
        score = 0;
        parent = null;
        open = true;
    }

    public Tile(Tile tile) {
        rowNumber = tile.rowNumber;
        colNumber = tile.colNumber;
        this.tileType.set(tile.getTileType());
        this.x.set(tile.getX());
        this.y.set(tile.getY());
        this.height.set(tile.getHeight());
        this.width.set(tile.getWidth());
        score = 0;
        parent = null;
        open = true;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public int getX() {
        return x.get();
    }

    public SimpleIntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public SimpleIntegerProperty yProperty() {
        return y;
    }

    public int getHeight() {
        return height.get();
    }

    public SimpleIntegerProperty heightProperty() {
        return height;
    }

    public int getWidth() {
        return width.get();
    }

    public SimpleIntegerProperty widthProperty() {
        return width;
    }

    public TileType getTileType() {
        return tileType.get();
    }

    public SimpleObjectProperty<TileType> tileTypeProperty() {
        return tileType;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    public void setWidth(int width) {
        this.width.set(width);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Tile getParent() {
        return parent;
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tile) {
            return rowNumber == ((Tile) obj).rowNumber && colNumber == ((Tile) obj).colNumber;
        }
        return false;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
