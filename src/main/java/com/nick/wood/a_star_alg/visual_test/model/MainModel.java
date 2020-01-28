package com.nick.wood.a_star_alg.visual_test.model;

import com.nick.wood.a_star_alg.map.Map;
import com.nick.wood.a_star_alg.map.Tile;
import com.nick.wood.a_star_alg.pathfinding.Pathfinding;
import com.nick.wood.a_star_alg.visual_test.model.sprite.Unit;
import com.nick.wood.a_star_alg.visual_test.utils.MapParser;
import com.nick.wood.a_star_alg.visual_test.utils.MapReader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class MainModel {

    private final Pathfinding pathfinding;
    private final SimpleObjectProperty<Path> pathProperty = new SimpleObjectProperty<>(new Path());
    private int[] destinationLocation = new int[]{0,0};
    private Tile[][] tileMatrix;
    private int[] startPosition;
    private final Unit unit = new Unit();
    private ImageView img;


    public MainModel(URI mapURI) throws IOException {

        img = new ImageView(new Image(mapURI.toURL().toString()));

        int[][] mapMatrix = MapReader.readBMPMap(new File(mapURI));

        startPosition = MapParser.getInitialPlayerPosition(mapMatrix);

        Map map = new Map(MapParser.ConvertToMapArray(mapMatrix));

        this.tileMatrix = map.getTileMatrix();

        this.pathfinding = new Pathfinding();

        unit.getShape().setTranslateX(startPosition[0]);
        unit.getShape().setTranslateY(startPosition[1]);

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

        startPosition = new int[]{(int)unit.getShape().getTranslateX(), (int)unit.getShape().getTranslateY()};

        List<Tile> pathTiles = pathfinding.getRoute(tileMatrix, startPosition, destinationLocation);

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
        destinationLocation = new int[]{(int)endPoint.getX(), (int)endPoint.getY()};

        findPath();
    }

    public ImageView getImg() {
        return img;
    }
}
