package com.nick.wood.rts.controller;

import com.nick.wood.rts.model.MainModel;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public AnchorPane canvas;

    private final MainModel model;

    public MainWindowController(MainModel mainModel) {
        this.model = mainModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final Image image = new Image("File:src/com/nick/wood/rts/assets/testMap.bmp");
        ImageView img = new ImageView(image);
        canvas.getChildren().add(img);
        model.getRoute().addListener((observable, oldValue, newValue) -> {
            canvas.getChildren().remove(oldValue);
            canvas.getChildren().add(newValue);
        });
        canvas.getChildren().add(model.getUnit().getShape());

        setupBindings();
    }

    private void setupBindings() {

        canvas.setOnMouseClicked(event -> {
            /*if (event.isShiftDown()) {
                System.out.println("Start position changed");
                model.updateStartPosition(event.getPickResult().getIntersectedPoint());

            } else if (event.isControlDown()){
                System.out.println("End position changed");
                model.updateEndPosition(event.getPickResult().getIntersectedPoint());
            } else {
                System.out.println("Just Mouse Click");

            }*/

            model.updateDestinationPosition(event.getPickResult().getIntersectedPoint());
        });

    }
}
