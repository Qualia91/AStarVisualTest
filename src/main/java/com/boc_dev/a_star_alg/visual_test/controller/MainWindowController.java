package com.boc_dev.a_star_alg.visual_test.controller;

import com.boc_dev.a_star_alg.visual_test.model.MainModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
            model.updateDestinationPosition(event.getPickResult().getIntersectedPoint());
        });

    }
}
