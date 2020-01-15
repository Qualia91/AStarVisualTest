package com.nick.wood.rts.model;

import com.nick.wood.rts.model.map.Map;
import com.nick.wood.rts.model.map.Tile;
import com.nick.wood.rts.model.pathfinding.Pathfinding;
import com.nick.wood.rts.model.sprite.Unit;
import com.nick.wood.rts.utils.MapParser;
import com.nick.wood.rts.utils.MapReader;
import com.nick.wood.rts.utils.Vector2I;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.File;
import java.util.List;

public class MainModel {

    private final Pathfinding pathfinding;
    private final SimpleObjectProperty<Path> pathProperty = new SimpleObjectProperty<>(new Path());
    private Vector2I destinationLocation = new Vector2I();
    private Tile[][] tileMatrix;
    private List<Tile> pathTiles;
    private Vector2I startPosition;
    private final Unit unit = new Unit();


    private final Timeline timeline = new Timeline();

    public MainModel() {

        String[][] mapMatrix = MapReader.readBMPMap(new File("src/com/nick/wood/rts/assets/testMap.bmp"));

        startPosition = MapParser.getInitialPlayerPosition(mapMatrix);

        Map map = new Map(MapParser.ConvertToMapArray(mapMatrix));

        this.tileMatrix = map.getTileMatrix();

        this.pathfinding = new Pathfinding();

        unit.getShape().setTranslateX(startPosition.getX());
        unit.getShape().setTranslateY(startPosition.getY());

        KeyFrame f = new KeyFrame(Duration.millis(50),
                ae -> unit.update());
        timeline.getKeyFrames().add(f);
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public Unit getUnit() {
        return unit;
    }

    private void findPath() {

        startPosition = new Vector2I((int)unit.getShape().getTranslateX(), (int)unit.getShape().getTranslateY());

        pathTiles = pathfinding.getRoute(tileMatrix, startPosition, destinationLocation);

        drawPath(pathTiles);

        unit.setPathTiles(pathTiles);
    }

    private void drawPath(List<Tile> pathTiles) {
        Path path = new Path();
        int index = 0;
        for (Tile tile : pathTiles) {
            double x = tile.getX() + (0.5 * tile.getWidth());
            double y = tile.getY() + (0.5 * tile.getHeight());
            if (index == 0) {
                path.getElements().add(new MoveTo(x, y));
            } else {
                path.getElements().add(new LineTo(x, y));
            }
            index++;
        }
        path.setStroke(Color.RED);
        pathProperty.set(path);
    }

    public SimpleObjectProperty<Path> getRoute() {
        return pathProperty;
    }

    public void updateDestinationPosition(Point3D endPoint) {
        destinationLocation = new Vector2I((int)endPoint.getX(), (int)endPoint.getY());

        findPath();
    }
}
