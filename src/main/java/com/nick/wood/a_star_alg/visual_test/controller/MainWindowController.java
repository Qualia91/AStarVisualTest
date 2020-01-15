package com.nick.wood.a_star_alg.visual_test.controller;

import com.nick.wood.a_star_alg.visual_test.model.MainModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    public AnchorPane canvas;

    private final MainModel model;

    public MainWindowController(MainModel mainModel) {
        this.model = mainModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        canvas.getChildren().add(model.getImg());
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
