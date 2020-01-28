package com.nick.wood.a_star_alg.visual_test;

import com.nick.wood.a_star_alg.visual_test.controller.MainWindowController;
import com.nick.wood.a_star_alg.visual_test.model.CoR.DefaultInputMap;
import com.nick.wood.a_star_alg.visual_test.model.CoR.Request;
import com.nick.wood.a_star_alg.visual_test.model.CoR.UserInputMap;
import com.nick.wood.a_star_alg.visual_test.model.MainModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * Main method for JavaFx Application.
 *
 * By default it will use the testMap.bmp in resources as the map. The user may choose
 * to provide a new map to the program at run using the following command line arguments:
 *
 * --map="full_path_to_file\file_name.bmp"
 *
 * However, the map must be a bmp format and must have the following colour scheme:
 *
 * BLACK:   rgb(0, 0, 0):       This will act as walls the player cannot pass through.
 * WHITE:   rgb(255, 255, 255): This will be free space the player can move through freely at normal speed.
 * RED:     rgb(255, 0, 0):     This will be the player starting position. If not provided by the user, the
 * player will start at x = 0, y = 0.
 * CYAN:    rgb(0, 255, 255):   This is a difficult space the player will move through slowly, reflected in the
 * algorithm trying to avoid these areas.
 *
 * When the screen loads, the user can set a destination by clicking the map. A red line will be produced
 * and the player (small black dot) will start the travel along it. The destination can be reset over and over again.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            // Chain of Responsibility pattern to get URI
            UserInputMap userInputMap = new UserInputMap();
            DefaultInputMap defaultInputMap = new DefaultInputMap();
            userInputMap.setSuccessor(defaultInputMap);
            URI resource = userInputMap.handleRequest(new Request(getParameters().getNamed().get("map"), "testMap.bmp"));

            MainModel mainModel = new MainModel(resource);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindowView.fxml"));

            loader.setControllerFactory(controller -> {
                if (controller.equals(MainWindowController.class)) {
                    return new MainWindowController(mainModel);
                }
                else {
                    throw new RuntimeException("Failed to create controller");
                }
            });

            Parent root = loader.load();

            primaryStage.setTitle("Visual A Star Test");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
