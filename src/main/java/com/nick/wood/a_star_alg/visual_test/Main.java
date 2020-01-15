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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            // Chain of Responsibility pattern to get URI
            UserInputMap userInputMap = new UserInputMap();
            DefaultInputMap defaultInputMap = new DefaultInputMap();

            userInputMap.setSuccessor(defaultInputMap);

            Request request = new Request(getParameters().getNamed().get("Map"), "testMap.bmp");

            URI resource = userInputMap.handleRequest(request);

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

            primaryStage.setTitle("RTS");
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
