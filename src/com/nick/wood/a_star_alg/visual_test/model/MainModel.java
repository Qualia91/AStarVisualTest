package com.nick.wood.a_star_alg.visual_test.model;

import com.nick.wood.a_star_alg.map.Map;
import com.nick.wood.a_star_alg.map.Tile;
import com.nick.wood.a_star_alg.pathfinding.Pathfinding;
import com.nick.wood.custom_math.Vector2I;
import com.nick.wood.a_star_alg.visual_test.model.sprite.Unit;
import com.nick.wood.a_star_alg.visual_test.utils.MapParser;
import com.nick.wood.a_star_alg.visual_test.utils.MapReader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

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


    public MainModel() {

        String[][] mapMatrix = MapReader.readBMPMap(new File("src/com/nick/wood/a_star_alg/visual_test/assets/testMap.bmp"));

        startPosition = MapParser.getInitialPlayerPosition(mapMatrix);

        Map map = new Map(MapParser.ConvertToMapArray(mapMatrix));

        this.tileMatrix = map.getTileMatrix();

        this.pathfinding = new Pathfinding();

        unit.getShape().setTranslateX(startPosition.getX());
        unit.getShape().setTranslateY(startPosition.getY());

        KeyFrame f = new KeyFrame(Duration.millis(50),
                ae -> unit.update());
        Timeline timeline = new Timeline();
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
